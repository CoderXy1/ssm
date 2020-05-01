angular.module("clothSalePublicApp")
    .controller("userInfoEditCtrl", function ($scope, $rootScope, $http,$state) {

        $scope.user_info_edit = [];
        $scope.selectParams = {
            user_id: $scope.getUserInfoBySession().user_id,
        }

        $scope.user_info_edit = $scope.getUserInfoBySession();
        $scope.user_info_edit.phone_number = $scope.user_info_edit.phone_number * 1; //转为数字

        $scope.loadData = function () {

        }

        $scope.updateUserInfo = function () {

            $http({
                method: "POST",
                url: '../../memberUserinfo/MD5Password',
                params: {
                    user_password: $scope.user_info_edit.password_old
                }
            }).then(function successCallback(response) {
                //请求成功
                if ($scope.user_info_edit.user_password == response.data.item) {
                    $http({
                        method: "POST",
                        url: '../../memberUserinfo/updateMemberUserinfo',
                        params: {
                            user_id : $scope.user_info_edit.user_id,
                            phone_number : $scope.user_info_edit.phone_number,
                            email : $scope.user_info_edit.email,
                            user_password: $scope.user_info_edit.password_new
                        }
                    }).then(function successCallback(response) {
                        //请求成功
                        $scope.showAlert('成功:',response.data.msg + ",请重新登录", 'success');
                        sessionStorage.removeItem("token_user")
                        sessionStorage.removeItem("user_info");
                        $state.go('public.login',{},{reload:true});
                    }, function errorCallback(response) {
                        //请求失败
                    });
                } else {
                    $scope.showAlert('失败:', '旧密码错误', 'danger');
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }

        $scope.loadData();


    });