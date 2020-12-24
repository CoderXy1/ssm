angular.module('ionicApp')

    .controller('videoEditCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup,$state,$stateParams,$sce) {

        $scope.selectVideoParams = {
            videoId : $stateParams.videoId == undefined?'':$stateParams.videoId,
            title : '',
            file : '',
        };

        $scope.selectVideoById = function () {

            $http({
                method: 'POST',
                url: 'video/selectSingle',
                params: {
                    videoId : $scope.selectVideoParams.videoId,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectVideoParams.title = response.data.title;
                $scope.selectVideoParams.content = response.data.content;
                $scope.selectVideoParams.weather = response.data.weather;
                $scope.selectVideoParams.temperature = response.data.temperature;
                $scope.selectVideoParams.file = response.data.file;
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
                $scope.fileId = null;
                return true;
            }

            if (file == null || file == '') {
                //alert("请选择文件");
                $scope.showAlert("提示","请选择文件");
                return false;
            } else if (file.size/1024/1024 > 100){
                $scope.showAlert("提示", "文件大小不能超过100M");
                return false;
            } else if ($scope.selectVideoParams.title == '') {
                $scope.showAlert("提示","请输入信息");
                return false;
            } else {

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
                    $scope.showAlert("添加视频","添加成功");
                    $state.go('video',{},{reload:true});
                    console.log("上传成功" + file);
                }).error(function (data) {
                    $ionicLoading.hide();
                    console.log('operation fail' + data);
                });
                return true;
            }

        };

        $scope.editVideo = function (){

            if ($scope.selectVideoParams.videoId != '' && $scope.selectVideoParams.videoId != null){
                if ($scope.insert('file/insertFile',true)) {
                    $scope.updateVideo();
                }
            }else {
                if ($scope.insert('file/insertFile',false)) {
                    $scope.insertVideo();
                }
            }
        }

        $scope.updateVideo = function (){
            $http({
                method: "POST",
                url: 'video/updateByPrimaryKeySelective',
                params: {
                    videoId: $scope.selectVideoParams.videoId,
                    title: $scope.selectVideoParams.title,
                    fileId : $scope.fileId,
                },
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("修改视频","修改成功");
                $state.go('video',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
                console.log(response.data);
            });
        }

        $scope.insertVideo = function (){
            $http({
                method: "POST",
                url: 'video/insert',
                params: {
                    videoId: $scope.genUUID(),
                    title: $scope.selectVideoParams.title,
                    fileId : $scope.fileId,
                },
            }).then(function successCallback(response) {
                //请求成功
                //$scope.showAlert("添加视频","添加成功");
                // $state.go('video',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
                console.log(response.data);
            });
        }

        $scope.videoUrl = function (url) {
            return $sce.trustAsResourceUrl(url);
        }

        $scope.loadData = function () {
            if ($scope.selectVideoParams.videoId != '' && $scope.selectVideoParams.videoId != null){
                $scope.selectVideoById();
            }
        }

        $scope.loadData();

    });