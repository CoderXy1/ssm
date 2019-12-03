angular.module("clothSaleApp")
    .controller("goodsCategoryCtrl", function ($scope,$rootScope,$http,$alert) {

        $scope.categoryList = [];
        $scope.selectParams = {
            pageIndex : 0,
            pageSize : 10,
            pageNum : 1,
            totalNum : 0,
            categoryName : '',
        }

        //新增分类
        $scope.insertCategory = function (){
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
                $scope.showAlert('提示:',response.data.msg);
                $scope.loadData();
                $('#addModal').modal('hide');
            }, function errorCallback(response) {
                //请求失败
            });
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
                    $scope.showAlert('提示:',response.data.msg);
                    $scope.loadData();
                }, function errorCallback(response) {
                    //请求失败
                    $scope.showAlert('警告:',response.data.msg);
                });
            }

        }

        //查询分类
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

        $scope.searchCategory = function (){
            $scope.selectParams.pageIndex = 0;
            $scope.selectCategory();
        }

        $scope.loadData = function () {
            $scope.selectParams.pageIndex = ($scope.selectParams.pageNum - 1) * $scope.selectParams.pageSize;
            $scope.selectCategory();
        }

        $scope.loadData();

    });