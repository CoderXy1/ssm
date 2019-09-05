angular.module('picApp', ['ui.bootstrap'])
    .controller('picCtrl', function ($scope, $http,$interval) {

        $scope.allPic = {};
        $scope.picNum = 0;
        $scope.arrayPage = new Array();
        $scope.selectparams = {

            'picName': '',
            'pageIndex': 0,
            'pageSize': 40,
            'pageNum' : 1,
            'totalNum' : 0,

        }

        $scope.selectPic = function () {

            $scope.selectparams.pageIndex = ($scope.selectparams.pageNum - 1) * $scope.selectparams.pageSize;

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
                    $scope.selectparams.totalNum = response.data;
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

        //初始化数据
        $scope.selectPic();



    });
