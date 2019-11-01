angular.module("loginApp", [])
    .controller("loginCtrl", function ($scope, $http, $timeout) {

        $scope.tip = "";
        $scope.roleName = "student";
        $scope.selectParams = {
            id: '',
            password: '',
        }

        $scope.clearTip = function () {
            $timeout(function () {
                $scope.tip = "";
            }, 2000);
        }

        $scope.login = function () {
            var url = "";
            if ($scope.selectParams.id == "" || $scope.selectParams.password == "") {
                $scope.tip = "请输入账号或密码";
                $scope.clearTip();
            } else {
                switch ($scope.roleName) {
                    case "student": url = 'student/selectByIdAndPw';
                        break;
                    case "teacher": url = 'teacher/selectTeaByLogin';
                        break;
                    case "admin": url = 'admin/selectAdminByLogin';
                        break;
                }
                $http({
                    method: "POST",
                    url: url,
                    params: $scope.selectParams,
                }).then(function successCallback(response) {
                    //请求成功
                    if (response.data == null || response.data == "") {
                        $scope.tip = "用户名或密码错误";
                        $scope.clearTip();
                    } else {
                        window.location.replace("home.html");
                        sessionStorage.setItem("isLogin", 'true');
                        sessionStorage.setItem("userInfo", JSON.stringify(angular.merge(response.data,{"role":$scope.roleName})));
                    }
                }, function errorCallback(response) {
                    //请求失败
                    $scope.tip = "请求失败，请重试";
                });
            }

        }
    });