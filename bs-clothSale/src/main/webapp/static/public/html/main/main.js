angular.module("clothSalePublicApp")
    .controller("mainCtrl", function ($scope, $rootScope,$http) {

        $scope.categoryList = [];
        $scope.spuList = [];
        $scope.categoryStep = 1;
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 12,
            categoryName : '',
        }

        //查询分类
        $scope.selectCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询商品
        $scope.selectGoodsSpu = function (category_id) {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpu',
                params: {
                    pageIndex : 0,
                    pageSize : 10,
                    category_id : category_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadData = function () {
            $scope.selectCategory();
        }

        $scope.loadData();



    });