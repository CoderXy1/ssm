"use strict";
angular.module("clothSaleApp", ['ui.router', 'oc.lazyLoad', 'ui.bootstrap', 'mgcrea.ngStrap'])
    .config(function ($stateProvider, $urlRouterProvider) {

        $urlRouterProvider.otherwise("/app/main");

        $stateProvider.state('login', {
            url: '/login',
            templateUrl: 'login.html',
            controller: 'loginCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['login.js']);
                }]
            }
        }).state('app', {
            url: '/app',
            templateUrl: 'app.html',
            controller: 'appCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['app.js']);
                }]
            }
        }).state('app.main', {
            url: '/main',
            templateUrl: 'main/main.html',
            controller: 'mainCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['main/main.js']);
                }]
            }
        }).state('app.goodsBrand', {
            url: '/goodsBrand',
            templateUrl: 'goods/brand/brand.html',
            controller: 'goodsBrandCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/brand/brand.js']);
                }]
            }
        }).state('app.goodsCategory', {
            url: '/goodsCategory',
            templateUrl: 'goods/category/category.html',
            controller: 'goodsCategoryCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/category/category.js']);
                }]
            }
        }).state('app.goodsSpec', {
            url: '/goodsSpec',
            templateUrl: 'goods/spec/spec.html',
            controller: 'goodsSpecCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/spec/spec.js']);
                }]
            }
        }).state('app.goodsSpecValue', {
            url: '/goodsSpecValue?spec_id&spec_name',
            templateUrl: 'goods/spec/specValue.html',
            controller: 'goodsSpecValueCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/spec/specValue.js']);
                }]
            }
        }).state('app.goodsSpu', {
            url: '/goodsSpu',
            templateUrl: 'goods/spu/spu.html',
            controller: 'goodsSpuCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/spu/spu.js']);
                }]
            }
        }).state('app.goodsSku', {
            url: '/goodsSku?spu_id',
            templateUrl: 'goods/spu/sku.html',
            controller: 'goodsSkuCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/spu/sku.js']);
                }]
            }
        }).state('app.goodsSkuAdd', {
            url: '/goodsSkuAdd',
            templateUrl: 'goods/spu/skuAdd.html',
            controller: 'goodsSkuAddCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['goods/spu/skuAdd.js']);
                }]
            }
        }).state('app.memberAdmin', {
            url: '/memberAdmin',
            templateUrl: 'member/admin/admin.html',
            controller: 'memberAdminCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['member/admin/admin.js']);
                }]
            }
        }).state('app.memberUser', {
            url: '/memberUser',
            templateUrl: 'member/user/user.html',
            controller: 'memberUserCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['member/user/user.js']);
                }]
            }
        }).state('app.memberAddress', {
            url: '/memberAddress',
            templateUrl: 'member/user/address/address.html',
            controller: 'memberAddressCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['member/user/address/address.js']);
                }]
            }
        }).state('app.orderInfo', {
            url: '/orderInfo',
            templateUrl: 'order/orderInfo.html',
            controller: 'orderInfoCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['order/orderInfo.js']);
                }]
            }
        }).state('app.activityInfo', {
            url: '/activityInfo',
            templateUrl: 'activity/activityInfo.html',
            controller: 'activityInfoCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['activity/activityInfo.js']);
                }]
            }
        }).state('app.activitySpu', {
            url: '/activitySpu?activity_id',
            templateUrl: 'activity/activitySpu/activitySpu.html',
            controller: 'activitySpuCtrl',
            resolve: {
                deps: ['$ocLazyLoad', function (e) {
                    return e.load(['activity/activitySpu/activitySpu.js']);
                }]
            }
        });
    })
    .run(['$rootScope','$state',function($rootScope,$state) {
        /* 监听路由的状态变化*/
        $rootScope.$on('$stateChangeSuccess', function (event,toState) {
            //是否未登录
            if(!(sessionStorage.getItem("token_admin"))) {
                $state.go('login');
            }
        });
    }])
    .controller("clothSaleCtrl", ['$scope', '$http', '$rootScope', '$alert', '$state',
        function ($scope, $http, $rootScope, $alert, $state) {

            //生成随机字母数字
            $scope.getUUID = function () {
                var d = new Date().getTime();
                var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                    var r = (d + Math.random() * 16) % 16 | 0;
                    d = Math.floor(d / 16);
                    return (c == 'x' ? r : (r & 0x7 | 0x8)).toString(16);
                });
                return uuid;
            }

            //提示信息
            $scope.showAlert = function (title, data, type) {
                $alert({title: title, content: data, placement: 'top', type: type, show: true, duration: 3});
            };

            //上传文件@parms input文件的id号
            $scope.insertFile = function (fileId, inputId, path) {

                var url = '';

                var form = new FormData();
                var file = document.getElementById(inputId).files[0];
                form.append('file', file);
                form.append('file_id', fileId);

                if (file == null || file == '') {
                    $scope.showAlert("提示:", "请选择文件", 'warning');
                } else {
                    var suffixIndex = file.name.lastIndexOf(".");
                    var suffix = file.name.substring(suffixIndex + 1).toUpperCase();
                    if (suffix != "BMP" && suffix != "JPG" && suffix != "JPEG" && suffix != "PNG" && suffix != "GIF") {
                        url = path + "file/insertFile";
                    } else {
                        url = path + "file/insertImage";
                    }

                    $http({
                        method: 'POST',
                        url: url,
                        data: form,
                        headers: {'Content-Type': undefined},
                        transformRequest: angular.identity
                    }).success(function (data) {
                        $scope.showAlert("提示:", "上传成功", 'success');
                    }).error(function (data) {
                        $scope.showAlert("警告:", "上传失败", 'danger');
                    });
                }

            };

            //从数据库下载文件
            $scope.downloadFile = function (file_id) {

                $http({
                    method: "POST",
                    url: 'file/downloadFile',
                    params: {
                        file_id: file_id,
                    },
                }).then(function successCallback(response) {
                    //请求成功
                    download("data:text/plain;base64," + response.data.file, response.data.filename);
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert("警告:", "下载失败", 'danger');
                });

            };

            //下载图片
            function download(src, name) {
                var imgData = src;//这里放需要下载的base64
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
        requestError: function (rejection) {
            $rootScope.loading = false;
            return rejection;
        },
        responseError: function (rejection) {
            $rootScope.loading = false;
            return rejection;
        }
    };
    return httpInterceptor;
}]).directive('loading', function () {
    return {
        restrict: 'E',
        transclude: true,
        template: '<div ng-show="loading" class="loading" id="allDiv"  style="position:fixed; top:0px; left:0px; width:100%; height:100%; display:none; background-color:#212121; opacity: 0.5; z-index:99999;">'
            + '<img alt="" src="img/loading.gif" style="vertical-align: middle;width:200px; height:200px; position: absolute; top:50%; left:50%; margin-top: -100px; margin-left:-100px;object-fit: cover"/></div>',
        link: function (scope, element, attr) {
            scope.$watch('loading', function (val) {
                if (val) {
                    document.getElementById("allDiv").style.display = "block";
                } else {
                    document.getElementById("allDiv").style.display = 'none';
                }
            });
        }
    }
}).directive('tooltip', function () {
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            $(element).hover(function () {
                $(element).tooltip('show');
            }, function () {
                $(element).tooltip('hide')
            })
        }
    }
}).config(["$httpProvider", function ($httpProvider) {
    $httpProvider.interceptors.push('httpInterceptor');
}]);
