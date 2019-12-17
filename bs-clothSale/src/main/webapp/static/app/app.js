angular.module("clothSaleApp")
    .controller("appCtrl", function ($scope, $rootScope,$state) {

        $scope.admin_info = [];

        if (sessionStorage.getItem("token_admin")){
            $scope.admin_info = JSON.parse(sessionStorage.getItem("admin_info"))[0];
        }

        $scope.quitLogin = function () {
            sessionStorage.removeItem("token_admin")
            sessionStorage.removeItem("admin_info");
            $state.go('login');
        }

    });