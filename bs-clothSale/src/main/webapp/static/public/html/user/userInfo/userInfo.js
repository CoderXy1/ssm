angular.module("clothSalePublicApp")
    .controller("userInfoCtrl", function ($scope, $rootScope,$http) {

        $scope.user_info = [];
        $scope.selectParams = {
            user_id : $scope.getUserInfoBySession().user_id,
        }

        $scope.user_info = $scope.getUserInfoBySession();

        $scope.loadData = function () {

        }

        $scope.loadData();


    });