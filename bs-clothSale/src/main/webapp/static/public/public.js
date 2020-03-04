angular.module("clothSalePublicApp")
    .controller("publicCtrl", function ($scope, $rootScope,$state,$http) {

        $scope.user_info = [];
        $scope.isLogin = sessionStorage.getItem("token_user");
        $scope.categoryList = [];

        $scope.selectCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params : {
                    pageIndex : 0,
                    pageSize : 12,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //是否登录
        if ($scope.isLogin) {
            $scope.user_info = JSON.parse(sessionStorage.getItem("user_info"))[0];
        }
        
        $scope.quitLogin = function () {
            sessionStorage.removeItem("token_user")
            sessionStorage.removeItem("user_info");
            $state.go('public.main',{},{reload:true});
        }

        $scope.loadData = function () {
            $scope.selectCategory();
        }

        $scope.loadData();

    });