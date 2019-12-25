angular.module("clothSaleApp")
    .controller("memberAdminCtrl", function ($scope, $rootScope,$state,$http) {

        $scope.memberAdminList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            admin_name : '',
        }

        $scope.selectAllAdmin = function (){
            $http({
                method: "POST",
                url: '../../memberAdmin/selectAllAdmin',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.memberAdminList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata==null?0:response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchAdmin = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectAllAdmin();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllAdmin();
        }

        $scope.loadData();

    });