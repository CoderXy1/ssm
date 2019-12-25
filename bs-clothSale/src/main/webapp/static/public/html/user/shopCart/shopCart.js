angular.module("clothSalePublicApp")
    .controller("shopCartCtrl", function ($scope, $rootScope,$http) {

        $scope.shopCartList = [];
        $scope.totalPrice = 0;
        $scope.selectCartList = {};

        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            user_id : sessionStorage.getItem("token_user")?JSON.parse(sessionStorage.getItem("user_info"))[0].user_id:'',
        }

        $scope.deleteShopCart = function (cart_id){

            if (confirm("确定要从购物车删除该商品吗?")){
                $http({
                    method: "POST",
                    url: '../../OrderCart/deleteOrderCart',
                    params: {
                        cart_id : cart_id
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert("提示:",response.data.msg,'success');
                }, function errorCallback(response) {
                    //请求失败
                });
            }

        }

        $scope.addOrderCart = function (OrderCartSelected,price_sale,total_num,cart_id,goods_name){
            if (OrderCartSelected){
                $scope.totalPrice += price_sale * total_num;
                $scope.selectCartList[goods_name] = cart_id;
            }else {
                $scope.totalPrice -= price_sale * total_num;
                $scope.selectCartList[goods_name] = null;
            }
        }

        $scope.changeTotalNum = function(cart_id,total_num) {
            if (total_num <= 0 || total_num == null){
                $scope.showAlert('错误:','请输入正确的数值','danger');
            }else{
                $http({
                    method: "POST",
                    url: '../../OrderCart/updateOrderCart',
                    params: {
                        cart_id : cart_id,
                        total_num : total_num
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                }, function errorCallback(response) {
                    //请求失败
                });
            }
        }

        $scope.selectShopCart = function (){
            $http({
                method: "POST",
                url: '../../OrderCart/selectCartByUserId',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.shopCartList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata==null?0:response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectShopCart();
        }

        $scope.loadData();
    });