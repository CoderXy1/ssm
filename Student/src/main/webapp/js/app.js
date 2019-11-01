"use strict";
angular.module("studentApp", ['ui.router', 'oc.lazyLoad'])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/main");

        $stateProvider.state('main', {
            url: '/main',
            templateUrl: 'html/home/main/main.html',
            controller: 'mainCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/main/main.js']);
                }]
            }
        }).state('elective', {
            url: '/elective',
            templateUrl: 'html/home/elective/elective.html',
            controller: 'electiveCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/elective/elective.js']);
                }]
            }
        }).state('coursetable', {
            url: '/coursetable',
            templateUrl: 'html/home/coursetable/coursetable.html',
            controller: 'coursetableCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/coursetable/coursetable.js']);
                }]
            }
        }).state('meinfo', {
            url: '/meinfo',
            templateUrl: 'html/home/meinfo/meinfo.html',
            controller: 'meinfoCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/meinfo/meinfo.js']);
                }]
            }
        }).state('system', {
            url: '/system',
            templateUrl: 'html/home/system/system.html',
            controller: 'systemCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/system/system.js']);
                }]
            }
        }).state('stumanage', {
            url: '/stumanage',
            templateUrl: 'html/home/stumanage/stumanage.html',
            controller: 'stumanageCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/stumanage/stumanage.js']);
                }]
            }
        }).state('editstu', {
            url: '/editstu?id',
            templateUrl: 'html/home/stumanage/editstu.html',
            controller: 'editstuCtrl',
            resolve: {
                // 调用 ocLazyLoad
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/stumanage/editstu.js']);
                }]
            }
        }).state('test', {
            url: '/test',
            templateUrl: 'html/test/test.html',
            controller: 'testCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/test/test.js']);
                }]
            }
        });
    }).controller("studentCtrl", ['$scope', '$http','$rootScope',
    function ($scope, $http,$rootScope) {

        $rootScope.info = {
            userInfo: {
                userId: '',
                userName: '',
                userPassword: '',
                userPic: '',
                power : '',
            },
            currentPage: 1,
        }
        $rootScope.loginInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        switch ($rootScope.loginInfo.role) {
            case "student" :
                $rootScope.info.userInfo.userId = $scope.loginInfo.stuid;
                $rootScope.info.userInfo.userName = $scope.loginInfo.stuname;
                $rootScope.info.userInfo.userPassword = $scope.loginInfo.stupassword;
                break;
            case "teacher" :
                $rootScope.info.userInfo.userId = $scope.loginInfo.teaid;
                $rootScope.info.userInfo.userName = $scope.loginInfo.teaname;
                $rootScope.info.userInfo.userPassword = $scope.loginInfo.teapassword;
                break;
            case "admin" :
                $rootScope.info.userInfo.userId = $scope.loginInfo.adminid;
                $rootScope.info.userInfo.userName = $scope.loginInfo.adminname;
                $rootScope.info.userInfo.userPassword = $scope.loginInfo.adminpassword;
                break;
        }
        $rootScope.info.userInfo.userPic = $scope.loginInfo.picpath;
        $rootScope.info.userInfo.power = $scope.loginInfo.power;

        $scope.selectParams = {

            stuid: $rootScope.info.userInfo.stuid,
            stuname: '',
            stupassword: '',
            sex: '',
            birthday: null,
            birthplace: '',
            origo: '',
            idnumber: '',
            nation: '',
            nationality: '',
            phonenumber: '',
            address: '',
            grade: '',
            classname: '',
            majorid: 0,
            power: '',
            picpath: ''

        }

        $scope.changeActive = function (index) {
            $scope.info.currentPage = index;
        }

        $scope.quitLogin = function () {
            sessionStorage.setItem("isLogin", 'false');
            sessionStorage.setItem("userInfo", '{}');
            window.location.replace("login.html");
        }

        //修改头像
        $scope.updateStu = function () {

            $http({
                method: "POST",
                url: 'student/updateByStuId',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == 1) {
                    $scope.selectuserInfo($scope.info.userInfo.stuid, $scope.info.userInfo.stupassword);
                }
            }, function errorCallback(response) {
                //请求失败
                //$scope.tip = "请求失败，请重试";
            });
        }

        //获取学生信息
        $scope.selectuserInfo = function (stuid, stuPass) {

            $http({
                method: "POST",
                url: 'student/selectByIdAndPw',
                params: {
                    stuid: stuid,
                    stuPassword: stuPass,
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == "") {

                } else {
                    sessionStorage.setItem("userInfo", JSON.stringify(response.data));
                    $scope.info.userInfo = response.data;
                    $('#picModal').modal('hide');
                }
            }, function errorCallback(response) {
                //请求失败
                //$scope.tip = "请求失败，请重试";
            });
        }
    }
]).factory('httpInterceptor', ["$rootScope", function ($rootScope) {
    //设置加载时httpProvider请求和返回的加载状态
    var httpInterceptor = {
        request: function (config) {
            //start 开始加载
            $rootScope.loading = true;
            return config;
        },
        response: function (response) {
            //end 结束加载
            $rootScope.loading = false;
            return response;
        },
        requestError:function(rejection){
            $rootScope.loading = false;
            return rejection;
        },
        responseError:function(rejection){
            $rootScope.loading = false;
            return rejection;
        }
    };
    return httpInterceptor;
}]).directive('loading', function(){
    return {
        restrict: 'E',
        transclude: true,
        template: '<div ng-show="loading" class="loading" id="allDiv"  style="position:fixed; top:0px; left:0px; width:100%; height:100%; display:none; background-color:#212121; opacity: 0.5; z-index:99999;">'
            +'<img alt="" src="img/loading.gif" style="vertical-align: middle;width:200px; height:200px; position: absolute; top:50%; left:50%; margin-top: -100px; margin-left:-100px;object-fit: cover"/></div>',
        link: function (scope, element, attr) {
            scope.$watch('loading', function (val) {
                if (val){
                    document.getElementById("allDiv").style.display = "block";
                }else{
                    document.getElementById("allDiv").style.display = 'none';
                }
            });
        }
    }
}).directive('tooltip',function () {
    return{
        restrict:'A',
        link:function (scope, element, attrs) {
            $(element).hover(function () {
                $(element).tooltip('show');
            },function () {
                $(element).tooltip('hide')
            })
        }
    }
}).config(["$httpProvider", function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);
