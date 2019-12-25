angular.module("clothSaleApp")
    .controller("memberAddressCtrl", function ($scope, $rootScope,$state,$http) {

        $scope.memberAddressList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            user_id : '',
            user_name : '',
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

        $scope.searchAddress = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectAllAddress();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllAddress();
        }

        $scope.loadData();

    });