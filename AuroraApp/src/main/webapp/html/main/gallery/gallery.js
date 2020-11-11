angular.module('ionicApp')

    .controller('galleryCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup) {

        $scope.galleryList = [];
        $scope.isLoadMore = true;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 12,
        };

        var htmlEl = angular.element(document.querySelector('html'));
        htmlEl.on('click', function (event) {
            if (event.target.nodeName === 'HTML') {
                if ($scope.alertImgPopup) {
                    $scope.alertImgPopup.close();
                }
                if ($scope.alertMenuPopup){
                    $scope.alertMenuPopup.close();
                }
            }
        });

        $scope.showImgAlert = function (src, imgName) {
            $scope.alertImgPopup = $ionicPopup.show({
                template: '<img ng-src="' + src + '" style="object-fit: cover;width: 100%;border-radius: 3px">',
                title: imgName,
                scope: $scope,
            });
        }

        $scope.alertMenu = function (fileId,imgName,galleryId,src) {
            $scope.downFileId = fileId;
            $scope.deleteGalleryId = galleryId;
            $scope.src = src;
            $scope.alertMenuPopup = $ionicPopup.show({
                template:
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-positive" ng-click="originImage(src)">原图</button></div>' +
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-balanced" ng-click="showFile(downFileId)">下载</button></div>' +
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-assertive" ng-click="deleteGallery(deleteGalleryId,downFileId)">删除</button></div>',
                title: imgName,
                scope: $scope,
            });
        }

        $scope.originImage = function (src) {

            var link = document.createElement('a');
            link.href = src;
            link.target = "_blank";
            link.click();

        };

        $scope.showFile = function (fileId) {

            $scope.downloadFile(fileId);
            $scope.alertMenuPopup.close();

        };

        $scope.selectGallery = function () {

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'gallery/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.galleryList = response.data;
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
                url: 'gallery/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.galleryList = response.data;
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

        $scope.deleteGallery = function (galleryId,fileId) {

            if (confirm("是否删除")){
                $http({
                    method: "POST",
                    url: 'gallery/deleteGallery',
                    params: {
                        galleryId : galleryId,
                        fileId : fileId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.galleryList = [];
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

        $scope.loadData = function () {

            $scope.selectGallery();

        }

        //上拉刷新
        $scope.loadMore = function () {
            $http({
                method: "POST",
                url: 'gallery/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == '') {
                    $scope.showAlert("提示", "没有更多数据了");
                    $scope.isLoadMore = false;
                } else {
                    angular.forEach(response.data, function (item, index, array) {
                        $scope.galleryList.push(item);
                    })
                    $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }, function errorCallback(response) {
                //请求失败
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }

        $scope.loadData();

    });