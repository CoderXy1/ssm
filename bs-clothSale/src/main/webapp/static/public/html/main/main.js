angular.module("clothSalePublicApp")
    .controller("mainCtrl", function ($scope, $rootScope,$http) {

        $scope.categoryFirstList = [];
        $scope.showList = false;
        $scope.categoryList = [];
        $scope.spuList = [];
        $scope.tabsSign = 1;
        $scope.selectFirstParams = {
            pageIndex : 0,
            pageSize : 10,
        }
        $scope.changeTabs = function (num){
            $scope.tabsSign = num;
        }

        //查询分类
        $scope.selectCategory = function (category_first_id){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params : {
                    pageIndex : 0,
                    pageSize : 12,
                    category_first_id : category_first_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询一级分类
        $scope.selectFirstCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategoryFirst',
                params : $scope.selectFirstParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryFirstList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询spu
        $scope.selectGoodsSpu = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpu',
                params: {
                    pageIndex: 0,
                    pageSize: 5,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadData = function () {
            $scope.selectFirstCategory();
            $scope.selectGoodsSpu();
        }

        $scope.loadData();



    });