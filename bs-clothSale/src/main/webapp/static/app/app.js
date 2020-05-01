angular.module("clothSaleApp")
    .controller("appCtrl", function ($scope, $rootScope, $state, $http,$timeout) {

        $scope.admin_info = [];
        $scope.password_old = '';
        $scope.password_new = '';

        if (sessionStorage.getItem("token_admin")) {
            $scope.admin_info = JSON.parse(sessionStorage.getItem("admin_info"))[0];
        }

        $scope.quitLogin = function () {
            sessionStorage.removeItem("token_admin")
            sessionStorage.removeItem("admin_info");
            $state.go('login');
        }

        $scope.showAdmin = function () {
            $('#addModal').modal('show');
        }

        $scope.updateAdmin = function () {

            $http({
                method: "POST",
                url: '../../memberUserinfo/MD5Password',
                params: {
                    user_password: $scope.password_old
                }
            }).then(function successCallback(response) {
                //请求成功
                if ($scope.admin_info.admin_password == response.data.item) {
                    $http({
                        method: "POST",
                        url: '../../memberAdmin/updateMemberAdmin',
                        params: {
                            admin_id: $scope.admin_info.admin_id,
                            admin_password: $scope.password_new
                        }
                    }).then(function successCallback(response) {
                        //请求成功
                        $('#addModal').modal('hide');
                        var a = $timeout(function(){
                            $scope.showAlert('成功:', response.data.msg + ",请重新登录", 'success');
                            sessionStorage.removeItem("token_admin")
                            sessionStorage.removeItem("admin_info");
                            $state.go('login',{},{reload:true});
                        },500);
                        a.then();
                    }, function errorCallback(response) {
                        //请求失败
                    });
                } else {
                    $scope.showAlert('失败:', '旧密码错误', 'danger');
                }
            })
        }

    });