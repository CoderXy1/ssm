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

        $scope.user_info = $scope.getUserInfoBySession();

        $scope.changeAddressId = function(address_id){

            $http({
                method: "POST",
                url: '../../memberUserinfo/updateMemberUserinfo',
                params : {
                    user_id : $scope.user_info.user_id,
                    address_id : address_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.updateLocalUserInfo();
                $scope.showAlert('成功:',response.data.msg,'success');
            }, function errorCallback(response) {
                //请求失败
            });

        }

        $scope.updateLocalUserInfo = function (){
            $http({
                method: "POST",
                url: '../../memberUserinfo/selectUserinfoByUserId',
                params : {
                    user_id : $scope.user_info.user_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                sessionStorage.setItem("user_info", JSON.stringify(response.data.item)); //修改session默认地址信息
                $scope.user_info = $scope.getUserInfoBySession();
                $scope.loadData();
            }, function errorCallback(response) {
                //请求失败
            });
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
                    $scope.loadData();
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