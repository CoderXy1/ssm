angular.module("studentApp", ['ui.bootstrap'])
    .controller("electiveCtrl", function ($scope,$http,$rootScope) {

        $rootScope.info.currentPage = 2;
        $scope.allCourseInfo = "";
        $scope.tip = "";
        $scope.depList = [];
        $scope.teaList = [];
        //查询课程
        $scope.selectCourseInfo = {
            stuId : $rootScope.info.userInfo.userId,
            courseName : '',
            teaId : '',
            depId : 0,
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
        }
        $scope.getCourseInfo = function () {

            if ($scope.selectCourseInfo.depId == '' || $scope.selectCourseInfo.depId == undefined){
                $scope.selectCourseInfo.depId = 0;
            }

            $http({
                method: "POST",
                url: 'course/selectCourse',
                params: $scope.selectCourseInfo,
            }).then(function successCallback(response) {
                //请求成功
                $scope.allCourseInfo = response.data;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectCourseNum = function (){

            $http({
                method: "POST",
                url: 'course/selectCourseNum',
                params: $scope.selectCourseInfo,
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectCourseInfo.totalNum = response.data;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectTeaByDep = function (depId){

            if (depId == '' || depId == undefined){
                depId = 0;
            }

            $http({
                method: "POST",
                url: 'teacher/selectAllTeacher',
                params: {
                    depId :depId,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.teaList = response.data;
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

        }

        $scope.deleteStucourse = function (courseId,term){

            $http({
                method: "POST",
                url: 'stucourse/deleteStuCorse',
                params: {
                    stuId :$scope.selectCourseInfo.stuId,
                    courseId : courseId,
                    term : term,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

        }

        $scope.insertStucourse = function (courseId,term){

            $http({
                method: "POST",
                url: 'stucourse/insertStuCourse',
                params: {
                    stuId :$scope.selectCourseInfo.stuId,
                    courseId : courseId,
                    term : term,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

        }

        $scope.loadData = function(){

            $scope.selectCourseNum();

            $scope.selectCourseInfo.pageIndex = ($scope.selectCourseInfo.pageNum - 1) * $scope.selectCourseInfo.pageSize;

            $scope.getCourseInfo();

            $scope.selectTeaByDep($scope.selectCourseInfo.depId);

            $http({
                method: "POST",
                url: 'department/selectAllDep',
            }).then(function successCallback(response) {
                //请求成功
                $scope.depList = response.data;
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

        }

        $scope.loadData();


    });