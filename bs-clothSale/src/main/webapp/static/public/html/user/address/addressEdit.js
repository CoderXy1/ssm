angular.module("clothSalePublicApp")
    .controller("addressEditCtrl", function ($scope, $rootScope,$http,$state,$stateParams) {

        $scope.provinceList = [];
        $scope.cityList = [];
        $scope.areaList = [];
        $scope.address_id_get = $stateParams.address_id == undefined ? '':$stateParams.address_id ;
        $scope.selectParams = {
            address_id : '',
            area_id : '',
            province_id : '',
            city_id : '',
            details_address : '',
            liaison_person : '',
            phone_number : '',
            postal_code : '',
            user_id : '',
        }

        //是否登录
        if (sessionStorage.getItem("token_user")) {
            $scope.selectParams.user_id = JSON.parse(sessionStorage.getItem("user_info"))[0].user_id;
        }

        $scope.selectAllProvinces = function (){
            $http({
                method: "POST",
                url: '../../address/selectAllProvinces',
            }).then(function successCallback(response) {
                //请求成功
                $scope.provinceList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectCityByProvinceId = function (){

            //清空市和区县的信息
            $scope.cityList = [];
            $scope.areaList = [];
            $scope.selectParams.city_id = '';

            $http({
                method: "POST",
                url: '../../address/selectCityByProvinceId',
                params : {
                    province_id : $scope.selectParams.province_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.cityList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectAreaByCityId = function (){
            $http({
                method: "POST",
                url: '../../address/selectAreaByCityId',
                params : {
                    city_id : $scope.selectParams.city_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.areaList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.insertMemberAddress = function (){

            $scope.selectParams.address_id = $scope.getUUID();

            $http({
                method: "POST",
                url: '../../memberAddress/insertMemberAddress',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:',response.data.msg,'success');
                $state.go('public.user.address',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.updateMemberAddress = function (){

            $scope.selectParams.address_id = $scope.address_id_get;

            $http({
                method: "POST",
                url: '../../memberAddress/updateMemberAddress',
                params : $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:',response.data.msg,'success');
                $state.go('public.user.address',{},{reload:true});
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.saveAddress = function (){
            if ($scope.address_id_get != ''){
                $scope.updateMemberAddress();
            }else {
                $scope.insertMemberAddress();
            }
        }

        $scope.selectAllProvincesByEdit = function (){
            $http({
                method: "POST",
                url: '../../address/selectAllProvinces',
            }).then(function successCallback(response) {
                //请求成功
                $scope.provinceList = response.data.item;
                $http({
                    method: "POST",
                    url: '../../address/selectCityByProvinceId',
                    params : {
                        province_id : $scope.selectParams.province_id
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.cityList = response.data.item;
                    $scope.selectAreaByCityId();
                }, function errorCallback(response) {
                    //请求失败
                });
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.loadData = function () {

            if ($scope.address_id_get != ''){
                $http({
                    method: "POST",
                    url: '../../memberAddress/selectSingleMemberAddress',
                    params : {
                        address_id : $scope.address_id_get
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.selectParams = response.data.item;
                    $scope.selectParams.phone_number = $scope.selectParams.phone_number * 1; //转为数字
                    $scope.selectAllProvincesByEdit();
                }, function errorCallback(response) {
                    //请求失败
                });
            }else {
                $scope.selectAllProvinces();
            }
        }

        $scope.loadData();


    });