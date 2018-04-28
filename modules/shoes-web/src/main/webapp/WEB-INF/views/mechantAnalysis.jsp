<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-4-4
  Time: 上午1:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>商品管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/bootstrap/css/bootstrap.css">
    <!--[if IE]>
    <script src="<%=basePath%>js/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    <!-- layui -->
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css"  media="all">
    <link rel="stylesheet" href="<%=basePath%>css/common.css"  media="all">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
    <script src="<%=basePath%>js/echarts.min.js"  charset="utf-8"></script>
</head>
<body class="layui-layout-body">
    <div class="layui-body" style="left:0px;background-color: #f1f1f1">
        <!-- 内容主体区域 -->
        <div class="row" style="height: 50%;padding-left:25px;padding-top:10px;">
            <div id="outStore" class="col-lg-8" style="height:100%;border:1px solid; border-color: #cccccc;border-radius:10px; background-color: #ffffff;"></div>
            <div class="col-lg-4 flex-container"  style="height: 100%">
                <div id="inStoreNum" style="margin-right: 10px;padding:10px; height:100%; background-color: #ffffff; border:1px solid; border-color: #cccccc;border-radius:10px;">
                   <div class="col-sm-6">
                       <span style="font-family: initial;font-size:40px;">入库金额</span><br>
                       <span style="font-family: serif;font-size:  40px;font-style: italic;">￥1200</span>
                   </div>
                    <div class="col-sm-6">
                        <img src="<%=basePath%>image/instore1.jpg" style="width:auto;height: auto;">
                    </div>
                </div>
                <div id="outStoreNUm" style="margin-top: 10px;margin-right: 10px;padding: 10px;height:100%; background-color: #ffffff; border:1px solid; border-color: #cccccc;border-radius:10px; ">
                    <div class="col-sm-6">
                        <span style="font-family: initial;font-size:40px;">出库金额</span><br>
                        <span style="font-family: serif;font-size:  40px;font-style: italic;">￥1200</span>
                    </div>
                    <div class="col-sm-6">
                        <img src="<%=basePath%>image/outstore1.jpg" style="width:auto;height: auto;">
                    </div>
                </div>
            </div>
        </div>
        <div class="row" style="height: 50%; background-color: #f1f1f1;padding-left:25px;padding-top:10px;">
            <div id="openMenuNum"class="col-lg-8"  style="padding: 10px;height:100%;background-color: #ffffff;border: 1px solid;border-color: #cccccc;border-radius:  10px; "></div>
            <div class="col-lg-4">
                <div id="inStore" style="margin-right: 10px;padding:10px;height:100%;background-color: #ffffff;border: 1px solid;border-color: #cccccc;border-radius:  10px; "></div>
            </div>
        </div>
    </div>
    <script  type="text/javascript">
        //JavaScript代码区域
        layui.use('element', function(){
            var element = layui.element;

        });
        var dataAxis = ['一', '二', '三', '四', '五', '六', '七'];
        var data = [220, 182, 191, 234, 290, 330, 310];
        var yMax = 500;
        var dataShadow = [];

        for (var i = 0; i < data.length; i++) {
            dataShadow.push(yMax);
        }
        var myChart = echarts.init(document.getElementById("outStore"));
        option = {
            title: {
                text: '特性示例：渐变色 阴影 点击缩放',
                subtext: 'Feature Sample: Gradient Color, Shadow, Click Zoom'
            },
            xAxis: {
                data: dataAxis,
                axisLabel: {
                    inside: true,
                    textStyle: {
                        color: '#fff'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: false
                },
                z: 10
            },
            yAxis: {
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    textStyle: {
                        color: '#999'
                    }
                }
            },
            dataZoom: [
                {
                    type: 'inside'
                }
            ],
            series: [
                { // For shadow
                    type: 'bar',
                    itemStyle: {
                        normal: {color: 'rgba(0,0,0,0.05)'}
                    },
                    barGap:'-100%',
                    barCategoryGap:'40%',
                    data: dataShadow,
                    animation: false
                },
                {
                    type: 'bar',
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#83bff6'},
                                    {offset: 0.5, color: '#188df0'},
                                    {offset: 1, color: '#188df0'}
                                ]
                            )
                        },
                        emphasis: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1,
                                [
                                    {offset: 0, color: '#2378f7'},
                                    {offset: 0.7, color: '#2378f7'},
                                    {offset: 1, color: '#83bff6'}
                                ]
                            )
                        }
                    },
                    data: data
                }
            ]
        };
        myChart.setOption(option);

        option2 = {
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
            },
            yAxis: {
                type: 'value'
            },
            series: [{
                data: [820, 932, 901, 934, 1290, 1330, 1320],
                type: 'line',
                areaStyle: {}
            }]
        };
        var myChart2 = echarts.init(document.getElementById("inStore"));
        myChart2.setOption(option2);
        option1 = {
            title : {
                text: '某站点用户访问来源',
                subtext: '纯属虚构',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: ['一','二','三','四','五']
            },
            series : [
                {
                    name: '访问来源',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:[
                        {value:335, name:'一'},
                        {value:310, name:'二'},
                        {value:234, name:'三'},
                        {value:135, name:'四'},
                        {value:1548, name:'五'}
                    ],
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                }
            ]
        };
        var myChart1 = echarts.init(document.getElementById("openMenuNum"));
        myChart1.setOption(option1);
    </script>
</body>
</html>
