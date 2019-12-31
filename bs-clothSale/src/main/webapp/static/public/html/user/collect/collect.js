angular.module("clothSalePublicApp")
    .controller("orderCollectCtrl", function ($scope, $rootScope, $http) {

        $scope.collectList = null;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            user_id: $scope.getUserInfoBySession().user_id,
        }

        $scope.selectAllCollect = function () {
            $http({
                method: "POST",
                url: '../../OrderCart/selectOrderCollect',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.collectList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata == null ? 0 : response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.deleteCollect = function (collect_id) {

            if (confirm("确定删除该收藏")) {
                $http({
                    method: "POST",
                    url: '../../OrderCart/deleteOrderCollect',
                    params: {
                        collect_id: collect_id
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                    $scope.showAlert('成功:', response.data.msg, 'success');
                }, function errorCallback(response) {
                    //请求失败
                });
            }

        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectAllCollect();
        }

        $scope.loadData();
    });