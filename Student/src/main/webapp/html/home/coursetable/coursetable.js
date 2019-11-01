angular.module("studentApp", ['ui.bootstrap'])
    .controller("coursetableCtrl", function ($scope,$http,$rootScope) {
        $scope.tip = "";
        $scope.allCourseInfo = '';
        $rootScope.info.currentPage = 6;

        //查询课表
        $scope.getStucourseInfo = function () {

            $http({
                method: "POST",
                url: 'stucourse/selectStucourseByWeekAndPitch',
                params : {
                    stuId : $scope.info.userInfo.userId,
                    term : '2019上学期',
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == "") {

                } else {
                    $scope.allCourseInfo = response.data;
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }
        $scope.getStucourseInfo();

    });