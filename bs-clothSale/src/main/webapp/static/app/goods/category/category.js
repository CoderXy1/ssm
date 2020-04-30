angular.module("clothSaleApp")
    .controller("goodsCategoryCtrl", function ($scope, $rootScope, $http, $alert) {

        $scope.categoryList = [];
        $scope.categoryFirstList = [];
        $scope.allCategoryFirstList = [];
        $scope.categoryStep = 1;
        $scope.GoodsCategoryFirst = {};
        $scope.selectParams = {
            pageIndex: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            categoryName: '',
        }
        $scope.selectFirstParams = {
            pageIndex: 0,
            pageSize: 10,
            pageNum: 1,
            totalNum: 0,
            categoryFirstName: '',
        }
        $scope.category_id_edit = '';

        //添加多个一级标签
        $scope.addGoodsCategoryFirst = function (isSelected, CategoryFirstId, CategoryFirstName) {

            if (isSelected) {
                $scope.GoodsCategoryFirst[CategoryFirstName] = CategoryFirstId;
            } else {
                $scope.GoodsCategoryFirst[CategoryFirstName] = null;
            }

        }

        //新增分类
        $scope.insertCategory = function () {

            if ($scope.categoryStep == 1) {
                $http({
                    method: "POST",
                    url: '../../GoodsCategory/insertCategoryFirst',
                    params: {
                        categoryFirstId: $scope.getUUID(),
                        categoryFirstName: $scope.editCategory_name,
                        categoryFirstOrder: $scope.category_order,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:', response.data.msg, 'success');
                    $scope.loadData();
                    $('#addModal').modal('hide');
                }, function errorCallback(response) {
                    //请求失败
                });
            } else {
                var flag = false;
                for (var item in $scope.GoodsCategoryFirst) {
                    var value = $scope.GoodsCategoryFirst[item];
                    if (value != null) {
                        $http({
                            method: "POST",
                            url: '../../GoodsCategory/insertGoodsCategory',
                            params: {
                                category_id: $scope.getUUID(),
                                category_name: $scope.editCategory_name,
                                category_order: $scope.category_order,
                                category_first_id: $scope.GoodsCategoryFirst,
                            }
                        }).then(function successCallback(response) {
                            //请求成功
                            $scope.showAlert('提示:', response.data.msg, 'success');
                            $scope.loadData();
                            $('#addModal').modal('hide');
                            $scope.GoodsCategoryFirst = {}; //清空
                        }, function errorCallback(response) {
                            //请求失败
                            $scope.GoodsCategoryFirst = {}; //清空
                        });
                    }
                    flag = true;
                    break;
                }
                if (!flag) {
                    $scope.showAlert('错误:', '请至少选择一个一级分类', 'danger');
                }
            }
        }

        //删除分类
        $scope.deleteCategory = function (category_id) {

            if (confirm("将会同时删除该分类下所有服饰，确定删除")) {
                $http({
                    method: "POST",
                    url: '../../GoodsCategory/deleteAllGoodsCategory',
                    params: {
                        category_id: category_id,
                        categoryStep: $scope.categoryStep,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:', response.data.msg, 'success');
                    $scope.loadData();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('警告:', response.data.msg, 'danger');
                });
            }

        }

        $scope.updateCategory = function (category_id,category_name,category_order) {
            $('#editModal').modal('show');
            $scope.category_name_edit = category_name;
            $scope.category_order_edit = category_order;
            $scope.category_id_edit = category_id;
        }

        //修改分类
        $scope.saveUpdateCategory = function () {

            $http({
                method: "POST",
                url: '../../GoodsCategory/updateAllCategory',
                params: {
                    categoryId: $scope.category_id_edit,
                    category_name_edit: $scope.category_name_edit,
                    category_order_edit: $scope.category_order_edit,
                    categoryStep: $scope.categoryStep,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.showAlert('提示:', response.data.msg, 'success');
                $scope.loadData();
                $('#editModal').modal('hide');
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:', response.data.msg, 'danger');
            });

        }


        //查询二级分类
        $scope.selectCategory = function () {
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategory',
                params: $scope.selectParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryList = response.data.item;
                if ($scope.categoryList != null && $scope.categoryList != '') {
                    $scope.selectParams.totalNum = $scope.categoryList[0].total;
                } else {
                    $scope.selectParams.totalNum = 0;
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询一级分类
        $scope.selectFirstCategory = function () {
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategoryFirst',
                params: $scope.selectFirstParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryFirstList = response.data.item;
                if ($scope.categoryFirstList != null && $scope.categoryFirstList != '') {
                    $scope.selectFirstParams.totalNum = $scope.categoryFirstList[0].total;
                } else {
                    $scope.selectFirstParams.totalNum = 0;
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        //查询所有一级分类
        $scope.selectAllFirstCategory = function () {
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategoryFirst',
                params: {
                    pageIndex: 0,
                    pageSize: 1000,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.allCategoryFirstList = response.data.item;
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchCategory = function () {
            if ($scope.categoryStep == 1) {
                $scope.selectFirstParams.pageIndex = 0;
                $scope.selectFirstCategory();
            } else {
                $scope.selectParams.pageIndex = 0;
                $scope.selectCategory();
            }
        }

        $scope.loadData = function () {
            if ($scope.categoryStep == 1) {
                $scope.selectFirstParams.pageIndex = ($scope.selectFirstParams.pageNum - 1) * $scope.selectFirstParams.pageSize;
            } else {
                $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            }
            $scope.selectFirstCategory();
            $scope.selectAllFirstCategory();
            $scope.selectCategory();
        }

        $scope.loadData();

    });