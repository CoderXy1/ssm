angular.module("clothSalePublicApp")
    .controller("searchCtrl", function ($scope, $rootScope,$http,$stateParams,$state) {

        $scope.brandList = [];
        $scope.categoryList = [];
        $scope.spuSearchList = [];
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 20,
            goods_name : $stateParams.goods_name == undefined ? '' : $stateParams.goods_name,
            category_id : $stateParams.category_id == undefined ? '' : $stateParams.category_id,
            brand_id : $stateParams.brand_id == undefined ? '' : $stateParams.brand_id,
        }

        //查询品牌
        $scope.selectBrand = function (){
            $http({
                method: "POST",
                url: '../../GoodsBrand/selectGoodsBrand',
                params : {
                    pageIndex : 0,
                    pageSize : 10,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.brandList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询一级分类
        $scope.selectFirstCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params : {
                    pageIndex : 0,
                    pageSize : 18,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询服饰
        $scope.selectGoodsSpu = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpu',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuSearchList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //清除品牌
        $scope.clearBrand = function (){
            $scope.selectParams.brand_id = '';
            $scope.loadData();
        }

        //清除分类
        $scope.clearCategory = function (){
            $scope.selectParams.category_id = '';
            $scope.loadData();
        }

        $scope.loadData = function () {
            $scope.selectBrand();
            $scope.selectFirstCategory();
            $scope.selectGoodsSpu();
        }

        $scope.loadData();


    });