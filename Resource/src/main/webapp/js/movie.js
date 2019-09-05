angular.module('movieApp', ['ui.bootstrap'])
    .controller('movieCtrl', function ($scope, $http) {

        $scope.allMovie = "";
        $scope.selectparams = {

            'movieName': '',
            'pageIndex': 0,
            'pageSize': 30,
            'pageNum' : 1,
            'totalNum' : 0,

        }

        $scope.getMovieNum = function (){
            $http({
                method: "POST",
                url: '../../movie/selectMovieNum',
                params: {
                    'movieName': $scope.selectparams.movieName,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectparams.totalNum = response.data;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertMovie = function () {

            $http({
                method: "POST",
                url: '../../movie/insertMovie',
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == 1) {
                    $scope.tip = "成功";
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }

        $scope.selectMovie = function(){

            $scope.selectparams.pageIndex = ($scope.selectparams.pageNum - 1) * $scope.selectparams.pageSize;

            $scope.getMovieNum();

            $http({
                method:"POST",
                url:'../../movie/selectAllMovie',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != ""){
                    $scope.allMovie = response.data;
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

        //初始化数据
        $scope.selectMovie();

    });