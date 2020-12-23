angular.module('ionicApp')

    .controller('journalEditCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup,$state,$stateParams) {

        $scope.selectJournalParams = {
            journalId : $stateParams.journalId == undefined?'':$stateParams.journalId,
            title : '',
            content : '',
            weather : '',
            temperature : '',
            file : '',
        };

        $scope.selectJournalById = function () {

            $http({
                method: 'POST',
                url: 'journal/selectSingle',
                params: {
                    journalId : $scope.selectJournalParams.journalId,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectJournalParams.title = response.data.title;
                $scope.selectJournalParams.content = response.data.content;
                $scope.selectJournalParams.weather = response.data.weather;
                $scope.selectJournalParams.temperature = response.data.temperature;
                $scope.selectJournalParams.file = response.data.file;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insert = function (url,isModify) {

            $scope.fileId = $scope.genUUID();

            var form = new FormData();
            var file = document.getElementById("fileUpload").files[0];
            form.append('file', file);
            form.append('fileId', $scope.fileId);

            if (isModify && (file == null || file == '')){
                return true;
            }

            if (file == null || file == '') {
                //alert("请选择文件");
                $scope.showAlert("提示","请选择文件");
                return false;
            }else if ($scope.selectJournalParams.title == '' || $scope.selectJournalParams.content == ''
                || $scope.selectJournalParams.weather == '' || $scope.selectJournalParams.temperature == '') {
                $scope.showAlert("提示","请输入信息");
                return false;
            }else {

                $scope.showLoading();

                $http({
                    method: 'POST',
                    url: url,
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).success(function (data) {
                    //alert("上传成功");
                    $ionicLoading.hide();
                    console.log("上传成功" + file);
                }).error(function (data) {
                    $ionicLoading.hide();
                    console.log('operation fail' + data);
                });
                return true;
            }

        };

        $scope.editJournal = function (){

            if ($scope.selectJournalParams.journalId != '' && $scope.selectJournalParams.journalId != null){
                if ($scope.insert('file/insertImage',true)) {
                    $scope.updateJournal();
                }
            }else {
                if ($scope.insert('file/insertImage',false)) {
                    $scope.insertJournal();
                }
            }
        }

        $scope.updateJournal = function (){
            $http({
                method: "POST",
                url: 'journal/updateByPrimaryKeySelective',
                params: {
                    journalId: $scope.selectJournalParams.journalId,
                    title: $scope.selectJournalParams.title,
                    content: $scope.selectJournalParams.content,
                    weather: $scope.selectJournalParams.weather,
                    temperature : $scope.selectJournalParams.temperature,
                    fileId : $scope.fileId,
                },
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("修改日记","修改成功");
                $state.go('main.journal',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
                console.log(response.data);
            });
        }

        $scope.insertJournal = function (){
            $http({
                method: "POST",
                url: 'journal/insert',
                params: {
                    journalId: $scope.genUUID(),
                    title: $scope.selectJournalParams.title,
                    content: $scope.selectJournalParams.content,
                    weather: $scope.selectJournalParams.weather,
                    temperature : $scope.selectJournalParams.temperature,
                    fileId : $scope.fileId,
                },
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("添加日记","添加成功");
                $state.go('main.journal',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
                console.log(response.data);
            });
        }

        $scope.loadData = function () {
            if ($scope.selectJournalParams.journalId != '' && $scope.selectJournalParams.journalId != null){
                $scope.selectJournalById();
            }
        }

        $scope.loadData();

    });