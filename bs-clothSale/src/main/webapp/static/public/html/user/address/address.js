angular.module("clothSalePublicApp")
    .controller("addressCtrl", function ($scope, $rootScope,$http) {

        $scope.user_info = [];
        $scope.memberAddressList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 5,
            pageNum : 1,
            totalNum : 0,
            user_id : '',
        }

        //是否登录
        if (sessionStorage.getItem("token_user")) {
            $scope.user_info = JSON.parse(sessionStorage.getItem("user_info"))[0];
        }

        $scope.selectAllAddress = function (){
            $http({
                method: "POST",
                url: '../../memberAddress/selectAllMemberAddress',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.memberAddressList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata==null?0:response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.deleteAddress = function (address_id){

            if (confirm("确认删除该收货地址")){
                $http({
                    method: "POST",
                    url: '../../memberAddress/deleteMemberAddress',
                    params : {
                        address_id : address_id
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:',response.data.msg,'success');
                }, function errorCallback(response) {
                    //请求失败
                });
            }
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllAddress();
        }

        $scope.loadData();


    });