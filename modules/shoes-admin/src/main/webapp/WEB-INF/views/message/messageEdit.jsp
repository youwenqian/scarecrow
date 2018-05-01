<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改color</title>
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
        <input type="hidden" name="id" value="${color.id}"/>
        <div class="layui-form-item">
            <label for="L_name" class="layui-form-label">
                <span class="x-red">*</span>名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_name" name="title" required="" lay-verify="title"
                       autocomplete="off" class="layui-input" value="${message.title}">
            </div>
            <div class="layui-form-mid layui-word-aux">
                <span class="x-red">*</span>
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_remark" class="layui-form-label">
                备注
            </label>
            <div class="layui-input-inline">
                <textarea id="L_remark" name="remark" lay-verify="remark"
                          autocomplete="off" class="layui-input"
                          style=" width: 194px; height: 133px;">${color.remark}</textarea>
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
            name: function(value){
                if(value.length < 2){
                    return '名称至少得2个字符';
                }
                if(value.length > 10){
                    return '名称不能10个字符';
                }
            },
            remark:function(value){
                if(value.length > 100){
                    return '名称不能100个字符';
                }
            }
        });

        //监听提交
        form.on('submit(add)', function(data){
            $.ajax({
                url:"${root}message/messageEdit",
                type: "POST",
                data:$("#submitForm").serializeArray(),
                success: function (data) {
                    if(data == 'success'){
                        layer.alert("修改成功", {icon: 6},function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                            location.href='${root}message/messageList';
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
