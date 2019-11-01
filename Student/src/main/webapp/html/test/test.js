angular.module("studentApp", [])
    .controller("testCtrl", function ($scope,$rootScope) {
        $scope.tip = "Test";
        $rootScope.info.currentPage = 3;
        $scope.loops = {
            1 : '',
            2 : '',
            3 : '',
            4 : '',
        }
    });