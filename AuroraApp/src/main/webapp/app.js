angular.module('ionicApp', ['ionic', 'oc.lazyLoad'])

    .config(['$ionicConfigProvider', function ($ionicConfigProvider) {
        $ionicConfigProvider.tabs.position('bottom'); // other values: top
    }])

    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise('/home');

        $stateProvider.state('main', {
            url: '/main',
            views: {
                'tab-main': {
                    templateUrl: 'html/main/main.html',
                    controller: 'mainCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/main.js']);
                }]
            }
        }).state('main.journal', {
            url: '/journal',
            views: {
                'tab-main-journal': {
                    templateUrl: 'html/main/journal/journal.html',
                    controller: 'journalCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/journal/journal.js']);
                }]
            }
        }).state('home', {
            url: '/home',
            views: {
                'tab-home': {
                    templateUrl: 'html/home/home.html',
                    controller: 'homeCtrl',
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/home/home.js']);
                }]
            }
        }).state('menu', {
            url: '/menu',
            views: {
                'tab-menu': {
                    templateUrl: 'html/menu/menu.html',
                    controller: 'menuCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/menu/menu.js']);
                }]
            }
        }).state('test', {
            url: '/test',
            views: {
                'tab-test': {
                    templateUrl: 'html/test/test.html',
                    controller: 'testCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/test/test.js']);
                }]
            }
        })
    })

    .controller('appCtrl', function ($scope) {
        $scope.onControllerChanged = function (oldController, oldIndex, newController, newIndex, $ionicSideMenuDelegate) {

            $scope.toggleLeftButton = function () {
                $ionicSideMenuDelegate.toggleLeft();
            };


        };
    });