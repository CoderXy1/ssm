angular.module('ionicApp')

    .controller('journalCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http) {

        $scope.journalList = [];

        $scope.selectJournal = function (){

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'journal/selectAll',
                params: {
                    pageIndex : 0,
                    pageSize : 5,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.journalList = response.data;
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
                url: 'journal/selectAll',
                params: {
                    pageIndex : 0,
                    pageSize : 5,
                }
            }).then(function successCallback(response) {
                //请求成功
                angular.forEach(response.data,function (item,index,array) {
                    $scope.journalList.push(item);
                })
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }, function errorCallback(response) {
                //请求失败
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }

        //$scope.loadData();

    });