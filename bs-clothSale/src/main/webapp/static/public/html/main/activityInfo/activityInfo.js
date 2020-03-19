angular.module("clothSalePublicApp")
    .controller("activityInfoCtrl", function ($scope, $rootScope,$http,$stateParams,$state) {

        $scope.selectParamsActivitySpu = {
            pageIndex: 0,
            pageSize: 40,
            pageNum: 1,
            totalNum: 0,
            activity_id : $stateParams.activity_id == undefined ? '' : $stateParams.activity_id,
            activity_name : $stateParams.activity_name == undefined ? '' : $stateParams.activity_name,
            goods_name : '',
        }
        $scope.activitySpuList = [];

        //查询活动商品
        $scope.selectActivitySpu = function (){

            $http({
                method: "POST",
                url: '../../ActivityInfo/selectSpuOfActivity',
                params: $scope.selectParamsActivitySpu
            }).then(function successCallback(response) {
                //请求成功
                $scope.activitySpuList = response.data.item;
                $scope.selectParamsActivitySpu.totalNum = response.data.extdata == null ? 0 : response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:',response.data.msg,'danger');
            });

        }

        $scope.loadData = function () {
            $scope.selectParamsActivitySpu.pageIndex = ($scope.selectParamsActivitySpu.pageNum - 1) * $scope.selectParamsActivitySpu.pageSize;
            $scope.selectActivitySpu();
        }

        $scope.loadData();


    });