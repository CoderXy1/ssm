angular.module('ionicApp')

    .controller('noteCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http,$ionicPopup) {

        $scope.noteList = [];

        $scope.selectNote = function (){

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'note/selectAll',
                params: {
                    pageIndex : 0,
                    pageSize : 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.noteList = response.data;
                $ionicLoading.hide();
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
            });
        }

        $scope.loadData = function () {

            $scope.selectNote();

        }

        //点击对话框外面关闭对话框
        var htmlEl = angular.element(document.querySelector('html'));
        htmlEl.on('click', function (event) {
            if (event.target.nodeName === 'HTML') {
                if ($scope.alertMenuPopup){
                    $scope.alertMenuPopup.close();
                }
            }
        });

        $scope.showNoteMenu = function (noteId,noteTitle){
            $scope.deleteNoteId = noteId;
            $scope.alertMenuPopup = $ionicPopup.show({
                template:
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-assertive" ng-click="deleteNote(deleteNoteId)">删除</button></div>' +
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-dark" ng-click="closePopup()">取消</button></div>',
                title: noteTitle,
                scope: $scope,
            });
        }

        $scope.closePopup = function (){
            $scope.alertMenuPopup.close();
        }

        $scope.deleteNote = function (noteId){

            $http({
                method: "POST",
                url: 'note/deleteNote',
                params: {
                    noteId : noteId
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("删除成功","");
                $scope.alertMenuPopup.close();
                $scope.loadData();
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert("删除失败","");
                $scope.alertMenuPopup.close();
            });

        }

        $scope.onRefresh = function (){

            $http({
                method: "POST",
                url: 'note/selectAll',
                params: {
                    pageIndex : 0,
                    pageSize : 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.noteList = response.data;
                $scope.$broadcast('scroll.refreshComplete');
            }, function errorCallback(response) {
                //请求失败
                $scope.$broadcast('scroll.refreshComplete');
            });

        }

        $scope.loadData();

    });