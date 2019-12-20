angular.module("clothSalePublicApp")
    .controller("loginCtrl", function ($scope, $rootScope,$http,$state,$stateParams) {

        $scope.goUrl = $stateParams.url == undefined ?"public.main":$stateParams.url;
        $scope.extraData = $stateParams.extraData == undefined ? "":$stateParams.extraData;

        $scope.isShowPassword = 0; //0不可见 1可见
        $scope.selectParams = {
            user_name : '',
            user_password : '',
        }
        
        $scope.showPassword = function () {
            var showPwd = $("#user_password");
            if ($scope.isShowPassword == 0){
                //可见
                showPwd.prop('type','text');
            }else {
                //不可见
                showPwd.prop('type','password');
            }
            $scope.isShowPassword = !$scope.isShowPassword;
        }
        
        $scope.login = function () {

            $http({
                method: "POST",
                url: '../../memberUserinfo/selectUserinfoByLogin',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                if (response.data.success){
                    sessionStorage.setItem("token_user", response.data.success);
                    sessionStorage.setItem("user_info", JSON.stringify(response.data.item));
                    $scope.showAlert("提示: ",response.data.msg,"success");
                    if ($scope.extraData != ''){
                        $state.go($scope.goUrl,JSON.parse($scope.extraData),{reload:true});
                    }else{
                        $state.go($scope.goUrl,{},{reload:true});
                    }

                }else {
                    $scope.showAlert("错误: ",response.data.msg,"danger");
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }


    });