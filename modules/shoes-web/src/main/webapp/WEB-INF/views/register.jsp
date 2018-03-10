<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-3-6
  Time: 下午11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shoes circle</title>
    <title>&nbsp;shoes circle&nbsp;</title>
    <link rel="stylesheet" type="text/css" href="/statics/js/login/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/statics/js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="/statics/js/login/css/styles.css">
    <!--[if IE]>
    <script src="/statics/js/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    <!-- layui -->
    <link rel="stylesheet" href="/statics/js/layui/css/layui.css" media="all">
</head>
<body>
<div class="htmleaf-container">
    <div class="wrapper">
        <div class="register">
            <div class="">
                <form class="layui-form" autocomplete="off">
                    <div class="layui-form-item" pane>
                        <div class="layui-inline">
                            <label class="layui-form-label" style="width:100px;">用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="nickName" lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"  style="width:100px;">登录密码</label>
                            <div class="layui-input-inline">
                                <input type="text" name="passWord" lay-verify="required" placeholder="登录系统的密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label"  style="width:100px;">赋权密码</label>
                            <div class="layui-input-inline">
                                <input type="text" name="confirmPassword" lay-verify="required" placeholder="赋权密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                    <div class="layui-form-item" pane>
                        <div class="layui-inline">
                            <div class="layui-inline">
                                <label class="layui-form-label"  style="width:100px;">姓名</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="userName" lay-verify="required" placeholder="姓名" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-block">
                                    <input type="radio" name="sex" value="M" />男
                                    <input type="radio" name="sex" value="F" checked/>女
                                    <input type="radio" name="sex" value="female">Female
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-inline">
                                    <label class="layui-form-label"  style="width:100px;">联系电话</label>
                                    <div class="layui-input-inline">
                                        <input type="tel" name="phoneNo" lay-verify="phone" autocomplete="off" class="layui-input">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>

            </div>
        </div>

        <ul class="bg-bubbles">
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
        </ul>
    </div>
</div>

<script src="/statics/js/jquery-1.9.1.min.js"></script>
<script src="/statics/js/layui/layui.js" charset="utf-8"></script>
<script src="/statics/js/custom/register.js"></script>
</body>
</html>
