angular.module('ionicApp', ['ionic'])


    .controller('homeCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http,$ionicSlideBoxDelegate) {

        $scope.showLoading = function () {
            $ionicLoading.show({
                content: 'Loading',
                animation: 'fade-in',
                showBackdrop: true,
                template: '<ion-spinner icon="bubbles" class="spinner-balanced"></ion-spinner>',
                maxWidth: 200,
                showDelay: 0
            });
        }


        $scope.selectGallery = function () {

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'gallery/selectAll',
                params: {
                    pageIndex: 0,
                    pageSize: 5,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.galleryList = response.data;
                $ionicLoading.hide();
                $ionicSlideBoxDelegate.update(); //渲染之后更新
                $ionicSlideBoxDelegate.loop(true); //更新轮播
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
                $ionicSlideBoxDelegate.update();
            });
        }

        $scope.loadData = function () {

            $scope.selectGallery();

        }

        $scope.onRefresh = function () {
            console.log('ON REFRESH');

            $timeout(function () {
                $scope.$broadcast('scroll.refreshComplete');
            }, 1000);
        }

        $scope.loadData();

    });