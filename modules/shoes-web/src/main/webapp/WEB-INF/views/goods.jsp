<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-4-4
  Time: 上午1:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
        <div class="col-lg-4">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="goodsName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="col-lg-4">
            <label class="layui-form-label">品牌</label>
            <div class="layui-input-block">
                <select name="brandId" id="goodsBrandSearch" lay-filter="brandIdSearchFilter" lay-search="" style="width:200px;">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4">
            <button type="button" id="search-button" onclick="searchGoods()" class="btn btn-default">查找</button>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="col-lg-4">
            <label class="layui-form-label">尺码</label>
            <div class="layui-input-block">
                <select name="goodsSize" id="goodsSizeSearch" lay-filter="goodsSizeSearchFilter" lay-search="" style="width:200px;">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-block">
                <select name="goodsClass" id="goodsTypeSearch" lay-filter="goodsClassSearchFilter" lay-search="" style="width:200px;">
                    <option value="">请选择</option>
                </select>
            </div>
        </div>
        <div class="col-lg-4">
            <button type="button" id="add-button" onclick="addGoods()" class="btn btn-default">添加</button>
        </div>
    </div>
</form>
<table class="layui-hide" id="goodsManageTable" lay-filter="goodTable"></table>
<form id="editGoodsForm" role="form" style="display:none">
    <div class = "form-group">
        <input type="text" name="id" id="idEdit" hidden>
    </div>
    <div class="form-group row" style="margin-left:0px;margin-right:0px;margin-bottom:0px"">
        <label for="goodsName" class="col-sm-2 control-label">名称</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="goodsNameEdit" name="goodsName" placeholder=“请输入名称”>
        </div>
        <label for="goodsBrandEdit" class="col-sm-2 control-label">品牌</label>
        <div class="col-sm-4">
            <select name="brandId" id="goodsBrandEdit" class="form-control" lay-search="">
                <option value="">请选择</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label for="goodsSizeEdit" class="col-sm-2 control-label">尺码</label>
        <div class="col-sm-4">
            <select name="goodsSize" id="goodsSizeEdit" class="form-control" lay-search="">
                <option value="">请选择</option>
            </select>
        </div>
        <label for="goodsColor" class="col-sm-2 control-label">颜色</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="goodsColorEdit" name="goodsColor" placeholder="请输入名字">
        </div>
    </div>
    <div class="form-group">
        <label for="goodsTypeEdit" class="col-sm-2 control-label">分类</label>
        <div class="col-sm-4">
            <select name="goodsClass" id="goodsTypeEdit" class="form-control" lay-search="">
                <option value="">请选择</option>
            </select>
        </div>
        <label for="keyword" class="col-sm-2 control-label">关键字</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="keywordEdit" name="keyword" placeholder="商品关键字">
        </div>
    </div>
    <div class="form-group">
        <label for="price" class="col-sm-2 control-label">价格</label>
        <div class="col-sm-4">
            <input type="number" class="form-control" id="priceEdit" name="price" placeholder="商品的价格（元）">
        </div>
        <label for="remark" class="col-sm-2 control-label">备注</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="remarkEdit" name="remark" placeholder="备注信息">
        </div>
    </div>
    <div class="form-group">
        <button type="button" onclick="editGoodsBtn()" id="editGoodsButton" class="btn btn-default" style="float: right; margin-bottom:  15px; margin-right: 15px;">确定</button>
    </div>
</form>
<form id="addGoodsForm" role="form" style="display:none">
    <div class = "form-group">
        <input type="text" name="id" id="idAdd" hidden>
    </div>
    <div class="form-group row" style="margin-left:0px;margin-right:0px;margin-bottom:0px"">
    <label for="goodsName" class="col-sm-2 control-label">名称</label>
    <div class="col-sm-4">
        <input type="text" class="form-control" id="goodsNameAdd" name="goodsName" placeholder=“请输入名称”>
    </div>
    <label for="goodsBrandAdd" class="col-sm-2 control-label">品牌</label>
    <div class="col-sm-4">
        <select name="brandId" id="goodsBrandAdd" class="form-control" lay-search="">
            <option value="">请选择</option>
        </select>
    </div>
    </div>
    <div class="form-group">
        <label for="goodsSizeAdd" class="col-sm-2 control-label">尺码</label>
        <div class="col-sm-4">
            <select name="goodsSize" id="goodsSizeAdd" class="form-control" lay-search="">
                <option value="">请选择</option>
            </select>
        </div>
        <label for="goodsColor" class="col-sm-2 control-label">颜色</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="goodsColorAdd" name="goodsColor" placeholder="请输入名字">
        </div>
    </div>
    <div class="form-group">
        <label for="goodsClassAdd" class="col-sm-2 control-label">分类</label>
        <div class="col-sm-4">
            <select name="goodsClass" id="goodsClassAdd" class="form-control" lay-search="">
                <option value="">请选择</option>
            </select>
        </div>
        <label for="keyword" class="col-sm-2 control-label">关键字</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="keywordAdd" name="keyword" placeholder="商品关键字">
        </div>
    </div>
    <div class="form-group">
        <label for="price" class="col-sm-2 control-label">价格</label>
        <div class="col-sm-4">
            <input type="number" class="form-control" id="priceAdd" name="price" placeholder="商品的价格（元）">
        </div>
        <label for="remark" class="col-sm-2 control-label">备注</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="remarkAdd" name="remark" placeholder="备注信息">
        </div>
    </div>
    <div class="form-group">
        <button type="button" onclick="addGoodsBtn()" id="addGoodsButton" class="btn btn-default" style="float: right; margin-bottom:  15px; margin-right: 15px;">确定</button>
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
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

    <!-- 这里同样支持 laytpl 语法，如： -->
    {{#  if(d.auth > 2){ }}
    <a class="layui-btn layui-btn-xs" lay-event="check">审核</a>
    {{#  } }}
</script>
<script src="<%=basePath%>js/custom/mechantGoods.js"></script>
</body>
</html>
