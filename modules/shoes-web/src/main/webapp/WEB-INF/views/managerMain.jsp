<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-3-6
  Time: 下午11:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shoes circle</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
    <!--[if IE]>
    <script src="<%=basePath%>js/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    <!-- layui -->
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css"  media="all">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="layui-row bodyContent">
            <div id = "userName" style="width: 50%; height: 40px; text-align: left; float: left"><span>欢迎：${userName}</span></div>
            <div style=" height: 40px; text-align: right;"><a href="">修改密码</a> <a href="<%=basePath%>index/logout">退出登录</a></div>
        </div>
        <div class="layui-row bodyContent" style="padding-top: 10px;">
            <ul class="layui-nav layui-bg-green">
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=mearchant" target="_blank">商家管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=credit" target="_blank">信用管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=brand" target="_blank">品牌管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=size" target="_blank">尺码管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=type" target="_blank">分类管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=advert" target="_blank">广告管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=account" target="_blank">账户管理</a></li>
                <li class="layui-nav-item"><a href="<%=basePath%>manage/main1?path=manageAnalysis" target="_blank">智能分析</a></li>
            </ul>
        </div>
        <div class="layui-row">
            <div class="bodyContent">
                <div class="layui-col-xs12" style="">
                    <div class="layui-carousel" id="test10">
                        <div carousel-item="">
                            <div><img src="<%=basePath%>image/111.jpg" style = "width: 100%; height: 100%;"></div>
                            <div><img src="<%=basePath%>image/222.jpg" style = "width: 100%; height: 100%;"></div>
                            <div><img src="<%=basePath%>image/333.jpg" style = "width: 100%; height: 100%;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['carousel', 'form'], function(){
        var carousel = layui.carousel
            ,form = layui.form;

        //图片轮播
        carousel.render({
            elem: '#test10'
            ,width: '100%'
            ,height: '300px'
            ,interval: 5000
        });

    });
</script>
</body>
</html>
