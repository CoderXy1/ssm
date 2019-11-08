angular.module('ionicApp')

    .controller('galleryCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http,$ionicPopup) {

        $scope.galleryList = [];
        $scope.isLoadMore = true;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 12,
        };

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

        $scope.selectJournal = function () {

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

        $scope.loadData = function () {

            $scope.selectJournal();

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
                    $scope.showAlert("提示","没有更多数据了");
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