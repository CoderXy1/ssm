angular.module('ionicApp')

    .controller('filesCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup, $sce) {

        $scope.fileList = [];
        $scope.isLoadMore = false;

        $scope.selectParams = {
            fileName: '',
            fileType: '',
            pageIndex: 0,
            pageSize: 5,
        }

        $scope.checkFile = function (fileId, fileType) {
            fileType = fileType.toUpperCase();
            if (fileType === "BMP" || fileType === "JPG" || fileType === "JPEG" || fileType === "PNG" || fileType === "GIF") {
                return 1;
            } else if (fileType === "mp4" || fileType === "MP4") {
                return 2;
            } else {
                return 3;
            }
        }

        $scope.videoUrl = function (url) {
            return $sce.trustAsResourceUrl(url);
        }

        var htmlEl = angular.element(document.querySelector('html'));
        htmlEl.on('click', function (event) {
            if (event.target.nodeName === 'HTML') {
                if ($scope.alertMenuPopup) {
                    $scope.alertMenuPopup.close();
                }
            }
        });

        $scope.showFilesMenu = function (fileId, fileName) {

            $scope.deleteFileId = fileId;
            $scope.alertMenuPopup = $ionicPopup.show({
                template:
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-assertive" ng-click="deleteFile(deleteFileId)">删除</button></div>' +
                    '<div class="button-bar" style="margin-bottom: 10px"><button class="button button-dark" ng-click="closePopup()">取消</button></div>',
                title: fileName,
                scope: $scope,
            });
        }

        $scope.closePopup = function (){
            $scope.alertMenuPopup.close();
        }

        $scope.deleteFile = function (fileId) {

            if (confirm("是否删除")) {
                $http({
                    method: "POST",
                    url: 'file/deleteFile',
                    params: {
                        fileId: fileId
                    },
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert("删除成功", "");
                    $scope.alertMenuPopup.close();
                    $scope.selectFiles();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert("删除失败", "");
                });
            }
        }

        $scope.selectFiles = function () {

            $scope.selectParams.pageIndex = 0;

            $http({
                method: "POST",
                url: 'file/selectAllFile',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                $scope.fileList = response.data;
                $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                $scope.isLoadMore = true;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //上拉刷新
        $scope.loadMore = function () {
            $http({
                method: "POST",
                url: 'file/selectAllFile',
                params: $scope.selectParams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == '') {
                    $scope.showAlert("提示", "没有更多数据了");
                    $scope.isLoadMore = false;
                } else {
                    angular.forEach(response.data, function (item, index, array) {
                        $scope.fileList.push(item);
                    })
                    $scope.selectParams.pageIndex += $scope.selectParams.pageSize;
                }
                $scope.$broadcast('scroll.infiniteScrollComplete');
            }, function errorCallback(response) {
                //请求失败
                $scope.$broadcast('scroll.infiniteScrollComplete');
            });
        }
    });

// window.onload = function () {
//     var video, output;
//     output = document.getElementById("output");
//     var canvas = document.createElement('canvas')
//     var img = document.createElement("img");
//     video = document.getElementById('video')
//     video.setAttribute('crossOrigin', 'anonymous')
//     video.currentTime=1;
//     canvas.width = video.clientWidth
//     canvas.height = video.clientHeight
//     video.onloadeddata = (() => {
//         canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height)
//         var dataURL = canvas.toDataURL('image/png')
//         img.src = dataURL;
//         img.width = 400;
//         img.height = 300;
//         output.appendChild(img);
//         console.log(img);
//     })
// }