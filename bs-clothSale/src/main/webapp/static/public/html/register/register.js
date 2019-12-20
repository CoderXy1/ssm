angular.module("clothSalePublicApp")
    .controller("registerCtrl", function ($scope, $rootScope,$state,$http) {

        $scope.isShowPassword = 0; //0不可见 1可见

        $scope.selectParams = {
            user_id : '',
            user_name : '',
            phone_number : '',
            user_password : '',
            icon_id : 'aa598905-d970-4633-d64d-4d1d8d9fc552',
            email : '',
        };

        $scope.showPassword = function () {
            var showPwd = $("#admin_password");
            if ($scope.isShowPassword == 0){
                //可见
                showPwd.prop('type','text');
            }else {
                //不可见
                showPwd.prop('type','password');
            }
            $scope.isShowPassword = !$scope.isShowPassword;
        }

        $scope.register = function () {

            $scope.selectParams.user_id = $scope.getUUID();

            $http({
                method: "POST",
                url: '../../memberUserinfo/insertMemberUserinfo',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert("提示: ",response.data.msg,"success");
                $state.go('public.login');
            }, function errorCallback(response) {
                //请求失败
            });

        }
        
        
    });