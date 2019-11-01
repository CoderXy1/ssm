angular.module('menuApp', [])
    .value('trustSrc', '')
    .controller('menuCtrl', function ($rootScope, $scope, $http, $sce) {

        $scope.userInfo = JSON.parse(sessionStorage.getItem("userInfo"));
        $scope.userName = $scope.userInfo.userName;
        $scope.isLogin = angular.equals(sessionStorage.getItem("isLogin"),'true');
        $scope.bookName = "";
        $scope.tip = "";
        $rootScope.trustSrc = $sce.trustAsResourceUrl("../main/main.html");

        $scope.changeUrl = function (mainUrl) {

            $scope.trustSrc = $sce.trustAsResourceUrl(mainUrl);

        }

        $scope.changeUrlByLogin = function (mainUrl) {

            if ($scope.userInfo.userName == null || $scope.userInfo.userName == ""){
                alert("请先登录！");
            }else{
                $scope.trustSrc = $sce.trustAsResourceUrl(mainUrl);
            }

        }


        $scope.quitLogin = function (mainUrl) {

            window.location.href = "../../index.html";
            sessionStorage.setItem("userInfo","{}");
            sessionStorage.setItem("isLogin",'false');

        }

        $scope.toSearch = function () {

            $scope.trustSrc = $sce.trustAsResourceUrl("../main/searchBooks.html?bookName=" + $scope.bookName);

        }

        $scope.login = function (){

            $http({
                method:"POST",
                url:'../../user/selectUserByLogin',
                params: {
                    'userName' : $scope.userNamePut,
                    'userPassword' : $scope.userPasswordPut,
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == null || response.data == ""){
                    $scope.tip = "账号或密码错误";
                }else {
                    window.location.href="menu.html";
                    sessionStorage.setItem("isLogin",'true');
                    sessionStorage.setItem("userInfo",JSON.stringify(response.data));
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

    });
