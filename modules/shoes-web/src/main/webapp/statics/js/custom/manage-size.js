var table,laypage,form,layer;
var pageSize,editGoodsLayer;
$(document).ready(function () {
    layui.use('table', function(){
        table = layui.table;
        laypage = layui.laypage;
        form = layui.form;
        layer = layui.layer;
        var bodyHeight = $("#table").closest("body").height();
        var layuiTableHBHeight = 80;
        pageSize = Math.floor((bodyHeight-layuiTableHBHeight)/39);
        table.render({
            elem: '#table'
            ,url:'/shoes/size/allDetail'
            ,cols: [[
                {field:'id',  title: 'ID', sort: true,align:'center',style:'display:none;'}
                ,{field:'sizeName',  title: '尺码名称',align:'center'}
                ,{field:'desc',  title: '描述', sort: true,align:'center'}
                ,{fixed: 'right',title:'操作', width: 165, align:'center', toolbar: '#operation'}
            ]]
            ,page: {
                limit:pageSize,
                limits:[pageSize]
            }
            ,skin:'line'
            ,even:true
        });
        table.on('tool(sizeTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定删除'+data.sizeName+'吗？', function(index){
                    $.ajax({
                        type: "POST",
                        url: "/shoes/size/deleteSize/"+data.id,
                        dataType:"json",
                        contentType: "application/json",
                        success: function (data) {
                            obj.del();
                            form.render('select');
                        }
                    });
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                editGoodsLayer = layer.open({
                    type: 1,
                    area:'600px',
                    title: '编辑尺码',
                    content: $('#editForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                    success: function(layero){
                        layer.setTop(layero); //重点2
                        $("#idEdit").val(data.id);
                        $("#nameEdit").val(data.name);
                        $("#remarkEdit").val(data.remark);
                    }
                });
            }
        });
    });
});

function search() {
    table.render({
        elem: '#table'
        ,url:'/shoes/size/allDetail?'+$("#searchForm").serialize()
        ,cols: [[
            {field:'id',  title: 'ID', sort: true,align:'center',style:'display:none;'}
            ,{field:'sizeName',  title: '尺码名称',align:'center'}
            ,{field:'remark',  title: '备注', sort: true,align:'center'}
            ,{fixed: 'right',title:'操作', width: 165, align:'center', toolbar: '#operation'}
        ]]
        ,page: {
            limit:pageSize,
            limits:[pageSize]
        }
        ,skin:'line'
        ,even:true
    });
}
function add() {
    editGoodsLayer = layer.open({
        type: 1,
        area:'600px',
        title: '添加尺码',
        content: $('#addForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        success: function(layero){
            layer.setTop(layero); //重点2
            $("#idAdd").val(data.id);
            $("#nameAdd").val(data.sizeName);
            $("#remarkAdd").val(data.remark);
        }
    });
}
function addBtn() {
    $.post("/shoes/size/addSize", $("#AddForm").serialize(),function (data) {
        if(data.success){
            layer.msg("添加成功！", {icon: 1,time:2000});
        }else{
            layer.msg("添加失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(AddGoodsLayer);
    });
}

function editBtn() {
    $.post("/shoes/size/updateSize", $("#editForm").serialize(),function (data) {
        if(data.success){
            layer.msg("修改成功！", {icon: 1,time:2000});
        }else{
            layer.msg("修改失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(editGoodsLayer);
    });
}