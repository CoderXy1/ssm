angular.module("studentApp", ['ui.bootstrap'])
    .controller("mainCtrl", function ($scope,$http,$rootScope) {

        $rootScope.info.currentPage = 1;
        $scope.tip = "";

        //查询选课
        $scope.getStucourseInfo = function () {

            $http({
                method: "POST",
                url: 'stucourse/selectStucourseByWeekAndPitch',
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == "") {
                    $scope.tip = "空";
                } else {
                    $scope.tip = response.data;
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }


    });