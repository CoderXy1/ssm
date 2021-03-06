angular.module("testApp", ['ngFileUpload'])
    .controller("testCtrl", function ($scope,$rootScope,$http,Upload) {

        $scope.imgUrl = '';

        $scope.genUUID = function() {
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                var r = (d + Math.random()*16)%16 | 0;
                d = Math.floor(d/16);
                return (c=='x' ? r : (r&0x7|0x8)).toString(16);
            });
            return uuid;
        }

        $scope.insert = function (url) {

            $scope.fileId = $scope.genUUID();

            var form = new FormData();
            var file = document.getElementById("fileUpload").files[0];
            form.append('file', file);
            form.append('fileId',$scope.fileId);

            if (file == null || file == ''){
                alert("请选择文件");
                return false;
            }else {
                $http({
                    method: 'POST',
                    url: url,
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).success(function (data) {
                    alert("上传成功");
                }).error(function (data) {
                    console.log('operation fail' + data);
                });
                return true;
            }

        };

        $scope.showFile = function () {

            $http({
                method: "POST",
                url: '../../file/downloadFile',
                params : {
                    galleryid : '48f4c552-ed28-491d-9a7e-8a605c88dbc9',
                    path : 'C:\\Users\\Administrator.SC-201907111318\\Desktop',
                },
            }).then(function successCallback(response) {
                //请求成功
                alert("下载成功");
            }, function errorCallback(response) {
                //请求失败
                console.log(response.data)
            });

        };

        $scope.showImage = function () {

            $http({
                method: "POST",
                url: '../../file/selectImage',
                params: {
                    fileId: '824faf19-c056-45c2-c588-0cc999ece9d7'
                },
            }).then(function successCallback(response) {
                //请求成功
                console.log(response.data);
                $scope.imgUrl = response.data[0];
            }, function errorCallback(response) {
                //请求失败
                console.log(response.data)
            });
        }
        
        $scope.insertGallery = function () {

            if ($scope.insert('../../file/insertImage')){
                $http({
                    method: "POST",
                    url: '../../gallery/insertGallery',
                    params : {
                        galleryid : $scope.genUUID(),
                        galleryname : '相册',
                        fileid : $scope.fileId,
                        galleryDescribe : '描述',
                    },
                }).then(function successCallback(response) {
                    //请求成功
                    alert("添加成功");
                }, function errorCallback(response) {
                    //请求失败
                    console.log(response.data)
                });
            }
        }

    });