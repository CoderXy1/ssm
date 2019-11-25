angular.module('ionicApp')

    .controller('filesCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http) {

        $scope.fileList = [];

        $scope.selectParams = {
            fileName : '',
            fileType : '',
            pageIndex: 0,
            pageSize: 5,
        }

        $scope.selectFiles = function () {

            $http({
                method: "POST",
                url: 'file/selectAllFile',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.fileList = response.data;
            }, function errorCallback(response) {
                //请求失败
            });
        }




    });