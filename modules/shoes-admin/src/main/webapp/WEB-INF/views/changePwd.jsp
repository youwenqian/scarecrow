<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改用户</title>
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
    <form class="layui-form" id="submitForm">
        <input type="hidden" name="id" value="${id}"/>
        <div class="layui-form-item">
            <label for="L_oldpass" class="layui-form-label">
                <span class="x-red">*</span>原密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_oldpass" name="oldpass" required="" lay-verify="oldpass"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_newpass" class="layui-form-label">
                <span class="x-red">*</span>新密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_newpass" name="newpass" required="" lay-verify="newpass"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到16个字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="x-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                       autocomplete="off" class="layui-input">
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
            oldpass: function(value){
                var url = '${root}/valdatePassword';
                var msg = '';
                $.ajaxSetup({
                    async: false
                });
                $.post(url,{'id':'${id}','password':value},function(data){
                    msg = data;
                    console.log(msg)
                });
                console.log(msg)
                if(msg != 'success'){
                    return '原密码错误';
                }
            },
            newpass: [/(.+){6,12}$/, '密码必须6到12位'] ,
            repass: function(value){
                if($('#L_newpass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data){


            $.ajax({
                url:"${root}modifyPwd",
                type: "POST",
                data:$("#submitForm").serializeArray(),
                success: function (data) {
                    if(data == 'success'){
                        layer.alert("修改成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
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
</body>
</html>
