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