angular.module("clothSaleApp")
    .controller("goodsBrandCtrl", function ($scope,$rootScope,$http,$alert) {

        $scope.brandList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            brandName : '',
        }

        //新增品牌
        $scope.insertBrand = function (){

            var file_id = $scope.getUUID();
            $scope.insertFile(file_id,'brand_icon_id','../../');

            $http({
                method: "POST",
                url: '../../GoodsBrand/insertGoodsBrand',
                params: {
                    brand_id : $scope.getUUID(),
                    brand_name : $scope.editBrand_name,
                    brand_order : 1,
                    brand_icon_id : file_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:',response.data.msg,'success');
                $scope.loadData();
                $('#addModal').modal('hide');
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //删除品牌
        $scope.deleteBrand = function (brand_id,file_id){

            if (confirm("将会同时删除该品牌下所有商品，确定删除")){
                $http({
                    method: "POST",
                    url: '../../GoodsBrand/deleteGoodsBrand',
                    params: {
                        brand_id : brand_id,
                        file_id : file_id,
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

        //查询品牌
        $scope.selectBrand = function (){
            $http({
                method: "POST",
                url: '../../GoodsBrand/selectGoodsBrand',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.brandList = response.data.item;
                if ($scope.brandList != null &&  $scope.brandList != ''){
                    $scope.selectParams.totalNum = $scope.brandList[0].total;
                }else {
                    $scope.selectParams.totalNum = 0;
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchBrand = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectBrand();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectBrand();
        }

        $scope.loadData();

    });