angular.module("clothSaleApp")
    .controller("activityInfoCtrl", function ($scope, $rootScope,$state,$http,$filter) {

        $scope.activityInfoList = null;
        $scope.selectParamsActivity = {
            pageIndex: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            activity_state : '-1',
            activity_name : '',
        }
        $scope.insertParamsActivity = {
            activity_id : '',
            activity_name : '',
            activity_describe : '',
            activity_date_begin : '',
            activity_date_end : '',
        }
        $scope.editParamsActivity = {
            activityId : '',
            activityName : '',
            activityDescribe : '',
            activityDateBegin : '',
            activityDateEnd : '',
        }

        $scope.selectActivityInfo = function (){

            $http({
                method: "POST",
                url: '../../ActivityInfo/selectActivityInfo',
                params: $scope.selectParamsActivity
            }).then(function successCallback(response) {
                //请求成功
                $scope.activityInfoList = response.data.item;
                $scope.selectParamsActivity.totalNum = response.data.extdata == null ? 0 : response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:',response.data.msg,'danger');
            });

        }

        $scope.insertActivity = function (){

            $scope.now = new Date();
            if ($scope.insertParamsActivity.activity_date_end <= $scope.insertParamsActivity.activity_date_begin || $scope.insertParamsActivity.activity_date_end <= $scope.now){
                $scope.showAlert('提示:','请正确的选择日期','danger');
            }else {
                $scope.insertParamsActivity.activity_id = $scope.getUUID();
                $http({
                    method: "POST",
                    url: '../../ActivityInfo/insertSelective',
                    params: $scope.insertParamsActivity
                }).then(function successCallback(response) {
                    //请求成功
                    if (response.data.item == 1){
                        $scope.showAlert('提示:',response.data.msg,'success');
                        $scope.loadData();
                        $('#addModal').modal('hide');
                    }
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误:',response.data.msg,'danger');
                });
            }

        }

        $scope.editActivity = function (activity_id){
            $http({
                method: "POST",
                url: '../../ActivityInfo/selectSingleActivityInfo',
                params: {
                    activity_id : activity_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.editParamsActivity = response.data.item;
                $scope.editParamsActivity.activityDateBegin = new Date($filter("date")($scope.editParamsActivity.activityDateBegin, "yyyy-MM-dd"));
                $scope.editParamsActivity.activityDateEnd = new Date($filter("date")($scope.editParamsActivity.activityDateEnd, "yyyy-MM-dd"));
                $('#editModal').modal('show');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:',response.data.msg,'danger');
            });
        }
        //修改活动
        $scope.updateActivity = function (){

            $scope.now = new Date();
            if ($scope.editParamsActivity.activityDateEnd <= $scope.editParamsActivity.activityDateBegin || $scope.editParamsActivity.activityDateEnd <= $scope.now){
                $scope.showAlert('提示:','请正确的选择日期','danger');
            }else {
                $http({
                    method: "POST",
                    url: '../../ActivityInfo/updateActivityInfo',
                    params: $scope.editParamsActivity
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('提示:',response.data.msg,'success');
                    $('#editModal').modal('hide');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误:',response.data.msg,'danger');
                });
            }
        }

        $scope.deleteActivity = function (activity_id){

            if (confirm("确认删除?")){
                $http({
                    method: "POST",
                    url: '../../ActivityInfo/deleteByPrimaryKey',
                    params: {
                        activity_id : activity_id
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    if (response.data.item == 1){
                        $scope.showAlert('提示:',response.data.msg,'success');
                        $scope.loadData();
                    }
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误:',response.data.msg,'danger');
                });
            }

        }

        $scope.loadData = function () {
            $scope.selectParamsActivity.pageIndex = ($scope.selectParamsActivity.pageNum - 1) * $scope.selectParamsActivity.pageSize;
            $scope.selectActivityInfo();
        }

        $scope.loadData();

    });