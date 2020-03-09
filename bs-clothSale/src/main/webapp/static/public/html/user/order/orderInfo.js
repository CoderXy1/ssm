angular.module("clothSalePublicApp")
    .controller("orderInfoCtrl", function ($scope, $rootScope, $http) {

        $scope.orderInfoList = null;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 3,
            pageNum: 1,
            totalNum: 0,
            order_state: 0,
            user_id: $scope.getUserInfoBySession().user_id,
        }
        $scope.selectParams_comment = {
            comment_id: '',
            order_info_id: '',
            user_id: '',
            sku_id: '',
            comment_content: '',
            comment_score: '',
        }
        $scope.payGoodsParams = {
            order_info_id: '',
            pay_way: 1,
            order_state: 2
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

        //退货
        $scope.orderReturn = function (order_info_id) {

            if (confirm("确认需要退货?")) {
                $http({
                    method: "POST",
                    url: '../../OrderInfo/updateOrderInfo',
                    params: {
                        order_info_id: order_info_id,
                        order_state: 6
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('成功:', '请等待退货', 'success');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('失败:', response.data.msg, 'danger');
                });
            }

        }

        //取消退货
        $scope.orderReturnCancel = function (order_info_id) {
            if (confirm("确认取消退货?")) {
                $http({
                    method: "POST",
                    url: '../../OrderInfo/updateOrderInfo',
                    params: {
                        order_info_id: order_info_id,
                        order_state: 2
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('成功:', '取消退货成功', 'success');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('失败:', response.data.msg, 'danger');
                });
            }
        }

        //收货
        $scope.orderSend = function (order_info_id, user_id, sku_id) {
            if (confirm("确认收货?")){
                $http({
                    method: "POST",
                    url: '../../OrderInfo/updateOrderInfo',
                    params: {
                        order_info_id : order_info_id,
                        order_state : 4
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('成功:','取消退货成功','success');
                    $scope.selectParams_comment.order_info_id = order_info_id;
                    $scope.selectParams_comment.user_id = user_id;
                    $scope.selectParams_comment.sku_id = sku_id;
                    $('#commentModal').modal('show');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('失败:',response.data.msg,'danger');
                });
            }
        }

        //添加评论
        $scope.insertComment = function () {

            $scope.selectParams_comment.comment_id = $scope.getUUID();
            //console.log($scope.selectParams_comment);

            $http({
                method: "POST",
                url: '../../OrderComment/insertOrderComment',
                params: $scope.selectParams_comment
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
                $('#commentModal').modal('hide');
                $scope.showAlert('成功:',response.data.msg,'success');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('失败:', response.data.msg, 'danger');
            });


        }

        //显示付款窗口
        $scope.orderPay = function (order_info_id) {
            $scope.payGoodsParams.order_info_id = order_info_id;
            $('#payModal').modal('show');
        }

        //付款
        $scope.payGoods = function () {
            $http({
                method: "POST",
                url: '../../OrderInfo/updateOrderInfo',
                params: $scope.payGoodsParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示', '付款成功，请等待发货', 'success');
                $('#payModal').modal('hide');
                $scope.loadData();
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误', response.data.msg, 'danger');
            });
        }

        //删除订单
        $scope.orderDelete = function (order_info_id) {
            if (confirm("确认删除订单?")) {
                $http({
                    method: "POST",
                    url: '../../OrderInfo/updateOrderInfo',
                    params: {
                        order_info_id: order_info_id,
                        order_state: 5
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('成功:', '删除成功', 'success');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('失败:', response.data.msg, 'danger');
                });
            }
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllOrderInfo();
        }

        $scope.loadData();

    });