angular.module('movieApp', [])
    .controller('movieCtrl', function ($scope, $http) {

        $scope.allMovie = "";
        $scope.movieNum = 0;
        $scope.arrayPage = new Array();
        $scope.selectparams = {

            'movieName': '',
            'pageIndex': 0,
            'pageSize': 30,

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
                $scope.movieNum = response.data;
                $scope.arrayPage = new Array();
                for (var i=0;i < Math.ceil($scope.movieNum/$scope.selectparams.pageSize);i++){
                    $scope.arrayPage.push(["0"]);
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertMovie = function () {

            $http({
                method: "POST",
                url: 'movie/insertMovie',
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

        $scope.selectPage = function (page) {
            $scope.selectparams.pageIndex = (page - 1) * $scope.selectparams.pageSize;
            $scope.selectMovie();
        }

        $scope.previousPage = function () {
            if ($scope.selectparams.pageIndex > 0) {
                $scope.selectparams.pageIndex -= $scope.selectparams.pageSize;
            }
            $scope.selectMovie();
        }

        $scope.nextPage = function () {
            if ($scope.selectparams.pageIndex + $scope.selectparams.pageSize <= $scope.movieNum){
                $scope.selectparams.pageIndex += $scope.selectparams.pageSize;
            }
            $scope.selectMovie();
        }

        //初始化数据
        $scope.selectMovie();

    });