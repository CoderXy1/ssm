angular.module("clothSaleApp")
    .controller("mainCtrl", function ($scope, $rootScope, $state,$http) {
        //统计图
        $scope.makeBarChart = function () {
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
                        text: '销售量(件)'
                    }
                },
                tooltip: {
                    // head + 每个 point + footer 拼接成完整的 table
                    headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                    pointFormat: '<tr><td style="color:{series.color};padding:0;">{series.name}: </td>' +
                        '<td style="padding:0;"><b>{point.y:.1f} 件</b></td></tr>',
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
                    name: '销售额',
                    data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54]
                },{
                    name: '进货额',
                    data: [60, 73, 105, 112, 150, 150, 111, 120, 250, 190, 231, 58]
                }]
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

        $scope.loadData = function () {
            $scope.selectCategorySpuTotal();
            $scope.makeBarChart();
        }

        $scope.loadData();

    });
