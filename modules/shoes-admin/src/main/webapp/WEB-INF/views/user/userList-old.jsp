<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<meta name="viewport" content="target-densitydpi=device-dpi, width=device-width, initial-scale=1, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
    </head>
<c:forEach items="${users}" var="user" varStatus="num">
    <tr>
<td><div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='${user.id}'><i class="layui-icon">&#xe605;</i></div></td>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>
            <c:choose>
                <c:when test="${user.isPayment == 0}">已支付</c:when>
                <c:otherwise>未支付</c:otherwise>
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
            <a title="查看"  onclick="x_admin_show('编辑','order-view.html')" href="javascript:;">
                <i class="layui-icon">&#xe63c;</i>
            </a>
            <a title="删除" onclick="member_del(this,'${user.id}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
            </a>
        </td>
    </tr>
</c:forEach>