angular.module("clothSalePublicApp")
    .controller("shopCartCtrl", function ($scope, $rootScope,$http) {

        $scope.shopCartList = [];

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

        $scope.selectShopCart = function (){
            $http({
                method: "POST",
                url: '../../OrderCart/selectCartByUserId',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.shopCartList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata.total;
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