angular.module('musicApp', [])
    .controller('musicCtrl', function ($scope, $http,$sce) {

        $scope.sce = $sce.trustAsResourceUrl;

        $scope.allmusic = "";
        $scope.musicNum = 0;
        $scope.arrayPage = new Array();
        $scope.selectparams = {

            'musicName': '',
            'singer' : '',
            'pageIndex': 0,
            'pageSize': 20,

        }

        $scope.addMusic = function (){
            $http({
                method: "POST",
                url: '../../music/insertMusic',
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == 1){
                    $scope.tip = "成功";
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectmusic = function(){

            $scope.getMusicNum();

            $http({
                method:"POST",
                url:'../../music/selectMusicByPage',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != ""){
                    $scope.allmusic = response.data;
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

        $scope.getMusicNum = function (){
            $http({
                method:"POST",
                url:'../../music/getMusicNum',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != ""){
                    $scope.musicNum = response.data;
                    $scope.arrayPage = new Array();
                    for (var i=0;i < Math.ceil($scope.musicNum/$scope.selectparams.pageSize);i++){
                        $scope.arrayPage.push(["0"]);
                    }
                }
            },function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectPage = function (page) {
            $scope.selectparams.pageIndex = (page - 1) * $scope.selectparams.pageSize;
            $scope.selectmusic();
        }

        $scope.previousPage = function () {
            if ($scope.selectparams.pageIndex > 0) {
                $scope.selectparams.pageIndex -= $scope.selectparams.pageSize;
            }
            $scope.selectmusic();
        }

        $scope.nextPage = function () {
            if ($scope.selectparams.pageIndex + $scope.selectparams.pageSize <= $scope.musicNum){
                $scope.selectparams.pageIndex += $scope.selectparams.pageSize;
            }
            $scope.selectmusic();
        }

        //初始化数据
        $scope.selectmusic();

    });