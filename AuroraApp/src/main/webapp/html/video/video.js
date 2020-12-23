angular.module('ionicApp', ['ionic'])

    .controller('videoCtrl', function ($scope, $timeout, $ionicLoading, $http) {

        $scope.insertGallery = function () {

            if ($scope.insert('file/insertFile')) {
                console.log('success')
            }
        }

        $scope.imgUrl = '';

        $scope.insert = function (url) {


            $scope.fileId = $scope.genUUID();

            var form = new FormData();
            var file = document.getElementById("fileUpload").files[0];
            form.append('file', file);
            form.append('fileId', $scope.fileId);

            if (file == null || file == '') {
                //alert("请选择文件");
                $scope.showAlert("提示", "请选择文件");
                return false;
            } else {

                var size = file.size / 1024;
                console.log("附件大小不能大于" + filemaxsize / 1024 + "M！");

                $scope.showLoading();

                $http({
                    method: 'POST',
                    url: url,
                    data: form,
                    headers: {'Content-Type': undefined},
                    transformRequest: angular.identity
                }).success(function (data) {
                    //alert("上传成功");
                    console.log("上传成功" + file);
                    $ionicLoading.hide();
                }).error(function (data) {
                    console.log('operation fail' + data);
                    $ionicLoading.hide();
                });
                return true;
            }

        };

    });