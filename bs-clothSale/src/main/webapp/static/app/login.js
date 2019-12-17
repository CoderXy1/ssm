angular.module("clothSaleApp")
    .controller("loginCtrl", function ($scope,$rootScope,$http,$state) {

        if(sessionStorage.getItem("token")) {
            $state.go('app.main');
        }

        $scope.adminInfo = {
            admin_name : '',
            admin_password : '',
        }

        $scope.adminLogin = function () {
            $http({
                method: "POST",
                url: '../../memberAdmin/selectAdminByLogin',
                params: $scope.adminInfo
            }).then(function successCallback(response) {
                //请求成功
                if (response.data.success){
                    sessionStorage.setItem("token_admin", response.data.success);
                    sessionStorage.setItem("admin_info", JSON.stringify(response.data.item));
                    $scope.showAlert('提示:',response.data.msg,'success');
                    $state.go("app.main");
                }else {
                    $scope.showAlert('错误:',response.data.msg,'danger');
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

    });