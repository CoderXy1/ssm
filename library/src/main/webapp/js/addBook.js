angular.module('addBookApp',[]).controller('addBookCtrl',function ($scope,$http) {

    $scope.allTypes = "";
    $scope.selectedType = -1;

    $http({
        method:"POST",
        url:'../../type/selectAllTypes',
    }).then(function successCallback(response) {
        //请求成功
        if (response.data != null){
            $scope.allTypes = response.data;
        }
    },function errorCallback(response) {
        //请求失败
    });

    $scope.addBook = function (){

        $http({
            method:"POST",
            url:'book/addBook',
            params: {
                'bookName' : encodeURIComponent($scope.bookName),
                'bookPicPath' : encodeURIComponent($scope.bookPicPath),
                'bookScore' : $scope.bookScore,
                'author' : encodeURIComponent($scope.author),
                'publishing' : encodeURIComponent($scope.publishing),
                'price' : $scope.price,
                'totalNumber' : $scope.totalNumber,
                'havingNumber' : $scope.havingNumber,
                'typeId' : $scope.selectedType,
            }
        }).then(function successCallback(response) {
            //请求成功
            if (response.data == 1){
                window.location.href="main.html";
            }else{
                $scope.tip = "错误";
            }
        },function errorCallback(response) {
            //请求失败
            $scope.tip = "失败";
        });

    }

});