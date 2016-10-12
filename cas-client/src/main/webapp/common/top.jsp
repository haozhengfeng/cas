<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="renderer" content="webkit"/>
	
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="resources/jquery-1.11.3.min.js"></script>
	
  </head>
  <body>
    <%-- <c:choose>
    	<c:when test="${!empty currentUser }">
    		欢迎${currentUser.username }登录&nbsp;&nbsp;<a href="logout">退出</a>
    	</c:when>
    	<c:otherwise>
    	    <a href="login">登录</a>&nbsp;&nbsp;<a href="login">注册</a>
    	</c:otherwise>
    </c:choose> --%>
    
    <a href="<%=basePath%>">首页</a>
    
    <!-- 当客户端没有用户登录时，进入一次cas后台，判断是否有用户登录 -->
    <% if (request.getRemoteUser() == null && request.getParameter("validated") == null && session.getAttribute("_const_cas_assertion_") == null) { %>
		<iframe id="myframe" name="myframe" src="login.jsp" style="display:none"></iframe>
	<% } %>
    
    <c:choose>
		<c:when test="${!empty currentUser }">
			欢迎${currentUser }登录&nbsp;&nbsp;<a href="user/center">会员中心</a>&nbsp;&nbsp;<a href="logout">退出</a>
		</c:when>
		<c:otherwise>
		    <a href="login.jsp">登录</a>&nbsp;&nbsp;<a href="regist.jsp">注册</a>
		</c:otherwise>
	</c:choose>
    
  </body>
</html>
