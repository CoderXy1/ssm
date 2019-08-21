angular.module('bookApp', [])
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

        }

        //设置当前选中页样式
        $scope.isActivePage = function (page) {
            return $scope.selPage == page;
        };

        $scope.getBookNum = function () {
            $http({
                method: "POST",
                url: '../../book/selectBookNum',
                params: {
                    'bookName': $scope.selectparams.bookName,
                }
            }).then(function successCallback(response) {
                //请求成功
                $scope.bookNum = response.data;
                $scope.pages = Math.ceil($scope.bookNum / $scope.selectparams.pageSize);
                if ($scope.tip == "") {
                    $scope.arrayPage = new Array();
                    for (var i = 0; i < ($scope.pages > 5 ? 5 : $scope.pages); i++) {
                        $scope.arrayPage.push(i + 1);
                    }
                }
                $scope.tip = "搜索结果 总共查询到" + $scope.bookNum + "条记录";
            }, function errorCallback(response) {
                //请求失败
            });
        }

        $scope.selectBook = function () {

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

        $scope.setPage = function (page) {
            $scope.selPage = page;
            $scope.selectPage($scope.selPage);
            $scope.isActivePage($scope.selPage);
            //不能小于1大于最大
            if (page < 1 || page > $scope.pages) return;
            if (page > 2) {  //最多显示分页数5
                //因为只显示5个页数，大于2页开始分页转换
                var newpageList = [];
                for (var i = (page - 3); i < ((page + 2) > $scope.pages ? $scope.pages : (page + 2)); i++) {
                    newpageList.push(i + 1);
                }
                $scope.arrayPage = newpageList;
            }else {
                $scope.arrayPage = new Array();
                for (var i = 0; i < ($scope.pages > 5 ? 5 : $scope.pages); i++) {
                    $scope.arrayPage.push(i + 1);
                }
            }
        }

        $scope.selectPage = function (page) {

            $scope.selectparams.pageIndex = (page - 1) * $scope.selectparams.pageSize;
            $scope.selectBook();

        }

        $scope.previousPage = function () {
            if ($scope.selectparams.pageIndex > 0) {
                $scope.setPage($scope.selPage - 1);
            }
        }

        $scope.nextPage = function () {
            if ($scope.selectparams.pageIndex + $scope.selectparams.pageSize <= $scope.bookNum) {
                $scope.setPage($scope.selPage + 1);
            }
        }

        //初始化数据
        $scope.selectPage(1);

    });