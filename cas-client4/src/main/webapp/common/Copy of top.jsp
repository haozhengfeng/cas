<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal" %>
<%@ page import="org.jasig.cas.client.util.AssertionHolder" %>
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

  </head>
  
  <body>
  	<%
	  	Object obj = request.getSession().getAttribute("_const_cas_assertion_");
		if (obj != null) {
			String cas = obj.toString();
			if (cas.length() > 0) {
				AttributePrincipal attributePrincipal = AssertionHolder.getAssertion().getPrincipal();
				String name = attributePrincipal.getName();
				System.out.println(name);
				System.out.println(attributePrincipal.getAttributes());
			}
		}else{
			System.out.println("未登录");
		}
  	%>
    <a href="login">登录</a>&nbsp;&nbsp;&nbsp;<a href="login">注册</a>
  </body>
</html>
