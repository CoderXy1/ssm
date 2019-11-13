angular.module('ionicApp')

    .controller('galleryCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http,$ionicPopup) {

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
            }
        });

        $scope.showImgAlert = function (src,imgName){
            $scope.alertImgPopup = $ionicPopup.show({
                template: '<img ng-src="data:image/jpg;base64,' + src + '" style="object-fit: cover;width: 100%;border-radius: 3px">',
                title: imgName,
                scope: $scope,
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