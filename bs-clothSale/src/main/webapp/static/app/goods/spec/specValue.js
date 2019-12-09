angular.module("clothSaleApp")
    .controller("goodsSpecValueCtrl", function ($scope,$rootScope,$http,$alert,$stateParams) {

        $scope.spec_name = $stateParams.spec_name;

        $scope.specValueList = [];
        $scope.categoryList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            spec_id : $stateParams.spec_id,
            spec_value : ''
        }

        //新增规格值
        $scope.insertSpecValue = function (){
            $http({
                method: "POST",
                url: '../../GoodsSpec/insertGoodsSpecValue',
                params: {
                    spec_value_id : $scope.getUUID(),
                    spec_id : $scope.selectParams.spec_id,
                    spec_value : $scope.editSpec_value,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
                $('#addModal').modal('hide');
                $scope.showAlert('提示:',response.data.msg,'success');
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.deleteGoodsSpecValue = function (spec_value_id){
            if (confirm("是否删除该规格值")){
                $http({
                    method: "POST",
                    url: '../../GoodsSpec/deleteGoodsSpecValue',
                    params: {
                        spec_value_id : spec_value_id,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('提示:',response.data.msg,'success');
                }, function errorCallback(response) {
                    //请求失败
                });
            }
        }

        //查询规格值
        $scope.selectSpecValue = function (){
            $http({
                method: "POST",
                url: '../../GoodsSpec/selectGoodsSpecValue',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.specValueList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchSpec = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectSpecValue();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectSpecValue();
        }

        $scope.loadData();

    });