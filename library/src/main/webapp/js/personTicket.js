angular.module('personTicketApp', []).controller('personTicketCtrl', function ($scope, $http, $rootScope, $sce) {

    $scope.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
    $scope.ticketInfo = "";
    $scope.ticketsInfoArray = new Array();

    $scope.refreshContent = function(){

        $scope.ticketsInfoArray = new Array();

        $http({
            method: "POST",
            url: '../../ticket/selectTicketByUserId',
            params: {
                'userId': $scope.userInfo.userId
            }
        }).then(function successCallback(response) {
            //请求成功
            if (response.data == null || response.data == "") {
                $scope.tip = "没有相关信息";
            } else {
                $scope.ticketInfo = response.data;
                for (var i=0;i < $scope.ticketInfo.length;i++){
                    var id = $scope.ticketInfo[i].bookId;
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
                var bookAndTicketInfo = Object.assign(response.data, $scope.ticketInfo[index]);
                $scope.ticketsInfoArray.push(bookAndTicketInfo);
            }
        }, function errorCallback(response) {
            //请求失败
        });

    }

    $scope.returnBook = function (bookId,borrowId) {

        $http({
            method: "POST",
            url: '../../borrow/updateBorrowReturnState',
            params: {
                "borrowId": borrowId,
            }
        }).then(function successCallback(response) {
            //请求成功
            if (response.data == 1) {
                $scope.updateReturnState(borrowId,bookId);
            }
        }, function errorCallback(response) {
            //请求失败
        });

    }

    $scope.updateReturnState = function (borrowId,bookId){
        $http({
            method: "POST",
            url: '../../ticket/updateReturnState',
            params: {
                "borrowId": borrowId,
                "isReturn":"true",
                "bookId":bookId
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