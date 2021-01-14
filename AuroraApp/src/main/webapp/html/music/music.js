angular.module('ionicApp')

    .controller('musicCtrl', function ($scope, $timeout, $ionicLoading,$ionicPopup, $http,$sce) {

        $scope.musicList = [];
        $scope.playingMusic = '';
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 999,
        };

        $scope.selectMusic = function () {

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'music/selectAll',
                params: $scope.selectParams,
                }).then(function successCallback(response) {
                //请求成功
                $scope.musicList = response.data;
                $ionicLoading.hide();
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
            });
        }

        //下拉刷新
        $scope.onRefresh = function (){

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'music/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.musicList = response.data;
                $ionicLoading.hide();
                $scope.$broadcast('scroll.refreshComplete');
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
                $scope.$broadcast('scroll.refreshComplete');
                $scope.isLoadMore = true;
            });

        }

        $scope.randomColor = function (){
            return '#' + Math.random().toString(16).substr(2, 6).toUpperCase();
        }

        $scope.playMusic = function (file){
            $scope.playingMusic = file;
            audio = document.getElementById('audio');
            audio.load();
            audio.play();
        }

        //弹出菜单
        $scope.alertMenu = function (musicId,title,fileId) {
            $scope.deleteMusicId = musicId;
            $scope.downFileId = fileId;
            $scope.alertMenuPopup = $ionicPopup.show({
                template:
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-assertive" ng-click="deleteMusic(deleteMusicId,downFileId)">删除</button></div>' +
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-dark" ng-click="closePopup()">取消</button></div>',
                title: title,
                scope: $scope,
            });
        }

        //关闭弹出菜单
        $scope.closePopup = function (){
            $scope.alertMenuPopup.close();
        }

        $scope.musicUrl = function (url) {
            return $sce.trustAsResourceUrl(url);
        }

        $scope.loadData = function () {

            $scope.selectMusic();
        }

        $scope.loadData();
    });