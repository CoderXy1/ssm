angular.module('ionicApp')

    .controller('galleryEditCtrl', function ($scope, $timeout, $ionicModal, $ionicActionSheet, $ionicLoading, $http, $ionicPopup,$state) {

        $scope.imgUrl = '';

        $scope.insert = function (url) {

            $scope.fileId = $scope.genUUID();

            var form = new FormData();
            var file = document.getElementById("fileUpload").files[0];
            form.append('file', file);
            form.append('fileId', $scope.fileId);

            if (file == null || file == '') {
                //alert("请选择文件");
                $scope.showAlert("提示","请选择文件");
                return false;
            }else if ($scope.galleryname == null || $scope.galleryDescribe == null) {
                $scope.showAlert("提示","请输入信息");
                return false;
            }else {
                $http({
                    method: 'POST',
                    url: url,
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).success(function (data) {
                    //alert("上传成功");
                    console.log("上传成功" + file);
                }).error(function (data) {
                    console.log('operation fail' + data);
                });
                return true;
            }

        };

        $scope.insertGallery = function () {

            if ($scope.insert('file/insertImage')) {
                $http({
                    method: "POST",
                    url: 'gallery/insertGallery',
                    params: {
                        galleryid: $scope.genUUID(),
                        galleryname: $scope.galleryname,
                        fileid: $scope.fileId,
                        galleryDescribe: $scope.galleryDescribe,
                    },
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert("添加相册","添加成功");
                    $state.go('main.gallery',{},{reload:true});
                }, function errorCallback(response) {
                    //请求失败
                    console.log(response.data);
                });
            }
        }

    });