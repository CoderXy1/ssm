angular.module('ionicApp')

    .controller('noteEditCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup,$state,$stateParams) {

        $scope.selectNoteParams = {
            noteId : $stateParams.noteId == undefined?'':$stateParams.noteId,
            noteTitle : '',
            content : '',
            noteColor : '#30e3ca',
        };

        $scope.selectNoteById = function () {

            $http({
                method: 'POST',
                url: 'note/selectNoteById',
                params: {
                    noteId : $scope.selectNoteParams.noteId,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectNoteParams.noteTitle = response.data.notetitle;
                $scope.selectNoteParams.content = response.data.content;
                $scope.selectNoteParams.noteColor = response.data.notecolor;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.saveNote = function (noteId){
            $http({
                method: 'POST',
                url: 'note/saveNote',
                params: $scope.selectNoteParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("保存成功","");
                $state.go('main.note',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert("保存失败","");
            });
        }

        $scope.insertNote = function () {

            $scope.selectNoteParams.noteId = $scope.genUUID();

            $http({
                method: 'POST',
                url: 'note/insertNote',
                params: $scope.selectNoteParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("添加成功","");
                $state.go('main.note',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert("添加失败  ","");
            });

        }

        $scope.editNote = function (){

            if ($scope.selectNoteParams.noteId != '' && $scope.selectNoteParams.noteId != null){
                $scope.saveNote($scope.selectNoteParams.noteId);
            }else{
                $scope.insertNote();
            }

        }

        $scope.loadData = function () {
            if ($scope.selectNoteParams.noteId != '' && $scope.selectNoteParams.noteId != null){
                $scope.selectNoteById();
            }
        }

        $scope.loadData();

    });