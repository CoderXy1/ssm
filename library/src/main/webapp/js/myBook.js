angular.module('myBookApp', [])
    .controller('myBookCtrl', function ($rootScope, $scope, $http, $sce, $location) {

        $scope.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        $scope.tip = "搜索结果";
        $scope.borrowInfo = "";
        $scope.booksInfoArray = new Array();

        $scope.refreshContent = function () {

            $scope.booksInfoArray = new Array();

            $http({
                method: "POST",
                url: '../../borrow/selectBorrowByUserId',
                params: {
                    'userId': $scope.userInfo.userId
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == "") {
                    $scope.tip = "没有相关信息";
                } else {
                    $scope.borrowInfo = response.data;
                    for (var i = 0; i < $scope.borrowInfo.length; i++) {
                        var id = $scope.borrowInfo[i].bookId;
                        $scope.getBookInfo(i, id);
                    }
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.refreshContent();

        $scope.returnBook = function (borrowId) {

            $http({
                method: "POST",
                url: '../../borrow/updateBorrowReturnState',
                params: {
                    "borrowId": borrowId,
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == 1) {
                    alert("归还成功!");
                    $scope.refreshContent();
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }

        $scope.getBookInfo = function (i, bookId) {

            $http({
                method: "POST",
                url: '../../book/selectBookById',
                params: {
                    'bookId': bookId,
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == "") {
                    $scope.tip = "没有相关信息";
                } else {
                    var bookAndBorrowInfo = Object.assign(response.data, $scope.borrowInfo[i]);
                    $scope.booksInfoArray.push(bookAndBorrowInfo);
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }


    });