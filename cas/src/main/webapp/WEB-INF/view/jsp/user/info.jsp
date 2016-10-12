<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="zh_CN">
<head>
  <meta charset="UTF-8" />
  <title>用户中心</title>
  
  <link rel="stylesheet" href="../css/cas.css" />  <!--[if lt IE 9]>
    <script src="//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6.1/html5shiv.js" type="text/javascript"></script>
  <![endif]-->
</head>
<body>

<div class="top">
   <div class="layout">
       <div class="fl">
           <a href="http://www.d1cm.com" title="第一工程机械网" target="_blank">第一工程机械网</a><span> | </span>
           <a href="http://product.d1cm.com" title="买新机" target="_blank">买新机</a><span> | </span>
           <a href="/" title="二手机" >二手机</a><span> | </span>
           <a href="http://www.maipeijian.com" title="麦配件" target="_blank">麦配件</a><span> | </span>
           <a href="http://app.d1cm.com/" title="移动应用" target="_blank">移动应用</a>
       </div>
       <div class="fr">
      		<c:if test="${empty username}">
      		<a href="${ctx }/login.jsp" title="登录" >登录</a><span> | </span>
      		<a href="${ctx }/register.jsp" title="注册">注册</a><span> | </span>
      		</c:if>
      		
      		<c:if test="${not empty username}">
      		您好！&nbsp;
		<a class="red" id="usename" target="_self" href="${ctx }/member/">${username }</a>
		<span> | </span>
		<a id="aLogout" target="_top" href="../logout">退出</a>
		<span> | </span>
		</c:if>
		
		<a href="${ctx }/member?flag=favorite" title="我的收藏">我的收藏</a>
		<span> | </span>
		客服电话：<span class="phone"><fmt:message key="site.phone" /></span>
       </div>
   </div>
</div>
</body>
</html>