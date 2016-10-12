<!DOCTYPE html>

<%@page import="java.net.URLEncoder"%>
<%@page import="org.springframework.binding.message.Message"%>
<%@page import="java.util.List"%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%
	String remoteUrl = "";
	if(request.getAttribute("remoteLoginUrl")!=null){
		remoteUrl = request.getAttribute("remoteLoginUrl").toString()+"?";
	}

	// 构造错误消息
    String errorMessage = "";
	if(request.getAttribute("messages")!=null){
		Message[] messages = (Message[])request.getAttribute("messages");
	    for(Message message : messages){
	    	errorMessage = URLEncoder.encode("&errorMessage=" + message.getText(),"utf-8");
	    }
	}
	
	String service = "";
	if(request.getAttribute("service")!= null && !"".equals(request.getAttribute("service"))){
		service = "service=" + URLEncoder.encode(request.getAttribute("service").toString());
	}
	//out.print("{\"total\":"+total+",\"rows\":");
%>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>CAS &#8211; Central Authentication Service</title>
  <script type="text/javascript">
        var remoteUrl = "${remoteLoginUrl}?";
        // 构造错误消息
        var errorMessage = "";
        errorMessage = encodeURIComponent('<c:forEach items="${messages}" var="message"><p class="message">${message.text}</p></c:forEach>');
        if(errorMessage!=""){
        	errorMessage = "&errorMessage=" + errorMessage;
        }
        
        // 构造service
        var service = "";
        <c:if test="${service != null && service != ''}">
        	service = "service=" + encodeURIComponent("${service}");
        </c:if>
        // 跳转回去
        window.location.href = remoteUrl + service + errorMessage+"&validated=true";
    </script>
</head>
</html>

