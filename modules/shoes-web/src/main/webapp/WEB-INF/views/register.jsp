<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-3-6
  Time: 下午11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shoes circle</title>
    <title>&nbsp;shoes circle&nbsp;</title>

    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css" media="all">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
    <script src="<%=basePath%>js/custom/register.js"></script>
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="register">
            <div class="layui-row">
                <div class="layui-col-md12">
                    <form class="layui-form" autocomplete="off" id="userForm">
                        <div class="layui-form-item" pane>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px;">用户名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="userName" lay-verify="required" placeholder="用户名"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px">昵称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="nickName" placeholder="昵称" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item" pane>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px;">登录密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" name="password" lay-verify="required" placeholder="登录系统的密码"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px;">赋权密码</label>
                                <div class="layui-input-inline">
                                    <input type="password" name="confirmPassword" lay-verify="required"
                                           placeholder="赋权密码"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item" pane>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px;">淘宝名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="taobaoName" lay-verify="required" placeholder="淘宝名称"
                                           autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width:300px;">
                                <label class="layui-form-label">性别</label>
                                <div class="layui-input-block" style="width:200px;">
                                    <input type="radio" name="sex" value="1" title="男">
                                    <input type="radio" name="sex" value="2" title="女" checked>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item" pane>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px">出生日期</label>
                                <input name="birthday" id="date1" autocomplete="off" class="layui-input"
                                       style="width:200px;" type="text">
                            </div>
                            <div class="layui-inline" style="width:300px;">
                                <label class="layui-form-label" style="width:100px">进货性质</label>
                                <div class="layui-input-inline">
                                    <select name="stockId" id="stockid" style="width:200px;">
                                        <option value="">请选择</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item" pane>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px;">联系电话</label>
                                <div class="layui-input-inline">
                                    <input type="tel" name="phoneNo" lay-verify="phone" autocomplete="off"
                                           class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width:100px">微信二维码</label>
                                <button type="button" class="layui-btn" id="weixinPicture" style="width:200px;">
                                    <i class="layui-icon">&#xe67c;</i>上传图片
                                </button>
                            </div>
                        </div>
                        <div class="layui-form-item" pane>
                            <div class="layui-inline" >
                                <label class="layui-form-label" style="width:100px">地址</label>
                                <div class="layui-input-block">
                                    <input type="text" name="address" placeholder="地址" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width:300px;">
                                <div class="layui-input-inline">
                                    <input type="hidden" name="imageAddress" placeholder="图片地址" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width:300px;">
                                <div class="layui-input-inline">
                                    <input type="hidden" name="isPayment" placeholder="是否支付" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline" style="width:300px;">
                                <div class="layui-input-inline">
                                    <input type="hidden" name="userType" placeholder="用户类型" class="layui-input" value="1">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <input type="button" value="确定" onClick="registerBase()"  style="background-color:indianred;">
                            <%--<button class="layui-btn" lay-submit lay-filter="registerForm" onclick="registerBase()" style="background-color:indianred;">确定</button>--%>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="erweima" style="display: none"> <img border="0" alt="支付二维码" style="width:100%; height:100%;" src="<%=basePath%>image/webwxgetmsgimg"  ></div>
<script>
</script>
</body>
</html>
