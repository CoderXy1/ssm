angular.module("clothSaleApp")
    .controller("mainCtrl", function ($scope, $rootScope, $state,$http) {

        $scope.spuTotalNum = 0; //商品种类
        $scope.userTotalNum = 0 ; //用户数量
        $scope.orderTotalNum = 0; //订单数量
        $scope.activityTotalNum = 0; //活动数量

        //统计图
        $scope.makeBarChart = function (data) {
            Highcharts.chart('chart-bar',{
                colors:["#03a9f4","#f76b8a","#fa4659","#8971d0","#5873fe","#ffb400","#e4d354","#2b908f","#f45b5b","#91e8e1"],
                chart: {
                    type: 'column'
                },
                xAxis: {
                    categories: [
                        '一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'
                    ],
                    crosshair: true
                },
                yAxis: {
                    min: 0,
                    title: {
                        text: '订单数量(个)'
                    }
                },
                tooltip: {
                    // head + 每个 point + footer 拼接成完整的 table
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0;">{series.name}: </td>' +
                        '<td style="padding:0;"><b>{point.y:.0f} 个</b></td></tr>',
                    footerFormat: '</table>',
                    shared: true,
                    useHTML: true
                },
                plotOptions: {
                    column: {
                        borderWidth: 0
                    }
                },
                series: [{
                    name: '订单数量',
                    data: data
                }]
            });
        }

        $scope.selectAllOrderInfoNumByMonth = function (){
            $http({
                method: "POST",
                url: '../../OrderInfo/selectAllOrderInfoNumByMonth',
                params: {}
            }).then(function successCallback(response) {
                //请求成功
                $scope.makeBarChart(response.data.item)
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        $scope.selectCategorySpuTotal = function (){
            $http({
                method: "POST",
                url: '../../GoodsCategory/selectCategorySpuTotal',
                params: {}
            }).then(function successCallback(response) {
                //请求成功
                $scope.makePieChart(response.data.item)
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        //饼状图
        $scope.makePieChart = function (data){
            //创建渐变色
            Highcharts.getOptions().colors = Highcharts.map(Highcharts.getOptions().colors, function (color) {
                return {
                    radialGradient: { cx: 0.5, cy: 0.3, r: 0.7 },
                    stops: [
                        [0, color],
                        [1, Highcharts.Color(color).brighten(-0.3).get('rgb')] // darken
                    ]
                };
            });
            // 构建图表
            var chart = Highcharts.chart('chart-pie',{
                colors:["#03a9f4","#f76b8a","#fa4659","#8971d0","#5873fe","#ffb400","#e4d354","#2b908f","#f45b5b","#91e8e1"],
                title: {
                    text: ''
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<p>{point.name}</p>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            },
                            connectorColor: 'silver'
                        }
                    }
                },
                series: [{
                    type: 'pie',
                    name: '服装分类占比',
                    data: data
                }]
            });
        }

        $scope.selectSpuTotalNum = function (){
            $http({
                method: "POST",
                url: '../../GoodsSpu/selectGoodsSpuNum',
                params: {}
            }).then(function successCallback(response) {
                //请求成功
                $scope.spuTotalNum = response.data.extdata.total;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        $scope.selectUserTotalNum = function (){
            $http({
                method: "POST",
                url: '../../memberUserinfo/selectAllUserinfoNum',
                params: {}
            }).then(function successCallback(response) {
                //请求成功
                $scope.userTotalNum = response.data.total;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        $scope.selectOrderTotalNum = function (){
            $http({
                method: "POST",
                url: '../../OrderInfo/selectAllOrderInfoNumByUserId',
                params: {}
            }).then(function successCallback(response) {
                //请求成功
                $scope.orderTotalNum = response.data.total;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        $scope.selectActivityTotalNum = function (){
            $http({
                method: "POST",
                url: '../../ActivityInfo/selectNumActivityInfo',
                params: {}
            }).then(function successCallback(response) {
                //请求成功
                $scope.activityTotalNum = response.data.total;
            }, function errorCallback(response) {
                //请求失败
                $scope.showAlert('警告:',response.data.msg,'danger');
            });
        }

        $scope.loadData = function () {
            $scope.selectSpuTotalNum();
            $scope.selectUserTotalNum();
            $scope.selectOrderTotalNum();
            $scope.selectActivityTotalNum();
            $scope.selectCategorySpuTotal();
            $scope.selectAllOrderInfoNumByMonth();
        }

        $scope.loadData();

    });
