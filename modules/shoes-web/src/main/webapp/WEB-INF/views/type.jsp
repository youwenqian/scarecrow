<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-4-25
  Time: 上午1:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>shoes circle</title>
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
</head>
<body>
<form class="layui-form layui-form-pane" id="searchForm" style="padding: 5px;">
    <div class="layui-form-item">
        <div class="col-lg-4">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="col-lg-4">
            <button type="button" id="search-button" onclick="search()" class="btn btn-default">查找</button>
        </div>
        <div class="col-lg-4">
            <button type="button" id="add-button" onclick="add()" class="btn btn-default">添加</button>
        </div>
    </div>
</form>
<table class="layui-hide" id="table" lay-filter="typeTable"></table>

<form id="editForm" role="form" style="display:none">
    <div class = "form-group">
        <input type="text" name="id" id="idEdit" hidden>
    </div>
    <div class="form-group row" style="margin-left:0px;margin-right:0px;margin-bottom:0px"">
    <label for="nameEdit" class="col-sm-6 control-label">名称</label>
    <div class="col-sm-6">
        <input type="text" class="form-control" id="nameEdit" name="TypeName" placeholder=“请输入名称”>
    </div>
    </div>
    <div class="form-group">
        <button type="button" onclick="editBtn()" id="editBrandButton" class="btn btn-default" style="float: right; margin-bottom:  15px; margin-right: 15px;">确定</button>
    </div>
</form>
<form id="addForm" role="form" style="display:none">
    <div class = "form-group">
        <input type="text" name="id" id="idAdd" hidden>
    </div>
    <div class="form-group row" style="margin-left:0px;margin-right:0px;margin-bottom:0px"">
    <label for="nameAdd" class="col-sm-6 control-label">名称</label>
    <div class="col-sm-6">
        <input type="text" class="form-control" id="nameAdd" name="typeName" placeholder=“请输入名称”>
    </div>
    </div>
    <div class="form-group">
        <button type="button" onclick="addBtn()" id="addBrandButton" class="btn btn-default" style="float: right; margin-bottom:  15px; margin-right: 15px;">确定</button>
    </div>
</form>
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
<script src="<%=basePath%>js/custom/manage-type.js"></script>
</body>
</html>
