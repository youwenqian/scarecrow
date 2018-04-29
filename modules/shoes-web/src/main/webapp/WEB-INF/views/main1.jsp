<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-4-15
  Time: 下午2:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shoes circle</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
    <!--[if IE]>
    <script src="<%=basePath%>js/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    <!-- layui -->
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css"  media="all">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">shoes circle</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <div id = "userName" style="padding-left:20px;width: 50%; height: 40px; text-align: left; float: left; color:#999;"><span>欢迎：${userName}</span></div>
        <div class="layui-nav layui-layout-right" ><a style="padding-right:30px; color:#999;" href="">修改密码</a> <a style=" color:#999;" href="<%=basePath%>index/logout">退出登录</a></div>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed"><a id="mearchant" href="<%=basePath%>manage/mearchant" target="content">商家管理</a></li>
                <li class="layui-nav-item"><a id="credit" href="<%=basePath%>manage/credit" target="content">信用管理</a></li>
                <li class="layui-nav-item"><a id="brand" href="<%=basePath%>manage/brand" target="content">品牌管理</a></li>
                <li class="layui-nav-item"><a id="size" href="<%=basePath%>manage/size" target="content">尺码管理</a></li>
                <li class="layui-nav-item"><a id="type" href="<%=basePath%>manage/type" target="content">分类管理</a></li>
                <li class="layui-nav-item"><a id="advert" href="<%=basePath%>manage/advert" target="content">广告管理</a></li>
                <li class="layui-nav-item"><a id="account" href="<%=basePath%>manage/account" target="content">账户管理</a></li>
                <li class="layui-nav-item"><a id="manageAnalysis" href="<%=basePath%>manage/manageAnalysis" target="content">智能分析</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body" style="background-color: #f1f1f1">
        <iframe name="content" style="width:100%;height:-webkit-fill-available;" ></iframe>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        var path = "<%=request.getParameter("path")%>";
        console.log(path);
        document.getElementById(path).click();
    });
</script>
</body>
</html>
