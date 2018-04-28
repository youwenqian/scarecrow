<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-4-4
  Time: 上午1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String userId = String.valueOf(request.getSession().getAttribute("userId"));
%>
<html>
<head>
    <title>商品管理</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/default.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/login/css/styles.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>js/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/common.css">
    <!--[if IE]>
    <script src="<%=basePath%>js/html5shiv/html5shiv.min.js"></script>
    <![endif]-->
    <!-- layui -->
    <link rel="stylesheet" href="<%=basePath%>js/layui/css/layui.css"  media="all">
    <script src="<%=basePath%>js/jquery-1.9.1.min.js"></script>
    <script src="<%=basePath%>js/layui/layui.js" charset="utf-8"></script>
    <script src="<%=basePath%>js/bootstrap/js/bootstrap.js"></script>
</head>
<body class="layui-layout-body">
<form class="layui-form layui-form-pane" id="search-goods-form" style="padding: 5px;">
    <div class="layui-form-item">
        <div class="col-lg-3">
            <label class="layui-form-label">品牌</label>
            <div class="layui-input-block">
                <select name="brandId" id="goodsBrandSearch" lay-filter="brandIdSearchFilter" lay-search="" style="width:200px;">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="col-lg-3">
            <label class="layui-form-label">尺码</label>
            <div class="layui-input-block">
                <select name="goodsSize" id="goodsSizeSearch" lay-filter="goodsSizeSearchFilter" lay-search="" style="width:200px;">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="col-lg-3">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-block">
                <select name="goodsClass" id="goodsTypeSearch" lay-filter="goodsClassSearchFilter" lay-search="" style="width:200px;">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="col-lg-3">
            <button type="button" id="search-button" onclick="searchGoods()" class="btn btn-default">查找</button>
        </div>
    </div>
</form>
<table class="layui-hide" id="goodsManageTable" lay-filter="goodTable"></table>
<form id="editGoodsForm" role="form" style="display:none">
    <div class = "form-group">
        <input type="text" name="goodsId" id="idEdit" hidden>
        <input type="text" name="sellerId" id="userIdEdit" hidden>
        <input type="text" name="buyerId" id="buyerIdEdit" value="<%=userId%>" hidden>
    </div>
    <div class = "form-group">
        <label id="info" class="col-sm-8 control-label"></label>
        <label for="qty" class="col-sm-2 control-label">数量</label>
        <div class="col-sm-2">
            <input type="number" class="form-control" id="qty" name="qty" min="0" placeholder="">
        </div>
    </div>
    <div class="form-group">
        <label for="remarkEdit" class="col-sm-2 control-label">留言</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="remarkEdit" name="remark" placeholder="备注信息">
        </div>
    </div>
    <div class="form-group">
        <button type="button" onclick="editGoodsBtn()" id="editGoodsButton" class="btn btn-default" style="float: right; margin-bottom:  15px; margin-right: 15px;">确定</button>
    </div>
</form>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
<script src="<%=basePath%>js/custom/dateFormat.js"></script>
<script id="timeTpl" type="text/html">
    {{#
    var date = new Date();
    date.setTime(d.createTime);
    return date.Format("yyyy-MM-dd hh:mm:ss");
    }}
</script>
<script type="text/html" id="operation">
    <a class="layui-btn layui-btn-xs" lay-event="buy">求购</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>
<script src="<%=basePath%>js/custom/buyGoods.js"></script>
</body>
</html>
