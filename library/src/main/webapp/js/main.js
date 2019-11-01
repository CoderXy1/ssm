angular.module('mainApp',[]).controller('mainCtrl',function ($scope,$http,$rootScope,$sce) {

    $scope.bookName = "";

    $scope.toSearch = function (){

        window.location.href = "searchBooks.html?bookName=" + $scope.bookName;

    }


});