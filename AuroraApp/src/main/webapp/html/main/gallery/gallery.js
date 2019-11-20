angular.module('ionicApp')

    .controller('galleryCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup) {

        $scope.galleryList = [];
        $scope.isLoadMore = true;
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 12,
        };

        var htmlEl = angular.element(document.querySelector('html'));
        htmlEl.on('click', function (event) {
            if (event.target.nodeName === 'HTML') {
                if ($scope.alertImgPopup) {
                    $scope.alertImgPopup.close();
                }
                if ($scope.alertMenuPopup){
                    $scope.alertMenuPopup.close();
                }
            }
        });

        $scope.showImgAlert = function (src, imgName) {
            $scope.alertImgPopup = $ionicPopup.show({
                template: '<img ng-src="data:image/jpg;base64,' + src + '" style="object-fit: cover;width: 100%;border-radius: 3px">',
                title: imgName,
                scope: $scope,
            });
        }

        $scope.alertMenu = function (fileId,imgName,galleryId) {
            $scope.downFileId = fileId;
            $scope.deleteGalleryId = galleryId;
            $scope.alertMenuPopup = $ionicPopup.show({
                template:
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-balanced" ng-click="showFile(downFileId)">下载</button></div>' +
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-assertive" ng-click="deleteGallery(deleteGalleryId,downFileId)">删除</button></div>',
                title: imgName,
                scope: $scope,
            });
        }

        $scope.showFile = function (fileId) {

            // $http({
            //     method: "POST",
            //     url: 'file/downloadFile',
            //     params: {
            //         fileId: fileId,
            //         path: 'C:\\Users\\Administrator.SC-201907111318\\Desktop',
            //     },
            // }).then(function successCallback(response) {
            //     //请求成功
            //    $scope.showAlert("成功下载到桌面","");
            //    $scope.alertMenuPopup.close();
            // }, function errorCallback(response) {
            //     //请求失败
            //     console.log(response.data);
            //     $scope.alertMenuPopup.close();
            // });

            $http({
                method: "POST",
                url: 'file/downloadFile',
                params: {
                    fileId: fileId,
                    path: 'C:\\Users\\Administrator.SC-201907111318\\Desktop',
                },
            }).then(function successCallback(response) {
                //请求成功
                download("data:image/jpg;base64," + response.data.file,response.data.filename);
                $scope.alertMenuPopup.close();
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert("下载失败","");
                $scope.alertMenuPopup.close();
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

        $scope.selectGallery = function () {

            $scope.showLoading();

            $http({
                method: "POST",
                url: 'gallery/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.galleryList = response.data;
                $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                $ionicLoading.hide();
            }, function errorCallback(response) {
                //请求失败
                $ionicLoading.hide();
            });
        }

        $scope.deleteGallery = function (galleryId,fileId) {

            if (confirm("是否删除")){
                $http({
                    method: "POST",
                    url: 'gallery/deleteGallery',
                    params: {
                        galleryId : galleryId,
                        fileId : fileId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.galleryList = [];
                    $scope.selectParams.pageIndex = 0;
                    $scope.loadData();
                    $scope.showAlert("删除成功",'');
                    $scope.isLoadMore = true;
                    $scope.alertMenuPopup.close();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert("删除失败",'');
                    $scope.alertMenuPopup.close();
                });
            }
        }

        $scope.loadData = function () {

            $scope.selectGallery();

        }

        //上拉刷新
        $scope.loadMore = function () {
            $http({
                method: "POST",
                url: 'gallery/selectAll',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == '') {
                    $scope.showAlert("提示", "没有更多数据了");
                    $scope.isLoadMore = false;
                } else {
                    angular.forEach(response.data, function (item, index, array) {
                        $scope.galleryList.push(item);
                    })
                    $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }, function errorCallback(response) {
                //请求失败
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }

        $scope.loadData();

    });