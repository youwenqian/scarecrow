/**
 * Created by li on 2018/3/28.
 */
var laydate, form, upload, element, layer;
$(document).ready(function () {
    layui.use(['laydate', 'form', 'upload', 'element', 'layer'], function () {
        laydate = layui.laydate;
        form = layui.form;
        upload = layui.upload; //上传
        element = layui.element; //元素操作
        layer = layui.layer;

        $.ajax({
            type: "POST",
            url: "init",
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                for (var i = 0; i < data.stockList.length; i++) {
                    $("#stockid").append('<option value="' + data.stockList[i].id + '">' + data.stockList[i].name + '</option>');
                }
                form.render('select');
            }
        });

        layer.open({
            type: 1,
            title: '修改密码',
            content: '<div style="width:600px;height: 500px;">' +
            '<div class="layui-row">' +
            '<form class="layui-form" id="modifyPasswordForm">' +
            '<div class="layui-form-item">' +
            '<label class="layui-form-label">原密码：</label> ' +
            '<div class="layui-input-block">' +
            '<input type="text" name="originalPasswrod" required lay-verify="required" placeholder="原密码" autocomplete="off" class="layui-input">' +
            '</div> ' +
            '</div>' +
            '<div class="layui-form-item">' +
            '<label class="layui-form-label">新密码：</label> ' +
            '<div class="layui-input-block">' +
            '<input type="text" name="newPasswrod" required lay-verify="required" placeholder="新密码" autocomplete="off" class="layui-input">' +
            '</div> ' +
            '</div>' +
            '<div class="layui-form-item">' +
            '<label class="layui-form-label">确认密码：</label> ' +
            '<div class="layui-input-block">' +
            '<input type="text" name="confirmPasswrod" required lay-verify="required" placeholder="确认密码" autocomplete="off" class="layui-input">' +
            '</div> ' +
            '</div>' +
            '<div class="layui-form-item">' +
            '<div class="layui-input-block">' +
            '<input type="button" onclick="modifyPassword()" value="确定"></input>' +
            '</div> ' +
            '</div>' +
            '</form> ' +
            '</div>' +
            '</div>' //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        });
    });

});

function modifyPassword() {

}
