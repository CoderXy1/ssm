angular.module("clothSaleApp")
    .controller("activitySpuCtrl", function ($scope, $rootScope,$state,$http,$stateParams) {

        $scope.activitySpuList = null;
        $scope.selectParamsActivitySpu = {
            pageIndex: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            activity_id : $stateParams.activity_id == undefined ? '' : $stateParams.activity_id,
            goods_name : '',
        }

        $scope.selectActivitySpu = function (){

            $http({
                method: "POST",
                url: '../../ActivityInfo/selectSpuAndActivity',
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

        $scope.changeActivitySpu = function (isActivity,spu_id){

            $scope.changeActivitySpuParams = {
                activity_id : $scope.selectParamsActivitySpu.activity_id,
                spu_id :spu_id
            }

            if (isActivity){
                //删除活动商品
                $http({
                    method: "POST",
                    url: '../../ActivityInfo/deleteActivitySpu',
                    params: $scope.changeActivitySpuParams
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('提示:',response.data.msg,'success');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误:',response.data.msg,'danger');
                });
            }else {
                //添加活动商品
                $http({
                    method: "POST",
                    url: '../../ActivityInfo/insertActivitySpu',
                    params: $scope.changeActivitySpuParams
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('提示:',response.data.msg,'success');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误:',response.data.msg,'danger');
                });
            }

        }

        $scope.loadData = function () {
            $scope.selectParamsActivitySpu.pageIndex = ($scope.selectParamsActivitySpu.pageNum - 1) * $scope.selectParamsActivitySpu.pageSize;
            $scope.selectActivitySpu();
        }

        $scope.loadData();

    });