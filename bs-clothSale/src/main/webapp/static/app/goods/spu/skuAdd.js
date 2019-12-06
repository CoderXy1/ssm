angular.module("clothSaleApp")
    .controller("goodsSkuAddCtrl", function ($scope, $rootScope, $http, $alert) {

        $scope.addSkuType = 1;
        $scope.isChecked = false;
        $scope.isSelectSpu = false;
        $scope.spu_id = '';
        $scope.specIds = {};
        $scope.categoryList = [];
        $scope.brandList = [];
        $scope.skuList = [];
        $scope.specList = {
            spec_id: [],
            spec_name: [],
            spec_value: []
        };
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 5,
            pageNum: 1,
            totalNum: 0,
            category_id: '',
            brand_id: '',
        }
        $scope.form1Params = {
            sku_id : '',
            stock : '',
        }
        $scope.form2Params = {
            category_id: '',
            brand_id: '',
        }
        //新增规格
        $scope.insertSpec = function () {
            $http({
                method: "POST",
                url: '../../GoodsSpec/insertGoodsSpec',
                params: {
                    spec_id: $scope.getUUID(),
                    spec_name: $scope.editSpec_name,
                    category_id: $scope.editCategory_id,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.loadData();
                $('#addModal').modal('hide');
                $scope.showAlert('提示:', response.data.msg), 'success';
            }, function errorCallback(response) {
                //请求失败
            });
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
                $scope.selectParams.totalNum = response.data.extdata.total;
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
        $scope.selectSpecBySpuId = function (spu_id) {

            $scope.specListClear();
            $scope.spu_id = spu_id;
            $scope.isSelectSpu = true;

            $http({
                method: "POST",
                url: '../../GoodsSpec/selectGoodsSpecAndValue',
                params: {
                    spu_id: spu_id
                }
            }).then(function successCallback(response) {
                //请求成功
                angular.forEach(response.data.item, function (value, key) {
                    $scope.specList.spec_id.push(value.spec_id);
                    $scope.specList.spec_name.push(value.spec_name);
                    $scope.temp = [];
                    angular.forEach(value.spec_value.split(","), function (value1, key) {
                        $scope.temp.push(value1.split(":"));
                    })
                    $scope.specList.spec_value.push($scope.temp);
                })

            }, function errorCallback(response) {
                //请求失败
            });
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

        $scope.insertSku = function () {
            if (!$scope.isSelectSpu) {
                $scope.showAlert('错误:', '选择商品', 'warning');
            } else {
                $http({
                    method: "POST",
                    url: 'GoodsSku/insertGoodsSku',
                    params: {
                        sku_id: $scope.getUUID(),
                        spu_id: $scope.spu_id,
                        price_input: $scope.price_input,
                        price_sale: $scope.price_sale,
                        stock: $scope.stock,
                        specIds: $scope.specIds,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:', response.data.msg, 'success');
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('错误:', response.data.msg, 'danger');
                });
            }
        }

        $scope.addGoodsSpecId = function (spec, specId) {

            $scope.specIds[spec] = specId;

        }

        $scope.specListClear = function () {
            $scope.specList = {
                spec_id: [],
                spec_name: [],
                spec_value: []
            };
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