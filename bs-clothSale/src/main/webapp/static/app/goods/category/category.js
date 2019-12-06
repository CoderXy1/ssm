angular.module("clothSaleApp")
    .controller("goodsCategoryCtrl", function ($scope,$rootScope,$http,$alert) {

        $scope.categoryList = [];
        $scope.categoryFirstList = [];
        $scope.categoryStep = 1;
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            categoryName : '',
        }
        $scope.selectFirstParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            categoryFirstName : '',
        }

        //新增分类
        $scope.insertCategory = function (){

            if ($scope.categoryStep == 1){
                $http({
                    method: "POST",
                    url: '../../GoodsCategory/insertCategoryFirst',
                    params: {
                        categoryFirstId : $scope.getUUID(),
                        categoryFirstName : $scope.editCategory_name,
                        categoryFirstOrder : $scope.category_order,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:',response.data.msg,'success');
                    $scope.loadData();
                    $('#addModal').modal('hide');
                }, function errorCallback(response) {
                    //请求失败
                });
            }else {
                $http({
                    method: "POST",
                    url: '../../GoodsCategory/insertGoodsCategory',
                    params: {
                        category_id : $scope.getUUID(),
                        category_name : $scope.editCategory_name,
                        category_order : $scope.category_order,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:',response.data.msg,'success');
                    $scope.loadData();
                    $('#addModal').modal('hide');
                }, function errorCallback(response) {
                    //请求失败
                });
            }
        }

        //删除分类
        $scope.deleteCategory = function (category_id){

            if (confirm("将会同时删除该分类下所有商品，确定删除")){
                $http({
                    method: "POST",
                    url: '../../GoodsCategory/deleteGoodsCategory',
                    params: {
                        category_id : category_id,
                    }
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.showAlert('提示:',response.data.msg,'success');
                    $scope.loadData();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('警告:',response.data.msg,'danger');
                });
            }

        }

        //查询二级分类
        $scope.selectCategory = function (){
                $http({
                    method: "POST",
                    url: '../../GoodsCategory/selectGoodsCategory',
                    params : $scope.selectParams
                }).then(function successCallback(response) {
                    //请求成功
                    $scope.categoryList = response.data.item;
                    if ($scope.categoryList != null &&  $scope.categoryList != ''){
                        $scope.selectParams.totalNum = $scope.categoryList[0].total;
                    }else {
                        $scope.selectParams.totalNum = 0;
                    }
                }, function errorCallback(response) {
                    //请求失败
                });
        }

        //查询一级分类
        $scope.selectFirstCategory = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectGoodsCategoryFirst',
                params : $scope.selectFirstParams
            }).then(function successCallback(response) {
                //请求成功
                $scope.categoryFirstList = response.data.item;
                if ($scope.categoryFirstList != null &&  $scope.categoryFirstList != ''){
                    $scope.selectFirstParams.totalNum = $scope.categoryFirstList[0].total;
                }else {
                    $scope.selectFirstParams.totalNum = 0;
                }
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.searchCategory = function (){
            if ($scope.categoryStep == 1){
                $scope.selectFirstParams.pageIndex = 0;
                $scope.selectFirstCategory();
            }else {
                $scope.selectParams.pageIndex = 0;
                $scope.selectCategory();
            }
        }

        $scope.loadData = function () {
            if ($scope.categoryStep == 1){
                $scope.selectFirstParams.pageIndex = ($scope.selectFirstParams.pageNum - 1) * $scope.selectFirstParams.pageSize;
            }else {
                $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            }
            $scope.selectFirstCategory();
            $scope.selectCategory();
        }

        $scope.loadData();

    });