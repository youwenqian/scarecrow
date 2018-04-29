<%--
  Created by IntelliJ IDEA.
  User: youwenqian
  Date: 18-3-24
  Time: 下午6:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="<%=basePath%>WEB-INF/classes/images/weixin/webwxgetmsgimg.jpeg" >
</body>
</html>
