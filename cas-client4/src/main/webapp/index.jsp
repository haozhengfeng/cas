<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cas-client2</title>
</head>
<body>
<%-- 当JSP页面被请求时引入指定文件 --%>
<c:choose>
	<c:when test="${!empty currentUser }">
		欢迎${currentUser }登录&nbsp;&nbsp;<a href="logout">退出</a>
	</c:when>
	<c:otherwise>
	    <a href="login">登录</a>&nbsp;&nbsp;<a href="login">注册</a>
	</c:otherwise>
</c:choose>
<h3>cas客户端4的测试项目</h3>
<h4>集成Saml11</h4>
</body>
</html>