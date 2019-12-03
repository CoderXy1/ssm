"use strict";
angular.module("clothSaleApp", ['ui.router', 'oc.lazyLoad','ui.bootstrap','mgcrea.ngStrap'])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/main");

        $stateProvider.state('main', {
            url: '/main',
            templateUrl: 'main/main.html',
            controller: 'mainCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['main/main.js']);
                }]
            }
        }).state('goodsBrand', {
            url: '/goodsBrand',
            templateUrl: 'goods/brand/brand.html',
            controller: 'goodsBrandCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/brand/brand.js']);
                }]
            }
        });
    }).controller("clothSaleCtrl", ['$scope', '$http','$rootScope','$alert',
    function ($scope, $http,$rootScope,$alert) {

        //生成随机字母数字
        $scope.genUUID = function () {
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = (d + Math.random() * 16) % 16 | 0;
                d = Math.floor(d / 16);
                return (c == 'x' ? r : (r & 0x7 | 0x8)).toString(16);
            });
            return uuid;
        }

        //提示信息
        $scope.showAlert = function (title,data) {
            $alert({title:title, content: data, placement: 'top', type: 'info', show: true, duration: 2});
        };

        $rootScope.info = {
            currentPage: 1,
        }

        //改变左侧菜单active状态
        $scope.changeActive = function (index) {
            $scope.info.currentPage = index;
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
