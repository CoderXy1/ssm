angular.module('picApp', [])
    .controller('picCtrl', function ($scope, $http,$interval) {

        $scope.allPic = {};
        $scope.picNum = 0;
        $scope.arrayPage = new Array();
        $scope.selectparams = {

            'picName': '',
            'pageIndex': 0,
            'pageSize': 40,

        }

        $scope.selectPic = function () {

            $scope.getPicNum();

            $http({
                method:"POST",
                url:'../../pic/selectPicByName',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != ""){
                    $scope.allPic = response.data;
                    for (var i=0;i < $scope.allPic.length;i++){
                        var json = {"isShow":false};
                        $scope.allPic[i] = angular.merge($scope.allPic[i],json);
                    }
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

        $scope.getPicNum = function (){
            $http({
                method:"POST",
                url:'../../pic/selectPicNum',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != ""){
                    $scope.picNum = response.data;
                    $scope.arrayPage = new Array();
                    for (var i=0;i < Math.ceil($scope.picNum/$scope.selectparams.pageSize);i++){
                        $scope.arrayPage.push(["0"]);
                    }
                }
            },function errorCallback(response) {
                //请求失败
            });
        }

        $scope.showSize = function (index) {
            $scope.allPic[index].isShow = true;
        }

        $scope.hideSize = function (index) {
            $scope.allPic[index].isShow = false;
        }

        $scope.selectPage = function (page) {
            $scope.selectparams.pageIndex = (page - 1) * $scope.selectparams.pageSize;
            $scope.selectPic();
        }

        $scope.previousPage = function () {
            if ($scope.selectparams.pageIndex > 0) {
                $scope.selectparams.pageIndex -= $scope.selectparams.pageSize;
            }
            $scope.selectPic();
        }

        $scope.nextPage = function () {
            if ($scope.selectparams.pageIndex + $scope.selectparams.pageSize <= $scope.picNum){
                $scope.selectparams.pageIndex += $scope.selectparams.pageSize;
            }
            $scope.selectPic();
        }

        //初始化数据
        $scope.selectPic();



    });
