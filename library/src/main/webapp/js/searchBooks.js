angular.module('searchBooksApp', [])
    .config(['$locationProvider', function ($locationProvider) {
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        });
    }])
    .controller('searchBooksCtrl', function ($rootScope, $scope, $http, $sce, $location) {

        $scope.bookName = $location.search().bookName;
        $scope.booksInfo = "";
        $scope.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        $scope.currPage = 0;
        $scope.pageSize = 50;
        $scope.allItems = 0;
        $scope.tip = "搜索结果";
        $scope.arrayPage = new Array();

        $http({
            method: "POST",
            url: '../../book/selectBookByName',
            params: {
                'bookName': $scope.bookName,
            }
        }).then(function successCallback(response) {
            //请求成功
            $scope.allItems = response.data.length;
            var num = Math.ceil($scope.allItems/$scope.pageSize);
            for (var i=0;i < num;i++){
                $scope.arrayPage.push(["0"]);
            }
            $scope.tip += " 总共查询到" + $scope.allItems + "条记录";
        }, function errorCallback(response) {
            //请求失败
        });

        $scope.selectPage = function(page){

            $scope.currPage = (page-1) * $scope.pageSize;
            $scope.getBookByPage();

        }

        $scope.getBookByPage = function (){

            $http({
                method: "POST",
                url: '../../book/selectBookByNameAndPage',
                params: {
                    'bookName': $scope.bookName,
                    'currPage' : $scope.currPage,
                    'pageSize' : $scope.pageSize,
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null) {
                    $scope.booksInfo = response.data;
                } else {
                    $scope.tip = "没有相关书籍";
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }

        $scope.getBookByPage();

        $scope.previousPage = function () {
            if ($scope.currPage > 0) {
                $scope.currPage -= $scope.pageSize;
            }
            $scope.getBookByPage();
        }

        $scope.nextPage = function () {
            if ($scope.currPage+$scope.pageSize <= $scope.allItems){
                $scope.currPage += $scope.pageSize;
            }
            $scope.getBookByPage();
        }

        $scope.borrowBook = function (bookId){

            if ($scope.userInfo.userId == "" || $scope.userInfo.userId == null){
                alert("未登录!");
            }else{
                $http({
                    method: "POST",
                    url: '../../book/borrowBook',
                    params: {
                        'bookId': bookId,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.getBookByPage();
                }, function errorCallback(response) {
                    //请求失败
                });

                $http({
                    method: "POST",
                    url: '../../borrow/addBorrow',
                    params: {
                        'bookId': bookId,
                        'userId':$scope.userInfo.userId
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    alert("借阅成功！");
                }, function errorCallback(response) {
                    //请求失败
                });

            }
        }

    });