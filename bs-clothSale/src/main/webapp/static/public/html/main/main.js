angular.module("clothSalePublicApp")
    .controller("mainCtrl", function ($scope, $rootScope,$http,$state) {

        $scope.categoryFirstList = [];
        $scope.showList = false;
        $scope.activityInfoList = [];
        $scope.activitySpuList = [];
        $scope.categoryList = [];
        $scope.spuList = [];
        $scope.spuList_today = [];
        $scope.tabsSign = 0;
        $scope.selectFirstParams = {
            pageIndex : 0,
            pageSize : 10,
        }
        $scope.changeTabs = function (num,activity_id){
            $scope.tabsSign = num;
            $scope.selectActivitySpu(activity_id);
        }

        //查询一级分类
        $scope.selectFirstCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategoryFirst',
                params : $scope.selectFirstParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryFirstList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询分类
        $scope.selectCategory = function (category_first_id){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params : {
                    pageIndex : 0,
                    pageSize : 12,
                    category_first_id : category_first_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询活动
        $scope.selectActivityInfo = function (){
            $http({
                method: "POST",
                url: '../../ActivityInfo/selectActivityInfo',
                params: {
                    pageIndex: 0,
                    pageSize: 4,
                    activity_state : 1,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.activityInfoList = response.data.item;
                $scope.selectActivitySpu($scope.activityInfoList[$scope.tabsSign].activity_id);
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:',response.data.msg,'danger');
            });
        }

        //查询活动商品
        $scope.selectActivitySpu = function (activity_id){

            $http({
                method: "POST",
                url: '../../ActivityInfo/selectSpuOfActivity',
                params: {
                    pageIndex: 0,
                    pageSize: 5,
                    activity_id : activity_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.activitySpuList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:',response.data.msg,'danger');
            });

        }

        //查询今日发现
        $scope.selectGoodsSpuToday = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpuRand',
                params: {
                    pageIndex: 0,
                    pageSize: 25,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuList_today = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadData = function () {
            $scope.selectFirstCategory();
            $scope.selectActivityInfo();
            $scope.selectGoodsSpuToday();
        }

        $scope.loadData();



    });