angular.module('myApp', [])
    .controller('myCtrl', function ($scope, $http, $timeout, $interval) {

        $scope.brandList = [];
        $scope.specList = [];
        $scope.categoryList = [];
        $scope.specIds = {};
        $scope.specValueList = [];
        $scope.selectParms = {
            pageIndex : 0,
            pageSize : 10,
            brandName : '',
            categoryName : '',
            brand_id : '',
            category_id : '',
        }

        //生成随机字母数字
        $scope.genUUID = function () {
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = (d + Math.random() * 16) % 16 | 0;
                d = Math.floor(d / 16);
                return (c == 'x' ? r : (r & 0x7 | 0x8)).toString(16);
            });
            return uuid;
        }

        $scope.selectBrand = function (){
            $http({
                method: "POST",
                url: 'GoodsBrand/selectGoodsBrand',
                params : $scope.selectParms
            }).then(function successCallback(response) {
                //请求成功
                $scope.brandList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectCategory = function (){
            $http({
                method: "POST",
                url: 'GoodsCategory/selectGoodsCategory',
                params : $scope.selectParms
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectGoodsSpu = function () {
            $http({
                method: "POST",
                url: 'GoodsSpu/selectGoodsSpu',
                params: $scope.selectParms
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.addGoodsSpec = function (isCheck,specId) {

            $scope.specIds[specId] = isCheck;

        }

        $scope.selectAllSpec = function (category_id){

            $scope.specIds = {};

            $http({
                method: "POST",
                url: 'GoodsSpec/selectAllGoodsSpec',
                params: {
                    category_id : category_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.specList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertGoodsSpu = function (){
            $http({
                method: "POST",
                url: 'GoodsSpu/insertGoodsSpu',
                params: {
                    spu_id : $scope.genUUID(),
                    goods_name : $scope.goods_name,
                    low_price : $scope.low_price,
                    spu_icon_id : '123456789',
                    brand_id : $scope.selectParms.brand_id,
                    category_id : $scope.selectParms.category_id,
                    spu_order : 1,
                    specIds : $scope.specIds
                }
            }).then(function successCallback(response) {
                //请求成功
               console.log(response.data.item);
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertBrand = function (){
            $http({
                method: "POST",
                url: 'GoodsBrand/insertGoodsBrand',
                params: {
                    brand_id : $scope.genUUID(),
                    brand_name : $scope.brand_name,
                    brand_order : 1,
                    brand_icon_id : 'XXX',
                }
            }).then(function successCallback(response) {
                //请求成功
                console.log(response.data.item);
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertCategory = function (){
            $http({
                method: "POST",
                url: 'GoodsCategory/insertGoodsCategory',
                params: {
                    category_id : $scope.genUUID(),
                    category_name : $scope.category_name,
                    category_order : 1,
                }
            }).then(function successCallback(response) {
                //请求成功
                console.log(response.data.item);
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertSpec = function (){
            $http({
                method: "POST",
                url: 'GoodsSpec/insertGoodsSpec',
                params: {
                    spec_id : $scope.genUUID(),
                    spec_name : $scope.spec_name,
                    category_id : $scope.selectParms.category_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                console.log(response.data.item);
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectSpecValue = function (spec_id){
            $http({
                method: "POST",
                url: 'GoodsSpec/selectGoodsSpecValue',
                params: {
                    spec_id : spec_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.specValueList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertSpecValue = function (spec_id,spec_value){
            $http({
                method: "POST",
                url: 'GoodsSpec/insertGoodsSpecValue',
                params: {
                    spec_value_id : $scope.genUUID(),
                    spec_id : spec_id,
                    spec_value : spec_value,
                }
            }).then(function successCallback(response) {
                //请求成功
                console.log(response.data.item);
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadDate = function () {
            $scope.selectCategory();
            $scope.selectBrand();
        }

        $scope.loadDate();

    })
