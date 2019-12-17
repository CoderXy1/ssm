angular.module("clothSalePublicApp")
    .controller("publicCtrl", function ($scope, $rootScope,$state) {

        $scope.user_info = [];
        $scope.isLogin = sessionStorage.getItem("token_user");

        //是否登录
        if ($scope.isLogin) {
            $scope.user_info = JSON.parse(sessionStorage.getItem("user_info"))[0];
        }
        
        $scope.quitLogin = function () {
            sessionStorage.removeItem("token_user")
            sessionStorage.removeItem("user_info");
            $state.go('public.main',{},{reload:true});
        }


    });