angular.module("clothSaleApp")
    .controller("goodsSkuAddCtrl", function ($scope, $rootScope, $http, $alert) {


        $scope.initData = function (){
            $scope.form2Params = {
                spu_id: '',
                goods_name: '',
                low_price: '',
                spu_icon_id: '',
                brand_id: '',
                category_id: '',
                spu_order: '',
                specIds: {}
            }
            $scope.form3Params = {
                sku_id: '',
                spu_id: '',
                price: '',
                stock: '',
                specIds: {}
            }
            $scope.form3SpecList = {
                spec_id: [],
                spec_name: [],
                spec_value: []
            };
        }
        $scope.initData();
        $scope.addSkuType = 1;
        $scope.addSpuStep = 1;
        $scope.isChecked = false;
        $scope.isSelectSpu = false;
        $scope.spu_id = '';
        $scope.categoryList = [];
        $scope.brandList = [];
        $scope.skuList = [];
        $scope.specList = [];
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 5,
            pageNum: 1,
            totalNum: 0,
            category_id: '',
            brand_id: '',
        }
        $scope.form1Params = {
            sku_id: '',
            stock: '',
        }


        //查询spu
        $scope.selectGoodsSpu = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpu',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuList = response.data.item;
                $scope.selectParams.totalNum = response.data.extdata==null?0:response.data.extdata.total;
                angular.forEach($scope.spuList, function (item, index) {
                    $scope.spuList[index]['isChecked'] = false;
                });
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询分类
        $scope.selectCategory = function () {
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params: {
                    pageIndex: 0,
                    pageSize: 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询品牌
        $scope.selectBrand = function () {
            $http({
                method: "POST",
                url: '../../GoodsBrand/selectGoodsBrand',
                params: {
                    pageIndex: 0,
                    pageSize: 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.brandList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询规格
        $scope.selectSpecByCategory = function () {

            $http({
                method: "POST",
                url: '../../GoodsSpec/selectAllGoodsSpec',
                params: {
                    pageIndex: 0,
                    pageSize: 1000,
                    category_id: $scope.form2Params.category_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.specList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //添加spu
        $scope.insertGoodsSpu = function () {

            var flag = false;
            for (var item in $scope.form2Params.specIds) {
                var value = $scope.form2Params.specIds[item];
                if (value != null) {
                    $scope.form2Params.spu_id = $scope.getUUID();
                    $scope.form2Params.spu_icon_id = $scope.getUUID();

                    $http({
                        method: "POST",
                        url: '../../GoodsSpu/insertGoodsSpu',
                        params: $scope.form2Params
                    }).then(function successCallback(response) {
                        //请求成功
                        $scope.insertFile($scope.form2Params.spu_icon_id, 'goods_icon', '../../');
                        $scope.form3Params.spu_id = $scope.form2Params.spu_id;
                        $scope.selectGoodsSpecBySpuId();
                        $scope.addSpuStep = 2;
                    }, function errorCallback(response) {
                        //请求失败
                    });
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                $scope.showAlert('错误:', '请选择规格', 'danger');
            }
        }

        //通过spu_id查询规格
        $scope.selectGoodsSpecBySpuId = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpec/selectGoodsSpecAndValue',
                params: {
                    spu_id: $scope.form3Params.spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                angular.forEach(response.data.item, function (value, key) {
                    $scope.form3SpecList.spec_id.push(value.spec_id);
                    $scope.form3SpecList.spec_name.push(value.spec_name);
                    $scope.temp = [];
                    if (value.spec_value != undefined){
                        angular.forEach(value.spec_value.split(","), function (value1, key) {
                            $scope.temp.push(value1.split(":"));
                        })
                    }
                    $scope.form3SpecList.spec_value.push($scope.temp);
                })
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.toForm3 = function (spu_id){

            $scope.initData();
            $scope.form3Params.spu_id = spu_id;
            $scope.selectGoodsSpecBySpuId();
            $scope.addSkuType = 2;
            $scope.addSpuStep = 2;
        }

        $scope.addGoodsSku = function (spec, specId) {
            $scope.form3Params.specIds[spec] = specId;
        }

        //查询sku
        $scope.selectSku = function (spu_id) {

            $scope.spu_id = spu_id;
            $scope.isSelectSpu = true;

            $http({
                method: "POST",
                url: '../../GoodsSku/selectAllSku',
                params: {
                    pageIndex: 0,
                    pageSize: 1000,
                    spu_id: spu_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.skuList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.updateSku = function () {
            $http({
                method: "POST",
                url: '../../GoodsSku/updateGoodsSku',
                params: $scope.form1Params
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:', response.data.msg, 'success');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:', response.data.msg, 'danger');
            });
        }

        //添加sku
        $scope.insertSku = function () {

            $scope.form3Params.sku_id = $scope.getUUID();

            $http({
                method: "POST",
                url: '../../GoodsSku/insertGoodsSku',
                params: $scope.form3Params
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:', response.data.msg, 'success');
                $scope.addSpuStep = 1;
                $scope.initData();
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('错误:', response.data.msg, 'danger');
            });
        }

        $scope.addGoodsSpecId = function (isSelected, spec, specId) {

            if (isSelected) {
                $scope.form2Params.specIds[spec] = specId;
            } else {
                $scope.form2Params.specIds[spec] = null;
            }

        }

        $scope.specListClear = function () {
            $scope.spu_id = '';
            $scope.isSelectSpu = false;
        }

        $scope.searchSpu = function () {
            $scope.selectParams.pageIndex = 0;
            $scope.specListClear();
            $scope.selectGoodsSpu();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.specListClear();
            $scope.selectBrand();
            $scope.selectCategory();
            $scope.selectGoodsSpu();
        }

        $scope.loadData();

    });