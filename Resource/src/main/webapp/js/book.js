angular.module('bookApp', ['ui.bootstrap'])
    .controller('bookCtrl', function ($scope, $http) {

        $scope.allBook = "";
        $scope.bookNum = 0;
        $scope.pages = 0;
        $scope.arrayPage = new Array();
        $scope.tip = "";
        $scope.selPage = 1;
        $scope.selectparams = {

            'bookName': '',
            'pageIndex': 0,
            'pageSize': 50,
            'pageNum' : 1,
            'totalNum' : 0,
        }

        $scope.getBookNum = function () {
            $http({
                method: "POST",
                url: '../../book/selectBookNum',
                params: {
                    'bookName': $scope.selectparams.bookName,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.selectparams.totalNum = response.data;
                $scope.tip = "搜索结果 总共查询到" + $scope.selectparams.totalNum + "条记录";
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectBook = function () {

            $scope.selectparams.pageIndex = ($scope.selectparams.pageNum - 1) * $scope.selectparams.pageSize;

            $scope.getBookNum();

            $http({
                method: "POST",
                url: '../../book/selectBookByNameAndPage',
                params: $scope.selectparams,
            }).then(function successCallback(response) {
                //请求成功
                if (response.data != null || response.data != "") {
                    $scope.allBook = response.data;
                }
            }, function errorCallback(response) {
                //请求失败
            });

        }

        //初始化数据
        $scope.selectBook();

    });