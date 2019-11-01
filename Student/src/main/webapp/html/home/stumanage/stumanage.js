angular.module("studentApp", ['ui.bootstrap'])
    .controller("stumanageCtrl", function ($scope,$http, $state,$stateParams,$rootScope) {

        $rootScope.info.currentPage = 9;

        $scope.info = {
            depList:[],
            majorList:null,
        }

        $scope.selectParams = {
            stuId : "",
            stuName: "",
            sex: "",
            origo: "",
            depId: 0,
            majorName: "",
            className: "",
            grade : "",
            pageIndex: 0,
            pageSize: 10,
            pageNum : 1,
            totalNum : 0,
        }

        $scope.allStudents = [];

        $scope.edit = function (id){
            $state.go("editstu",{id:id})
        }

        $scope.delete = function (id){
            var msg = "确定删除当前学生？";
            if (confirm(msg)==true){
                $http({
                    method: "POST",
                    url: 'student/deleteByPrimaryKey',
                    params: {
                        stuid : id,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.loadData();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.tip = "请求失败，请重试";
                });
            }
        }

        $scope.selectMajorByDep = function (depId){
            if (depId != null && depId != ''){
                $http({
                    method: "POST",
                    url: 'major/selectMajorByDepId',
                    params: {
                        depId : depId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.info.majorList = response.data;
                }, function errorCallback(response) {
                    //请求失败
                    $scope.tip = "请求失败，请重试";
                });
            }
        }

        $scope.selectAllStuNum = function (){
            $http({
                method: "POST",
                url: 'student/selectStuNumBySearch',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectParams.totalNum = response.data;
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });
        }

        $scope.loadData = function () {

            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;

            if ($scope.selectParams.depId == null || $scope.selectParams.depId == ''){
                $scope.selectParams.depId = 0;
            }

            $http({
                method: "POST",
                url: 'student/selectStuByPage',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectAllStuNum();
                $scope.allStudents = response.data;
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });

            $http({
                method: "POST",
                url: 'department/selectAllDep',
            }).then(function successCallback(response) {
                //请求成功
                $scope.info.depList = response.data;
            }, function errorCallback(response) {
                //请求失败
                $scope.tip = "请求失败，请重试";
            });
        }

        $scope.loadData();

    });