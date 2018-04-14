<%--
  Created by IntelliJ IDEA.
  User: wangyucheng
  Date: 2018/3/20
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎页面-X-admin2.0</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/statics/css/font.css">
    <link rel="stylesheet" href="/statics/css/xadmin.css">
    <link rel="stylesheet" href="/statics/css/simplePagination/simplePagination.css">

    <script type="text/javascript" src="/statics/js/jQuery/jQuery-2.1.4.min.js"></script>
    <script type="text/javascript" src="/statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/statics/js/xadmin.js"></script>
    <script type="text/javascript" src="/statics/admin/userList.js"></script>
    <script type="text/javascript" src="/statics/js/simplePagination/jquery.simplePagination.js"></script>


    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->

    <script>
        $(function () {
            var isPayment = $('#isPayment');
            console.log(isPayment);
            if(isPayment == 0){
                $('#isPayment').find('option:eq(1)').attr('selected','selected');
            }else if(isPayment == 1){
                $('#isPayment').find('option:eq(2)').attr('selected','selected');
            }
        })
    </script>
</head>

<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">用户管理</a>
        <a><cite>用户列表</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so" id="queryForm">
            <input class="layui-input" placeholder="开始日期" name="startTime" id="startTime" lay-key="1" value="${startTime}">
            <input class="layui-input" placeholder="结束日期" name="endTime" id="endTime" lay-key="2" value="${endTime}">
            <div class="layui-input-inline">
                <select name="isPayment" id="isPayment">
                    <option value="" >支付状态</option>
                    <option value="0" >已支付</option>
                    <option value="1">未支付</option>
                </select>
            </div>
            <input type="text" name="phoneNo"  placeholder="请输入手机号" autocomplete="off" class="layui-input" value="${phoneNo}">
            <input type="text" name="userName"  placeholder="请输入用户名称" autocomplete="off" class="layui-input" value="${userName}">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach" id="searchBtn"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAllUser()"><i class="layui-icon"></i>批量删除</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','../user/toUserAdd', 600, 400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：${count} 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>id</th>
            <th>用户名称</th>
            <th>是否缴费</th>
            <th>性别</th>
            <th>用户类型</th>
            <th>手机</th>
            <th>淘宝名称</th>
            <th>注册日期</th>
            <th>是否有效</th>
            <th >操作</th>
        </tr>
        </thead>
        <tbody id="dataList">
        <c:forEach items="${users}" var="user" varStatus="num">
            <tr>
                <td>
                    <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${user.id}'><i
                            class="layui-icon">&#xe605;</i></div>
                </td>
                <td>${user.id}</td>
                <td>${user.userName}</td>
                <td>
                    <c:choose>
                        <c:when test="${user.isPayment == 0}">已缴费</c:when>
                        <c:otherwise>未缴费</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.sex == 1}">男</c:when>
                        <c:otherwise>女</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${user.userType == 0}">管理员</c:when>
                        <c:otherwise>商家</c:otherwise>
                    </c:choose>
                </td>
                <td>${user.phoneNo}</td>
                <td>${user.taobaoName}</td>
                <td>
                    <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                </td>
                <td class="td-status">
                <span class="layui-btn layui-btn-normal layui-btn-mini">
                    <c:choose>
                        <c:when test="${user.status == 1}">已启用</c:when>
                        <c:otherwise>未启用</c:otherwise>
                    </c:choose>
                </span>
                </td>
                <td class="td-manage">
                    <a title="编辑"  onclick="x_admin_show('编辑','../user/toUserEdit?id=${user.id}', 600, 400)" href="javascript:;">
                        <i class="layui-icon">&#xe642;</i>
                    </a>
                    <a title="查看" onclick="x_admin_show('查看','../user/toUserEdit')" href="javascript:;">
                        <i class="layui-icon">&#xe63c;</i>
                    </a>
                    <a title="删除" onclick="delUser(this,'${user.id}')" href="javascript:;">
                        <i class="layui-icon">&#xe640;</i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="page" style="display: none;">
        <div>
            <a class="prev" href="">&lt;&lt;</a>
            <a class="num" href="">1</a>
            <span class="current">2</span>
            <a class="num" href="">3</a>
            <a class="num" href="">489</a>
            <a class="next" href="">&gt;&gt;</a>
        </div>
    </div>

</div>
<div class="ui_tb_h30"><div id="pagination" class="pagination"></div></div>
</body>
    </html>