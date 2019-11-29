angular.module('detailApp', [])
    .config(['$locationProvider', function ($locationProvider) {

        $locationProvider.html5Mode(true);

    }])
    .controller('detailCtrl', function ($scope, $http, $timeout, $interval, $location) {

        $scope.specIds = {};
        $scope.tip = $location.search();
        $scope.specList = {
            spec_id : [],
            spec_name : [],
            spec_value : []
        };
        $scope.selectParms = {
            pageIndex: 0,
            pageSize: 10,
        }
        angular.forEach($scope.tip,function (value, key) {
            $scope.selectParms[key] = value;
        })

        //生成随机字母数字
        $scope.genUUID = function () {
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = (d + Math.random() * 16) % 16 | 0;
                d = Math.floor(d / 16);
                return (c == 'x' ? r : (r & 0x7 | 0x8)).toString(16);
            });
            return uuid;
        }

        $scope.selectGoodsSpu = function () {
            $http({
                method: "POST",
                url: 'GoodsSpec/selectGoodsSpecAndValue',
                params: $scope.selectParms
            }).then(function successCallback(response) {
                //请求成功
                //$scope.specList = response.data.item;
                angular.forEach(response.data.item,function (value, key) {
                    $scope.specList.spec_id.push(value.spec_id);
                    $scope.specList.spec_name.push(value.spec_name);
                    $scope.temp = [];
                    angular.forEach(value.spec_value.split(","),function (value1, key) {
                        $scope.temp.push(value1.split(":"));

                    })
                    $scope.specList.spec_value.push($scope.temp);
                })

            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.addGoodsSku = function (spec,specId) {

            $scope.specIds[spec] = specId;

        }

        $scope.selectGoodsSku = function (){
            $http({
                method: "POST",
                url: 'GoodsSku/selectGoodsSku',
                params : {
                    specIds : $scope.specIds,
                    spu_id : $scope.selectParms['spu_id']
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.goodsSku = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.price = "";
        $scope.stock = "";
        $scope.insertGoodsSku = function (){
            $http({
                method: "POST",
                url: 'GoodsSku/insertGoodsSku',
                params : {
                    sku_id : $scope.genUUID(),
                    spu_id : $scope.selectParms['spu_id'],
                    price : $scope.price,
                    stock : $scope.stock,
                    specIds : $scope.specIds,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.goodsSku = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectGoodsSpu();



    })
