var table,laypage,form,layer;
var editGoodsLayer,pageSize;
$(document).ready(function () {
    layui.use('table', function(){
        table = layui.table;
        laypage = layui.laypage;
        form = layui.form;
        layer = layui.layer;
        var bodyHeight = $("#goodsManageTable").closest("body").height();
        var formHeight = $("#search-goods-form").height();
        var layuiTableHBHeight = 80;
        pageSize = Math.floor((bodyHeight-formHeight-layuiTableHBHeight-5)/39);
        table.render({
            elem: '#goodsManageTable'
            ,url:'/shoes/goods/allDetail'
            ,cols: [[
                {field:'id', minWidth:'0%',width:'0%',type:'space',style:'display:none;'}
                ,{field:'userId', type:'space',style:'display:none;',minWidth:'0%',width:'0%'}
                ,{field:'goodsName',  title: '名称',align:'center'}
                ,{field:'goodsBrand',  title: '品牌',align:'center'}
                ,{field:'goodsSize',  title: '尺码',align:'center'}
                ,{field:'goodsColor',  title: '颜色',align:'center'}
                ,{field:'goodsType',  title: '分类',align:'center'}
                ,{field:'keyword',  title: '关键字',align:'center'}
                ,{field:'price',  title: '价格',align:'center'}
                ,{field:'createUser',  title: '创建人', align:'center'}
                ,{field:'createTime',  title: '创建时间',templet: '#timeTpl',align:'center'}
                ,{field:'updateUser', title: '更新人', minWidth: 100,align:'center'}
                ,{field:'updateTime',  title: '更新时间', templet: '#timeTpl',align:'center'}
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
        $.ajax({
            type: "POST",
            url: "/shoes/brand/shortBrand",
            dataType:"json",
            contentType: "application/json",
            success: function (data) {
                for (var i = 0; i < data.data.length; i++) {
                    $("#goodsBrandSearch").append('<option value="' + data.data[i].id + '">' + data.data[i].name + '</option>');
                }
                form.render('select');
            }
        });
        $.ajax({
            type: "POST",
            url: "/shoes/size/allDetail",
            dataType:"json",
            contentType: "application/json",
            success: function (data) {
                for (var i = 0; i < data.data.length; i++) {
                    $("#goodsSizeSearch").append('<option value="' + data.data[i].id + '">' + data.data[i].sizeName + '</option>');
                }
                form.render('select');
            }
        });
        $.ajax({
            type: "POST",
            url: "/shoes/type/shortType",
            dataType:"json",
            contentType: "application/json",
            success: function (data) {
                for (var i = 0; i < data.data.length; i++) {
                    $("#goodsTypeSearch").append('<option value="' + data.data[i].id + '">' + data.data[i].typeName + '</option>');
                }
                form.render('select');
            }
        });
        //监听表格复选框选择
        /* table.on('checkbox(demo)', function(obj){
             console.log(obj)
         });*/
         //监听工具条
         table.on('tool(goodTable)', function(obj){
             var data = obj.data;
             if(obj.event === 'del'){
                 layer.confirm('确定删除'+data.goodsName+'吗？', function(index){
                     $.ajax({
                         type: "POST",
                         url: "/shoes/goods/deleteType/"+data.id,
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
                     title: '编辑商品',
                     content: $('#editGoodsForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                     success: function(layero){
                         layer.setTop(layero); //重点2
                         $("#idEdit").val(data.id);
                         $("#goodsNameEdit").val(data.goodsName);
                         if($("#goodsBrandEdit>option").length<=1){
                             $("#goodsBrandSearch>option:first ~ option").each(function(){
                                 $("#goodsBrandEdit").append(this);
                                 if(data.goodsBrand==$(this).html()){
                                     $("#goodsBrandEdit>option:last").attr("selected","selected");
                                     form.render('select');
                                 }
                             });
                         }else{
                             $("#goodsBrandEdit>option").each(function () {
                                 if(data.goodsBrand==$(this).html()){
                                     $(this).attr("selected","selected");
                                 }
                             });
                         }
                         if($("#goodsSizeEdit>option").length<=1){
                             $("#goodsSizeSearch>option:first ~ option").each(function(){
                                 $("#goodsSizeEdit").append(this);
                                 if(data.goodsSize==$(this).html()){
                                     $("#goodsSizeEdit>option:last").attr("selected","selected");
                                     form.render('select');
                                 }
                             });
                         }else{
                             $("#goodsSizeEdit>option").each(function () {
                                 if(data.goodsSize==$(this).html()){
                                     $(this).attr("selected","selected");
                                 }
                             });
                         }
                         $("#goodsColorEdit").val(data.goodsColor);
                         if($("#goodsTypeEdit>option").length<=1){
                             $("#goodsTypeSearch>option:first ~ option").each(function(){
                                 $("#goodsTypeEdit").append(this);
                                 if(data.goodsType==$(this).html()){
                                     $("#goodsTypeEdit>option:last").attr("selected","selected");
                                     form.render('select');
                                 }
                             });
                         }else{
                             $("#goodsTypeEdit>option").each(function () {
                                 if(data.goodsType==$(this).html()){
                                     $(this).attr("selected","selected");
                                 }
                             });
                         }
                         $("#keywordEdit").val(data.keyword);
                         $("#priceEdit").val(data.price);
                         $("#remarkEdit").val(data.remark);
                     }
                 });
             }
         });
        form.on('select(brandIdSearchFilter)', function (data) {
            brandId = data.value;
            // categoryName = data.elem[data.elem.selectedIndex].text;
            form.render('select');
        });
        form.on('select(goodsClassSearchFilter)', function (data) {
            goodsClass = data.value;
            // categoryName = data.elem[data.elem.selectedIndex].text;
            form.render('select');
        });
        form.on('select(goodsSizeSearchFilter)', function (data) {
            goodsSize = data.value;
            // categoryName = data.elem[data.elem.selectedIndex].text;
            form.render('select');
        });
        /*
                 var $ = layui.$, active = {
                     getCheckData: function(){ //获取选中数据
                         var checkStatus = table.checkStatus('idTest')
                             ,data = checkStatus.data;
                         layer.alert(JSON.stringify(data));
                     }
                     ,getCheckLength: function(){ //获取选中数目
                         var checkStatus = table.checkStatus('idTest')
                             ,data = checkStatus.data;
                         layer.msg('选中了：'+ data.length + ' 个');
                     }
                     ,isAll: function(){ //验证是否全选
                         var checkStatus = table.checkStatus('idTest');
                         layer.msg(checkStatus.isAll ? '全选': '未全选')
                     }
                 };

                 $('.demoTable .layui-btn').on('click', function(){
                     var type = $(this).data('type');
                     active[type] ? active[type].call(this) : '';
                 });*/
    });
});

function addGoods() {
    editGoodsLayer = layer.open({
        type: 1,
        area:'600px',
        title: '添加商品',
        content: $('#addGoodsForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
        success: function(layero){
            layer.setTop(layero); //重点2
            $("#idAdd").val("");
            $("#goodsNameAdd").val("");
            if($("#goodsBrandAdd>option").length<=1){
                $("#goodsBrandSearch>option:first ~ option").each(function(){
                    $("#goodsBrandAdd").append(this);
                });
            }else{
                $("#goodsBrandSearch>option:first").attr("selected","selected");
            }
            if($("#goodsSizeAdd>option").length<=1){
                $("#goodsSizeSearch>option:first ~ option").each(function(){
                    $("#goodsSizeAdd").append(this);
                });
            }else{
                $("#goodsSizeSearch>option:first").attr("selected","selected");
            }
            $("#goodsColorAdd").val("");
            if($("#goodsClassAdd>option").length<=1){
                $("#goodsTypeSearch>option:first ~ option").each(function(){
                    $("#goodsClassAdd").append(this);
                });
            }else{
                $("#goodsClassSearch>option:first").attr("selected","selected");
            }
            $("#price").val(0.0);
            $("#keyword").val("");
            $("#remark").val("");
        }
    });
}

function searchGoods() {
    table.render({
        elem: '#goodsManageTable'
        ,url:'/shoes/goods/getGoods?'+$("#search-goods-form").serialize()
        ,cols: [[
            {field:'id', minWidth:'0%',width:'0%',type:'space',style:'display:none;'}
            ,{field:'userId', type:'space',style:'display:none;',minWidth:'0%',width:'0%'}
            ,{field:'goodsName',  title: '名称',align:'center'}
            ,{field:'goodsBrand',  title: '品牌',align:'center'}
            ,{field:'goodsSize',  title: '尺码',align:'center'}
            ,{field:'goodsColor',  title: '颜色',align:'center'}
            ,{field:'goodsType',  title: '分类',align:'center'}
            ,{field:'keyword',  title: '关键字',align:'center'}
            ,{field:'price',  title: '价格',align:'center'}
            ,{field:'createUser',  title: '创建人', align:'center'}
            ,{field:'createTime',  title: '创建时间',templet: '#timeTpl',align:'center'}
            ,{field:'updateUser', title: '更新人', minWidth: 100,align:'center'}
            ,{field:'updateTime',  title: '更新时间', templet: '#timeTpl',align:'center'}
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

function addGoodsBtn() {
    $.post("/shoes/goods/addGoods", $("#AddGoodsForm").serialize(),function (data) {
        if(data.success){
            layer.msg("添加成功！", {icon: 1,time:2000});
        }else{
            layer.msg("添加失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(AddGoodsLayer);
    });
}

function editGoodsBtn() {
    $.post("/shoes/goods/updateGoods", $("#editGoodsForm").serialize(),function (data) {
        if(data.success){
            layer.msg("修改成功！", {icon: 1,time:2000});
        }else{
            layer.msg("修改失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(editGoodsLayer);
    });
}