"use strict";
angular.module('ionicApp', ['ionic', 'oc.lazyLoad'])

    .config(['$ionicConfigProvider', function ($ionicConfigProvider) {
        $ionicConfigProvider.tabs.position('bottom'); // other values: top
        $ionicConfigProvider.scrolling.jsScrolling(true);
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
        }).state('main.gallery', {
            url: '/gallery',
            views: {
                'tab-main-gallery': {
                    templateUrl: 'html/main/gallery/gallery.html',
                    controller: 'galleryCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/gallery/gallery.js']);
                }]
            }
        }).state('main.galleryEdit', {
            url: '/galleryEdit',
            views: {
                'tab-main-gallery': {
                    templateUrl: 'html/main/gallery/galleryEdit.html',
                    controller: 'galleryEditCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/gallery/galleryEdit.js']);
                }]
            }
        }).state('main.note', {
            url: '/note',
            views: {
                'tab-main-note': {
                    templateUrl: 'html/main/note/note.html',
                    controller: 'noteCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/note/note.js']);
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

    .controller('appCtrl', function ($scope,$ionicSideMenuDelegate,$ionicActionSheet,$state,$ionicPopup,$ionicLoading) {

        $scope.toggleLeftButton = function () {
            $ionicSideMenuDelegate.toggleLeft();
        };

        //对话框
        $scope.showAlert = function (title, msg) {
            $ionicPopup.alert({
                title: title,
                template: msg,
                buttons: [
                    {text:'确定',type:'button-positive'}
                ],
            });
        };

        //loading弹窗
        $scope.showLoading = function () {
            $ionicLoading.show({
                content: 'Loading',
                animation: 'fade-in',
                showBackdrop: true,
                template: '<ion-spinner icon="bubbles" class="spinner-balanced"></ion-spinner>',
                maxWidth: 200,
                showDelay: 0
            });
        }


        $scope.showBottomMenu = function() {

            $ionicActionSheet.show({
                buttons: [
                    { text: '<b> 相 册 </b>' },
                    { text: ' 日 记 ' },
                    { text: ' 便 签 ' },
                    { text: ' 未 定 ' }
                ],
                titleText: ' 新 增 ',
                cancelText: ' 取 消 ',
                cancel: function() {
                    // add cancel code..
                },
                buttonClicked: function(index) {
                    switch (index) {
                        case 0:
                            $state.go('main.galleryEdit');
                            break;
                        case 1:
                            $state.go('main.journal');
                            break;
                        case 2:
                            $state.go('main.gallery');
                            break;
                        case 3:
                            $state.go('main.gallery');
                            break;
                    }
                }
            });

            /*$timeout(function() {
                hideSheet();
            }, 2000);*/

        };

    });