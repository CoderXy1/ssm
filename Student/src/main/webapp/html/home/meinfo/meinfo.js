angular.module("studentApp", [])
    .controller("meinfoCtrl", function ($scope,$rootScope) {

        $rootScope.info.currentPage = 4;
        $scope.info_me = $rootScope.loginInfo;

    });