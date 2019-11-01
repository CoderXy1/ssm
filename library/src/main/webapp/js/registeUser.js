angular.module('registeUserApp', [])
    .controller('registeUserController', function ($scope, $http, $sce) {

        $scope.provinces = "";
        $scope.citys = "";

        $http({
            method:"POST",
            url:'../../province/selectAllProvince',
        }).then(function successCallback(response) {
            //请求成功
            if (response.data != null){
                $scope.provinces = response.data;
            }
        },function errorCallback(response) {
            //请求失败
        });

        $scope.changeCity = function (provinceId) {

            $http({
                method:"POST",
                url:'../../city/selectCityByProvinceId',
                params: {
                    'provinceId' : provinceId,
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null){
                    $scope.citys = response.data;
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

        $scope.registeUser = function () {

            $http({
                method:"POST",
                url:'../../user/addUser',
                params: {
                    'userName' : $scope.userNameRe,
                    'userPassword' : $scope.userPassword,
                    'sex' : $scope.sex,
                    'phone' : $scope.phone,
                    'address' : $scope.provinceInfo.provinceName + $scope.cityInfo.cityName,
                    'power' : $scope.power
                }
            }).then(function successCallback(response) {
                //请求成功
                if (response.data == 1){
                    window.location.href = "main.html"
                }
            },function errorCallback(response) {
                //请求失败
            });

        }

    });
