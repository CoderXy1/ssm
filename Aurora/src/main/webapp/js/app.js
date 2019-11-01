"use strict";
angular.module("auroraApp", ['ui.router', 'oc.lazyLoad'])
    .config(function ($stateProvider, $urlRouterProvider) {

        //$urlRouterProvider.otherwise("/test");

        // $stateProvider.state('test', {
        //     url: '/test',
        //     templateUrl: 'html/test/test.html',
        //     controller: 'testCtrl',
        //     resolve: {
        //         deps: ['$ocLazyLoad', function (e) {
        //             return e.load(['html/test/test.js']);
        //         }]
        //     }
        // });
    }).controller("auroraCtrl", ['$scope', '$http','$rootScope',
    function ($scope, $http,$rootScope) {

        $scope.journalList = [];
        $scope.galleryList = [];
        $scope.selectParms = {
            pageIndex : 0,
            pageSize : 3,
        }

        $scope.selectJournal = function (){

            $http({
                method: "POST",
                url: 'journal/selectAll',
                params: {
                    pageIndex : 0,
                    pageSize : 3,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.journalList = response.data;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectGallery = function (){

            $http({
                method: "POST",
                url: 'gallery/selectAll',
                params: {
                    pageIndex : 0,
                    pageSize : 9,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.galleryList = response.data;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadData = function () {

            $scope.selectJournal();
            $scope.selectGallery();
        }

        $scope.loadData();

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
