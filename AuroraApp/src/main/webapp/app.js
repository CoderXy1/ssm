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
        }).state('main.noteEdit', {
            params : { noteId : ''},
            url: '/noteEdit',
            views: {
                'tab-main-note': {
                    templateUrl: 'html/main/note/noteEdit.html',
                    controller: 'noteEditCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/note/noteEdit.js']);
                }]
            }
        }).state('main.files', {
            url: '/files',
            views: {
                'tab-main-files': {
                    templateUrl: 'html/main/files/files.html',
                    controller: 'filesCtrl'
                }
            },
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['html/main/files/files.js']);
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

    .controller('appCtrl', function ($scope,$ionicSideMenuDelegate,$ionicActionSheet,$state,$ionicPopup,$ionicLoading,$http) {

        $scope.toggleLeftButton = function () {
            $ionicSideMenuDelegate.toggleLeft();
        };

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

        //从数据库下载文件
        $scope.downloadFile = function (fileId) {

            $http({
                method: "POST",
                url: 'file/downloadFile',
                params: {
                    fileId: fileId,
                },
            }).then(function successCallback(response) {
                //请求成功
                download("data:text/plain;base64," + response.data.file,response.data.filename);
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert("下载失败","");
            });

        };
        //下载图片
        function  download(src,name) {
            var imgData =  src;//这里放需要下载的base64
            downloadFile(name, imgData);
        }
        //下载
        function downloadFile(fileName, content) {
            var aLink = document.createElement('a');
            var blob = base64ToBlob(content); //new Blob([content]);
            var evt = document.createEvent("HTMLEvents");
            evt.initEvent("click", true, true);//initEvent 不加后两个参数在FF下会报错  事件类型，是否冒泡，是否阻止浏览器的默认行为
            aLink.download = fileName;
            aLink.href = URL.createObjectURL(blob);
            // aLink.dispatchEvent(evt);
            aLink.click()
        }
        //base64转blob
        function base64ToBlob(code) {
            var parts = code.split(';base64,');
            var contentType = parts[0].split(':')[1];
            var raw = window.atob(parts[1]);
            var rawLength = raw.length;

            var uInt8Array = new Uint8Array(rawLength);

            for (var i = 0; i < rawLength; ++i) {
                uInt8Array[i] = raw.charCodeAt(i);
            }
            return new Blob([uInt8Array], {type: contentType});
        }

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
                            $state.go('main.noteEdit');
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