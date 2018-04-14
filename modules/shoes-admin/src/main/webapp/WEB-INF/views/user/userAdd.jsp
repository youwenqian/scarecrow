<%--
  Created by IntelliJ IDEA.
  User: wangyucheng
  Date: 2018/4/3
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <form class="layui-form" id="submitForm" method="post" action="../user/userAdd">
        <div class="layui-form-item">
            <label for="L_userName" class="layui-form-label">
                <span class="x-red">*</span>用户名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_userName" name="userName" required="" lay-verify="userName"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>将会成为唯一的登入名
            </div>
        </div>
        <%--<div class="layui-form-item">--%>
            <%--<label for="L_username" class="layui-form-label">--%>
                <%--<span class="x-red">*</span>昵称--%>
            <%--</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" id="L_username" name="username" required="" lay-verify="nikename"--%>
                       <%--autocomplete="off" class="layui-input">--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="password" required="" lay-verify="pass"
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
            <label for="L_repass" class="layui-form-label">
            </label>
            <button  class="layui-btn" lay-filter="add" lay-submit="submit">
                增加
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
            userName: function(value){
                if(value.length < 5){
                    return '昵称至少得5个字符';
                }
            }
            ,pass: [/(.+){6,12}$/, '密码必须6到12位']
            ,repass: function(value){
                if($('#L_pass').val()!=$('#L_repass').val()){
                    return '两次密码不一致';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data){

            $.ajax({
                url:"${root}user/userAdd",
                type: "POST",
                data:$("#submitForm").serializeArray(),
                success: function (data) {
                    if(data == 'success'){
                        layer.alert("增加成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            location.href='${root}user/userView';
                        });
                    }else{
                        layer.alert("增加失败", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        });
                    }
                }

            });
            return false;
        });


    });
</script>
</body>
</html>
