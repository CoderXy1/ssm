angular.module('indexApp',[]).controller('indexCtrl',function ($scope,$http) {

    $scope.userName = "";
    $scope.userPassword = "";
    sessionStorage.setItem("isLogin",'false');

    $scope.login = function (){

        $http({
            method:"POST",
            url:'user/selectUserByLogin',
            params: {
                'userName' : $scope.userName,
                'userPassword' : $scope.userPassword,
            }
        }).then(function successCallback(response) {
            //请求成功
            if (response.data == null || response.data == ""){
                $scope.tip = "账号或密码错误";
            }else{
                window.location.href="html/menu/menu.html";
                sessionStorage.setItem("isLogin",'true');
                sessionStorage.setItem("userInfo",JSON.stringify(response.data));
            }
        },function errorCallback(response) {
            //请求失败
        });

    }


    $scope.visitorLogin = function (){

        sessionStorage.setItem("userInfo","{}");
        window.location.href = "html/menu/menu.html";

    }

});