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
            elem: '#table'
            ,url:'/shoes/merchant/allDetail'
            ,cols: [[
                {field:'id', minWidth:'0%',width:'0%',type:'space',style:'display:none;'}
                ,{field:'userName', title:'用户名' align:'center'}
                ,{field:'nickName',  title: '昵称',align:'center'}
                ,{field:'birthday',  title: '生日',align:'center'templet: '#timeTpl'}
                ,{field:'isPayment',  title: '是否支付',align:'center'}
                ,{field:'sex',  title: '性别',align:'center'}
                ,{field:'userType',  title: '所属类型',align:'center'}
                ,{field:'phoneNo',  title: '手机号',align:'center'}
                ,{field:'address',  title: '地址',align:'center'}
                ,{field:'stockId',  title: '创建人', align:'center'}
                ,{field:'taobaoName',  title: '淘宝账户',align:'center'}
                ,{field:'createUser', title: '创建人', minWidth: 100,align:'center'}
                ,{field:'createTime',  title: '创建时间', templet: '#timeTpl',align:'center'}
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
            url: "/shoes/merchant/shortBrand",
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
            url: "/shoes/merchant/allDetail",
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
            url: "/shoes/merchant/shortType",
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
         table.on('tool(merchantTable)', function(obj){
             var data = obj.data;
             if(obj.event === 'del'){
                 layer.confirm('确定删除'+data.userName+'吗？', function(index){
                     $.ajax({
                         type: "POST",
                         url: "/shoes/merchant/deleteMerchant/"+data.id,
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
                     content: $('#editForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
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
    });
});

function search() {
    table.render({
        elem: '#goodsManageTable'
        ,url:'/shoes/merchant/getMerchant?'+$("#searchForm").serialize()
        ,cols: [[
            {field:'id', minWidth:'0%',width:'0%',type:'space',style:'display:none;'}
            ,{field:'userName', title:'用户名' align:'center'}
            ,{field:'nickName',  title: '昵称',align:'center'}
            ,{field:'birthday',  title: '生日',align:'center'templet: '#timeTpl'}
            ,{field:'isPayment',  title: '是否支付',align:'center'}
            ,{field:'sex',  title: '性别',align:'center'}
            ,{field:'userType',  title: '所属类型',align:'center'}
            ,{field:'phoneNo',  title: '手机号',align:'center'}
            ,{field:'address',  title: '地址',align:'center'}
            ,{field:'stockId',  title: '创建人', align:'center'}
            ,{field:'taobaoName',  title: '淘宝账户',align:'center'}
            ,{field:'createUser', title: '创建人', minWidth: 100,align:'center'}
            ,{field:'createTime',  title: '创建时间', templet: '#timeTpl',align:'center'}
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

function editBtn() {
    $.post("/shoes/merchant/updateMerchant", $("#editForm").serialize(),function (data) {
        if(data.success){
            layer.msg("修改成功！", {icon: 1,time:2000});
        }else{
            layer.msg("修改失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(editGoodsLayer);
    });
}