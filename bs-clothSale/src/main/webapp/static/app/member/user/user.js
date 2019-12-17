angular.module("clothSaleApp")
    .controller("memberUserCtrl", function ($scope, $rootScope,$state,$http) {

        $scope.memberUserList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            user_name : '',
        }

        $scope.selectAllUser = function (){
            $http({
                method: "POST",
                url: '../../memberUserinfo/selectAllUserinfo',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.memberUserList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchUser = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectAllUser();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllUser();
        }

        $scope.loadData();

    });