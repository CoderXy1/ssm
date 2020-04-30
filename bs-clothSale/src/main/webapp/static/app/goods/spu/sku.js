angular.module("clothSaleApp")
    .controller("goodsSkuCtrl", function ($scope,$rootScope,$http,$alert,$stateParams) {

        $scope.skuList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            spu_id : $stateParams.spu_id,
        }
        $scope.editSku = {
            sku_id : '',
            price_sale : '',
        }

        //删除规格
        $scope.deleteSku = function (sku_id){

            if (confirm("将会删除该服装,确定删除")){
                $http({
                    method: "POST",
                    url: '../../GoodsSku/deleteGoodsSku',
                    params: {
                        sku_id : sku_id,
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

        //查询单个sku
        $scope.selectSingleSku = function (sku_id,price_sale){
            $scope.editSku.sku_id = sku_id;
            $scope.editSku.price_sale = price_sale;
            $('#addModal').modal('show');
        }

        //修改sku
        $scope.updateGoodsSku = function (){
            $http({
                method: "POST",
                url: '../../GoodsSku/updateGoodsSkuPriceSale',
                params: $scope.editSku
                }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:',response.data.msg,'success');
                $scope.loadData();
                $('#addModal').modal('hide');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        //查询sku
        $scope.selectSku = function (){
            $http({
                method: "POST",
                url: '../../GoodsSku/selectAllSku',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.skuList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchSpec = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectSku();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectSku();
        }

        $scope.loadData();

    });