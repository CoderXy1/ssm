angular.module("clothSaleApp")
    .controller("goodsSpuCtrl", function ($scope,$rootScope,$http,$alert) {

        $scope.spuList = [];
        $scope.categoryList = [];
        $scope.brandList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            goods_name : '',
            brand_id : '',
            category_id : '',
        }
        $scope.editSpu = {
            spuId: '',
            goodsName: '',
            lowPrice: '',
            spuIconId: '',
            brandId: '',
            categoryId: '',
            spuOrder: '',
        }

        //删除服饰
        $scope.deleteSpu = function (spu_id){

            if (confirm("将会删除该服饰下的所有库存,确定删除")){
                $http({
                    method: "POST",
                    url: '../../GoodsSpu/deleteGoodsSpu',
                    params: {
                        spu_id : spu_id,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:',response.data.msg,'success');
                    $scope.loadData();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('警告:',response.data.msg,'danger');
                });
            }

        }

        //查询spu
        $scope.selectGoodsSpu = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpu',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata==null?0:response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询单个spu
        $scope.selectSingleGoodsSpu = function (spu_id) {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectByPrimaryKey',
                params: {
                    spu_id : spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.editSpu = response.data.item;
                $('#addModal').modal('show');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        //修改服装
        $scope.updateGoodsSpu = function (){

            $http({
                method: "POST",
                url: '../../GoodsSpu/updateGoodsSpu',
                params: $scope.editSpu
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:',response.data.msg,'success');
                $scope.loadData();
                $('#addModal').modal('hide');
            }, function errorCallback(response) {
                //请求失败
            });

        }

        //查询分类
        $scope.selectCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params : {
                    pageIndex : 0,
                    pageSize : 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询品牌
        $scope.selectBrand = function (){
            $http({
                method: "POST",
                url: '../../GoodsBrand/selectGoodsBrand',
                params : {
                    pageIndex : 0,
                    pageSize : 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.brandList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchSpec = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectGoodsSpu();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectGoodsSpu();
            $scope.selectCategory();
            $scope.selectBrand();
        }

        $scope.loadData();

    });