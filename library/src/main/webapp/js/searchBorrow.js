angular.module('searchBorrowApp', []).controller('searchBorrowCtrl', function ($scope, $http, $rootScope, $sce) {

    $scope.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    $scope.borrowInfo = "";

    $scope.refreshContent = function(){

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
                for (var i=0;i < $scope.borrowInfo.length;i++){
                    var id = $scope.borrowInfo[i].bookId;
                    $scope.getBookName(i,id);
                }
            }
        }, function errorCallback(response) {
            //请求失败
        });

    }

    $scope.refreshContent();

    $scope.getBookName = function (index,bookId) {

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
                $scope.borrowInfo[index].bookId = response.data.bookName;
            }
        }, function errorCallback(response) {
            //请求失败
        });

    }
    
    $scope.renewBook = function (borrowId,returnDate_should) {

        $http({
            method: "POST",
            url: '../../borrow/updateBorrowRenewState',
            params: {
                "borrowId": borrowId,
                "returnDate_should": returnDate_should,
            }
        }).then(function successCallback(response) {
            //请求成功
            if (response.data == 1) {
                alert("续借成功!");
                $scope.refreshContent();
            }
        }, function errorCallback(response) {
            //请求失败
        });

    }

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

});