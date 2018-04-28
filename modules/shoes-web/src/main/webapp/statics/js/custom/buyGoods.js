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
            if(obj.event === 'buy'){
                editGoodsLayer = layer.open({
                    type: 1,
                    area:'700px',
                    title: '编辑订单',
                    content: $('#editGoodsForm'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                    success: function(layero){
                        layer.setTop(layero); //重点2
                        var info = data.goodsName+" "+data.goodsBrand+" "+data.goodsSize+" "+ data.goodsColor+" "+data.goodsType+" "+data.price;
                        $("#idEdit").val(data.id);
                        $("#userIdEdit").val(data.userId);
                        $("#info").html(info);
                        $("#qty").val(1);
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
            // ,{field:'createUser',  title: '创建人', align:'center'}
            // ,{field:'createTime',  title: '创建时间',templet: '#timeTpl',align:'center'}
            // ,{field:'updateUser', title: '更新人', minWidth: 100,align:'center'}
            // ,{field:'updateTime',  title: '更新时间', templet: '#timeTpl',align:'center'}
            // ,{field:'remark',  title: '备注', sort: true,align:'center'}
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
function editGoodsBtn() {
    $.post("/shoes/order/addOrder", $("#editGoodsForm").serialize(),function (data) {
        if(data.success){
            layer.msg("下单成功！", {icon: 1,time:2000});
        }else{
            layer.msg("下单失败，请联系管理员", {icon: 5,time:2000});
        }
        layer.close(editGoodsLayer);
    });
}