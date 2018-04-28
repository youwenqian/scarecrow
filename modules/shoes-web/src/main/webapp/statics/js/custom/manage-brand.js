var table,laypage,form,layer;
var editGoodsLayer,pageSize ;
$(document).ready(function () {
    layui.use('table', function(){
        table = layui.table;
        layer = layui.layer;
        laypage = layui.laypage;
        form = layui.form;
        var bodyHeight = $("#table").closest("body").height();
        var layuiTableHBHeight = 80;
        pageSize = Math.floor((bodyHeight-layuiTableHBHeight)/39);
        table.render({
            elem: '#table'
            ,url:'/shoes/brand/allDetail'
            ,cols: [[
                {field:'id',  minWidth:'0%',width:'0%',type:'space',style:'display:none;'}
                ,{field:'name',  title: '品牌名称',align:'center'}
                ,{field:'createUser',  title: '创建人', sort: true,align:'center'}
                ,{field:'createTime',  title: '创建时间',templet: '#timeTpl',align:'center'}
                ,{field:'updateUser', title: '更新人', minWidth: 100,align:'center'}
                ,{field:'updateTime',  title: '更新时间', sort: true,templet: '#timeTpl',align:'center'}
                ,{field:'remark',  title: '备注', sort: true,align:'center'}
                ,{fixed: 'right',title:'操作',  align:'center', toolbar: '#operation'}
            ]]
            ,page: {
                limit:pageSize,
                limits:[pageSize]
            }
            ,skin:'line'
            ,even:true
        });
        table.on('tool(brandTable)', function(obj){
            var data = obj.data;
            if(obj.event === 'del'){
                layer.confirm('确定删除'+data.name+'吗？', function(index){
                    $.ajax({
                        type: "POST",
                        url: "/shoes/brand/deleteBrand/"+data.id,
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
                    title: '编辑品牌',
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

function searchBrand() {
    table.render({
        elem: '#table'
        ,url:'/shoes/brand/allDetail?'+$("#search-brand-form").serialize()
        ,cols: [[
            {field:'id',  title: 'ID', sort: true,align:'center',style:'display:none;'}
            ,{field:'name',  title: '品牌名称',align:'center'}
            ,{field:'createUser',  title: '创建人', sort: true,align:'center'}
            ,{field:'createTime',  title: '创建时间',templet: '#timeTpl',align:'center'}
            ,{field:'updateUser', title: '更新人', minWidth: 100,align:'center'}
            ,{field:'updateTime',  title: '更新时间', sort: true,templet: '#timeTpl',align:'center'}
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
function addBrand(){
    editGoodsLayer = layer.open({
        type: 1,
        area:'600px',
        title: '添加品牌',
        content: $('#addForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        success: function(layero){
            layer.setTop(layero); //重点2
            $("#nameEdit").val("");
            $("#remarkEdit").val("");
        }
    });
}
function addBrandBtn() {
    $.post("/shoes/brand/addBrand", $("#AddForm").serialize(),function (data) {
        if(data.success){
            layer.msg("添加成功！", {icon: 1,time:2000});
        }else{
            layer.msg("添加失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(AddGoodsLayer);
    });
}

function editGoodsBtn() {
    $.post("/shoes/brand/updateBrand", $("#editForm").serialize(),function (data) {
        if(data.success){
            layer.msg("修改成功！", {icon: 1,time:2000});
        }else{
            layer.msg("修改失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(editGoodsLayer);
    });
}