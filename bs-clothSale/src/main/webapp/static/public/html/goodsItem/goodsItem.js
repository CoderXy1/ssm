angular.module("clothSalePublicApp")
    .controller("goodsItemCtrl", function ($scope, $rootScope, $state, $stateParams, $http) {

        $scope.spu_id = $stateParams.spu_id == undefined ? '' : $stateParams.spu_id;
        $scope.spuInfo = {};
        $scope.total_num = 1;
        $scope.specList = {
            spec_id: [],
            spec_name: [],
            spec_value: []
        };
        $scope.selectSpecValue = [];
        $scope.goodsSku = '';

        $scope.changeSelectSpecValue = function (index, selectSpecValue) {
            if ($scope.selectSpecValue[index] == selectSpecValue){
                $scope.selectSpecValue[index] = null;
            }else {
                $scope.selectSpecValue[index] = selectSpecValue;
            }
            $scope.selectSkuBySpecSpu();
        }

        $scope.addCart = function (){

            if (sessionStorage.getItem("token_user")){
                if ($scope.goodsSku != null && $scope.goodsSku != ''){
                    if ($scope.goodsSku[0].stock < $scope.total_num){
                        $scope.showAlert('错误','库存不足，请重新选择数量','danger');
                    }else {
                        $http({
                            method: "POST",
                            url: '../../OrderCart/insertOrderCart',
                            params: {
                                cart_id : $scope.getUUID(),
                                user_id : JSON.parse(sessionStorage.getItem("user_info"))[0].user_id,
                                sku_id : $scope.goodsSku[0].sku_id,
                                total_num : $scope.total_num
                            }
                        }).then(function successCallback(response) {
                            //请求成功
                            $scope.showAlert('提示',response.data.msg,'success');
                        }, function errorCallback(response) {
                            //请求失败
                            $scope.showAlert('错误',response.data.msg,'danger');
                        });
                    }
                }else {
                    $scope.showAlert('提示','暂无该规格商品','danger');
                }
            }else {
                $state.go('public.login',{url:'public.goodsItem',extraData:'{"spu_id":"'+$stateParams.spu_id + '"}'});
            }

        }

        $scope.selectSingleSpu = function () {

            $http({
                method: "POST",
                url: '../../GoodsSpu/selectSingleGoodsSpu',
                params: {
                    spu_id: $scope.spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                angular.forEach(response.data.item, function (value, key) {
                    $scope.specList.spec_id.push(value.spec_id);
                    $scope.specList.spec_name.push(value.spec_name);
                    $scope.temp = [];
                    $scope.selectSpecValue.push(null);
                    angular.forEach(value.spec_value.split(","), function (value1, key) {
                        $scope.temp.push(value1.split(":"));
                    })
                    $scope.specList.spec_value.push($scope.temp);
                })
                $scope.spuInfo = response.data.extdata;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectSkuBySpecSpu = function () {
            $http({
                method: "POST",
                url: '../../GoodsSku/selectSkuBySpecSpu',
                params: {
                    specIds: $scope.selectSpecValue,
                    spu_id: $scope.spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.goodsSku = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            })
        }

        $scope.insertOrderCollect = function () {
            $http({
                method: "POST",
                url: '../../OrderCart/insertOrderCollect',
                params: {
                    collect_id: $scope.getUUID(),
                    user_id : $scope.getUserInfoBySession().user_id,
                    spu_id: $scope.spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('成功:',response.data.msg,'success');
            }, function errorCallback(response) {
                //请求失败
            })
        }

        $scope.changeTotalNum = function (flag){
            if (flag == 1){
                $scope.total_num++;
            }else {
                if ($scope.total_num > 1){
                    $scope.total_num--;
                }
            }
        }

        $scope.loadData = function () {
            $scope.selectSingleSpu();
        };

        $scope.loadData();

        //添加一个点击事件
        $(".color_ul li").click(function () {
            //切换颜色
            $(this).addClass("on").siblings().removeClass("on");
        });
    });