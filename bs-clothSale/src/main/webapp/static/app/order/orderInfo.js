angular.module("clothSaleApp")
    .controller("orderInfoCtrl", function ($scope, $rootScope,$state,$http) {

        $scope.orderInfoList = null;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            order_state : '0',
            user_id : '',
            user_name : '',
        }

        $scope.changeOrderState = function (order_state) {
            $scope.selectParams.order_state = order_state;
            $scope.loadData();
        }

        $scope.selectAllOrderInfo = function () {

            $http({
                method: "POST",
                url: '../../OrderInfo/selectAllOrderInfoByUserId',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.orderInfoList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata == null ? 0 : response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });

        }

        //发货
        $scope.orderSend = function (order_info_id){
            $http({
                method: "POST",
                url: '../../OrderInfo/updateOrderInfo',
                params: {
                    order_info_id : order_info_id,
                    order_state : 3
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
                $scope.showAlert('成功:','发货成功','success');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('失败:',response.data.msg,'danger');
            });
        }

        //退货
        $scope.orderReturn = function (order_info_id,sku_id,total_num){
            $http({
                method: "POST",
                url: '../../OrderInfo/updateOrderInfo',
                params: {
                    order_info_id : order_info_id,
                    order_state : 7
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.changeGoodsStock(sku_id,total_num);
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('失败:',response.data.msg,'danger');
            });
        }

        //修改退货后库存
        $scope.changeGoodsStock = function (sku_id,total_num){
            $http({
                method: "POST",
                url: '../../GoodsSku/updateGoodsSku',
                params: {
                    sku_id : sku_id,
                    stock : total_num
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
                $scope.showAlert('成功:','退货成功','success');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('失败:',response.data.msg,'danger');
            });
        }

        $scope.searchOrderInfo = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectAllOrderInfo();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllOrderInfo();
        }

        $scope.loadData();

    });