angular.module("testApp")
    .controller("mainCtrl", function ($scope, $rootScope, $http) {

        $scope.filePath = "";

        $scope.getFile = function () {
            $http({
                method: "POST",
                url: 'file/getFile',
                params: {
                    fileId: $scope.fileId
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.filePath = response.data.file;
            }, function errorCallback(response) {
                //请求失败
            });
        }
    });