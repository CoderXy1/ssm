angular.module('indexApp', [])
    .controller('indexCtrl', function ($scope, $http,$interval) {

        $scope.tip = "";
        $scope.iframeUrl = "html/main/main.html";
        $scope.info = {
            userInfo : "",
            isLogin : false,
            currentPage : 1,
        };

        $scope.selectparams = {
            userId: 0,
            userName: "",
            userPassword: "",
        }
        $scope.setSessionItem = function (){
            sessionStorage.setItem("info",$scope.info);
            sessionStorage.setItem("userInfo",$scope.selectparams);
        }

        $scope.changeUrl = function (url,index){
            $scope.iframeUrl = url;
            $scope.info.currentPage = index;
        }

        $scope.login = function () {

            if ($scope.selectparams.userName == "" || $scope.selectparams.userPassword == "") {
                $scope.tip = "请输入用户名或密码";
            } else {
                $http({
                    method: "POST",
                    url: 'user/selectUserByLogin',
                    params: $scope.selectparams,
                }).then(function successCallback(response) {
                    //请求成功
                    if (response.data == null || response.data == "") {
                        $scope.tip = "用户名或密码错误";
                    } else {
                        $('#loginModal').modal('hide');
                        $scope.info.userInfo = response.data;
                        $scope.selectparams.userId = $scope.info.userInfo.userId;
                        $scope.info.isLogin = true;
                        $scope.updateState();
                    }
                }, function errorCallback(response) {
                    //请求失败
                });
            }
        }

        $scope.quitLogin = function (){
            $scope.info.userInfo = "";
            $scope.info.isLogin = false;
            $scope.selectparams.userId = 0;
            $scope.selectparams.userName = "";
            $scope.selectparams.userPassword = "";
            $scope.setSessionItem();
        }

        $scope.updateState = function () {
            $http({
                method: "POST",
                url: 'user/updateUser',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == 1) {
                    $scope.setSessionItem();
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }


        $scope.insertPic = function () {
            $http({
                method: "POST",
                url: 'pic/insertPic',
            }).then(function successCallback(response) {
                //请求成功
            }, function errorCallback(response) {
                //请求失败
            });
        }
        $interval($scope.insertPic, 1000 * 60 * 60 * 24);


    });