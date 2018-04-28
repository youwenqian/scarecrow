<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>&nbsp;shoes circle&nbsp;</title>
  <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css" />
  <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
  <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
  <!--[if IE]>
  <script src="js/html5shiv/html5shiv.min.js"></script>
  <![endif]-->
  <!-- layui -->
  <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css"  media="all">
</head>
<body>
<div class="htmleaf-container">
  <div class="wrapper">
    <div class="container">
      <div class="_container_">
        <h1>&nbsp;&nbsp;&nbsp;欢迎登陆<span><i>.</i><i>.</i><i>.</i></span></h1>
        <!-- 					<h1>Welcome<span><i>.</i><i>.</i><i>.</i></span></h1> -->

        <form class="form" autocomplete="off">
          <input type="text" name="userName" placeholder="登陆账户">
          <input type="password" name="passWord" placeholder="登陆密码">
          <button type="submit" id="login-button">登陆</button>
        </form>
        <div style="width: 80px; margin-top: 10px;margin-right: 10px;"><a href="<%=basePath%>index/register" target="_self">注册</a></div>

      </div>
    </div>
  </div>
  <img src="../statics/image/123.jpg" style="width:50%; height:50%" >
</div>

<script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
<script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
<script src="<%=basePath%>js/custom/index.js"></script>
</body>
</html>