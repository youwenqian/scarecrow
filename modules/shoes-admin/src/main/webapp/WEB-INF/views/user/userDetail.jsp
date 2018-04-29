<%--
  Created by IntelliJ IDEA.
  User: wangyucheng
  Date: 2018/4/3
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息</title>
    <link rel="shortcut icon" href="${root}favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${root}statics/css/font.css">
    <link rel="stylesheet" href="${root}statics/css/xadmin.css">
    <script type="text/javascript" src="${root}statics/js/jQuery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${root}statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${root}statics/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="x-body">
        <div class="layui-form-item">
            <label class="layui-form-label">>用户名称</label>
            <div class="layui-input-inline">${user.userName} </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">昵称 </label>
            <div>${user.nickName}</div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机</label>
            <div >${user.phoneNo}</div>
        </div>
        <c:if test="userType eq 1">
            <div class="layui-form-item">
                <label class="layui-form-label">淘宝名称</label>
                <div>${user.taobaoName}</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">进货性质</label>
                <div>${user.stockId}</div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">是否缴费</label>
                <div>
                    <c:if test="${user.isPayment eq 0}">已支付</c:if>
                    <c:if test="${user.isPayment eq 1}">未支付</c:if>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div >
                    <c:if test="${user.sex eq 0}">女</c:if>
                    <c:if test="${user.sex eq 0}">男</c:if>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">地址</label>
                <div>${user.address}</div>
            </div>
        </c:if>
</div>
</body>
</html>
