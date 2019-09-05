angular.module('musicApp', ['ui.bootstrap'])
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
            'pageNum' : 1,
            'totalNum' : 0,
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

            $scope.selectparams.pageIndex = ($scope.selectparams.pageNum - 1) * $scope.selectparams.pageSize;

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
                    $scope.selectparams.totalNum = response.data;
                }
            },function errorCallback(response) {
                //请求失败
            });
        }

        //初始化数据
        $scope.selectmusic();

    });