angular.module('ionicApp', ['ionic'])

    .controller('videoCtrl', function ($scope, $timeout, $ionicLoading,$ionicPopup, $http,$sce) {

        $scope.videoList = [];
        $scope.isLoadMore = true;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 5,
        };

        $scope.selectVideo = function () {

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'video/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.videoList = response.data;
                $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                $ionicLoading.hide();
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
            });
        }

        //下拉刷新
        $scope.onRefresh = function (){

            $scope.showLoading();
            $scope.selectParams.pageIndex = 0;

            $http({
                method: "POST",
                url: 'video/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.videoList = response.data;
                $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                $scope.isLoadMore = true;
                $ionicLoading.hide();
                $scope.$broadcast('scroll.refreshComplete');
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
                $scope.$broadcast('scroll.refreshComplete');
                $scope.isLoadMore = true;
            });

        }

        //上拉刷新
        $scope.loadMore = function () {
            $http({
                method: "POST",
                url: 'video/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == '') {
                    $scope.showAlert("提示", "没有更多数据了");
                    $scope.isLoadMore = false;
                } else {
                    angular.forEach(response.data, function (item, index, array) {
                        $scope.videoList.push(item);
                    })
                    $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }, function errorCallback(response) {
                //请求失败
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }

        //弹出菜单
        $scope.alertMenu = function (videoId,title,fileId) {
            $scope.deleteVideoId = videoId;
            $scope.downFileId = fileId;
            $scope.alertMenuPopup = $ionicPopup.show({
                template:
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-assertive" ng-click="deleteVideo(deleteVideoId,downFileId)">删除</button></div>',
                title: title,
                scope: $scope,
            });
        }

        $scope.deleteVideo = function (videoId,fileId){
            if (confirm("是否删除")){
                $http({
                    method: "POST",
                    url: 'video/deleteVideo',
                    params: {
                        videoId : videoId,
                        fileId : fileId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.videoList = [];
                    $scope.selectParams.pageIndex = 0;
                    $scope.loadData();
                    $scope.showAlert("删除成功",'');
                    $scope.isLoadMore = true;
                    $scope.alertMenuPopup.close();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert("删除失败",'');
                    $scope.alertMenuPopup.close();
                });
            }
        }

        $scope.videoUrl = function (url) {
            return $sce.trustAsResourceUrl(url);
        }

        $scope.loadData = function () {

            $scope.selectVideo();

        }

        $scope.loadData();

    });