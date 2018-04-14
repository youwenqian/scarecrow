$(function () {

    layui.use('laydate', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });
    $('#searchBtn').click(function () {
        location.href = "../color/colorList";
    });

});



/*用户-停用*/
function member_stop(obj,id){
    layer.confirm('确认要停用吗？',function(index){

        if($(obj).attr('title')=='启用'){

            //发异步把用户状态进行更改
            $(obj).attr('title','停用')
            $(obj).find('i').html('&#xe62f;');

            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
            layer.msg('已停用!',{icon: 5,time:1000});

        }else{
            $(obj).attr('title','启用')
            $(obj).find('i').html('&#xe601;');

            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
            layer.msg('已启用!',{icon: 5,time:1000});
        }

    });
}

/*用户-删除*/
function del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            url:"../color/delColor",
            type: "POST",
            data:{"id":id},
            success: function (data) {
                if(data == 'success'){
                    $(obj).parents("tr").remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                }else{
                    layer.alert("删除失败", {icon: 6},function () {
                        layer.close(index);
                    });
                }
            }

        });
    });
}

function delAll () {

    var data = tableCheck.getData();
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            url:"../color/batchDel",
            type: "POST",
            data:{"ids":data.join(",")},
            success: function (data) {
                if(data == 'success'){
                    $(".layui-form-checked").not('.header').parents('tr').remove();
                    layer.msg('已删除!',{icon:1,time:1000});
                }else{
                    layer.alert("删除失败", {icon: 6},function () {
                        layer.close(index);
                    });
                }
            }
        });
    });
}