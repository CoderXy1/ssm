angular.module("studentApp", [])
    .controller("systemCtrl", function ($scope,$rootScope) {
        $rootScope.info.currentPage = 7;
        $scope.tip = "123456";
    });