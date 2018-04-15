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
    <title>修改用户</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/statics/css/font.css">
    <link rel="stylesheet" href="/statics/css/xadmin.css">
    <script type="text/javascript" src="/statics/js/jQuery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/statics/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="x-body">
    <form class="layui-form" id="submitForm">
        <input type="hidden" name="id" value="${user.id}"/>
        <div class="layui-form-item">
            <label for="L_userName" class="layui-form-label">
                <span class="x-red">*</span>用户名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_userName" name="userName" required="" lay-verify="userName"
                       autocomplete="off" class="layui-input" value="${user.userName}" disabled>
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>唯一的登入名
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_nickName" class="layui-form-label">
                <span class="x-red">*</span>昵称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_nickName" name="nickName" required="" lay-verify="nickName"
                       autocomplete="off" class="layui-input" value="${user.nickName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_taobaoName" class="layui-form-label">
                <span class="x-red">*</span>淘宝名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_taobaoName" name="taobaoName" required="" lay-verify="taobaoName"
                       autocomplete="off" class="layui-input" value="${user.taobaoName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_isPayment" class="layui-form-label">
                <span class="x-red">*</span>是否缴费
            </label>
            <div class="layui-input-inline">
                <select id="L_isPayment" name="isPayment">
                    <option value="1"  <c:if test="${user.isPayment eq 1}">selected</c:if>>未支付</option>
                    <option value="0"  <c:if test="${user.isPayment eq 0}">selected</c:if>>已支付</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_sex" class="layui-form-label">
                <span class="x-red">*</span>性别
            </label>
            <div class="layui-input-inline">
                <select id="L_sex" name="sex">
                    <option value="0"  <c:if test="${user.sex eq 0}">selected</c:if>>女</option>
                    <option value="1" <c:if test="${user.sex eq 1}">selected</c:if>>男</option>
                </select>
            </div>
        </div>


        <div class="layui-form-item">
            <label for="L_phoneNo" class="layui-form-label">
                <span class="x-red">*</span>手机
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_phoneNo" name="phoneNo" required="" lay-verify="phoneNo"
                       autocomplete="off" class="layui-input" value="${user.phoneNo}">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_address" class="layui-form-label">
                <span class="x-red">*</span>地址
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_address" name="address" required="" lay-verify="address"
                       autocomplete="off" class="layui-input" value="${user.address}">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="L_confirmPassword" class="layui-form-label">
                <span class="x-red">*</span>进货密码
            </label>
            <div class="layui-input-inline">
                <input type="confirmPassword" id="L_confirmPassword" name="confirmPassword" required="" lay-verify="confirmPassword"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>

        <div class="layui-form-item">
            <button  class="layui-btn" lay-filter="add" lay-submit="">
                确定
            </button>
        </div>
    </form>
</div>
<script>
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //自定义验证规则
        form.verify({
            nickName: function(value){
                if(value.length < 2){
                    return '昵称至少2个字符';
                }
                if(value.length > 10){
                    return '昵称不能超过10个字符';
                }
            },
            taobaoName: function(value){
                if(value.length < 2){
                    return '昵称至少2个字符';
                }
                if(value.length > 20){
                    return '昵称不能超过20个字符';
                }
            },
            address: function(value){
                if(value.length < 2){
                    return '昵称至少2个字符';
                }
                if(value.length > 50){
                    return '昵称不能超过50个字符';
                }
            },
            phoneNo:[/^[1][3-9][0-9]{9}$/, "手机号必须为11位数字"],
            confirmPassword: [/(.+){6,12}$/, '密码必须6到12位']
        });

        //监听提交
        form.on('submit(add)', function(data){


            $.ajax({
                url:"${root}user/userEdit",
                type: "POST",
                data:$("#submitForm").serializeArray(),
                success: function (data) {
                    if(data == 'success'){
                        layer.alert("修改成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            location.href='${root}user/userView';
                        });
                    }else{
                        layer.alert("修改失败", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                }

            });
            return false;
            return false;
        });


    });
</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>
</html>
