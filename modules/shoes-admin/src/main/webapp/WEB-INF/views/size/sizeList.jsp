
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎页面-X-admin2.0</title>
    <link rel="shortcut icon" href="${root}favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${root}statics/css/font.css">
    <link rel="stylesheet" href="${root}statics/css/xadmin.css">
    <link rel="stylesheet" href="${root}statics/css/simplePagination/simplePagination.css">

    <script type="text/javascript" src="${root}statics/js/jQuery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="${root}statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${root}statics/js/xadmin.js"></script>
    <script type="text/javascript" src="${root}statics/admin/sizeList.js"></script>
    <script type="text/javascript" src="${root}statics/js/simplePagination/jquery.simplePagination.js"></script>


    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->

</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">颜色管理</a>
        <a><cite>颜色列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="queryForm">
            <input type="text" name="name"  placeholder="请输入名称" autocomplete="off" class="layui-input" value="${name}">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach" id="searchBtn"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加颜色','${root}size/toSizeAdd', 600, 400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${count} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>id</th>
            <th>名称</th>
             <th >操作</th>
        </tr>
        </thead>
        <tbody id="dataList">
        <c:forEach items="${sizes}" var="size" varStatus="num">
            <tr class="result">
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${size.id}'><i
                            class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${size.id}</td>
                <td>${size.name}</td>
                <td class="td-manage">
                    <a title="编辑"  onclick="x_admin_show('编辑','../size/toSizeEdit?id=${size.id}', 600, 400)" href="javascript:;">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                    <a title="删除" onclick="del(this,'${size.id}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination" id="pagination" >
    </div>
</div>

</body>
<!--分页-->
<link rel="stylesheet" href="${root}statics/css/simplePagination/simplePagination.css">
<script type="text/javascript" src="${root}statics/js/simplePagination/jquery.simplePagination.js"></script>
<script>
    var page_index;
    var itemsOnPage = 10;
    $(function() {
        $("#pagination").pagination({
            items: ${count},
            itemsOnPage: itemsOnPage,
            cssStyle: 'compact-theme',  //light-theme，dark-theme和compact-theme。
            onInit: changePage,
            onPageClick: changePage
        });
    });

    function changePage(){
        page_index = $("#pagination").pagination('getCurrentPage') -1;
        $("#dataList .result").hide();
        for(var i = page_index * itemsOnPage; i < page_index * itemsOnPage + itemsOnPage; i++){
            $("#dataList .result:eq(" + i + ")").show();
        }
    }
    /*用户-删除*/
    function del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            $.ajax({
                url:"${root}size/delSize",
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
                url:"${root}size/batchDel",
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
</script>
    </html>