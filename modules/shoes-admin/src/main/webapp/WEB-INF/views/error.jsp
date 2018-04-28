<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${root}common/style/css_base.css" rel="stylesheet" type="text/css" />
<link href="${root}common/style/css_main.css" rel="stylesheet" type="text/css" />
	
<script type="text/javascript" src="${root}scripts/jquery/jquery-1.7.1.js"></script>

<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${data.code eq -903 }">
			<script type="text/javascript">
				$(function(){
					window.parent.location.href="${root}";
				})
			</script>
		</c:when>
		<c:otherwise>
			<div class="sys_tips st_f">
		      	抱歉，操作失败。<c:if test="${not empty data }">错误代码：${data.code }</c:if> <a href="#" onclick="javascript:history.go(-1)">返回</a>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>