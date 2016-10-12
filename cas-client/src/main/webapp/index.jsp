<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cas-client</title>
</head>
<body>
<%-- 当JSP页面被请求时引入指定文件 --%>
<%-- <c:choose>
	<c:when test="${!empty currentUser }">
		欢迎${currentUser }登录&nbsp;&nbsp;<a href="logout">退出</a>
	</c:when>
	<c:otherwise>
	    <a href="login.jsp">登录</a>&nbsp;&nbsp;<a href="user/add">注册</a>
	</c:otherwise>
</c:choose> --%>
<jsp:include page="common/top.jsp"></jsp:include>
<h3>cas的测试项目</h3>
<div id="message"></div>
<div>
	<textarea id="smessage"></textarea>
</div>
<div>
	<input id="send" type="button" value="发送"/>
	<input id="close" type="button" value="关闭"/> 
</div>


<script type="text/javascript">
	var wsServer = 'ws://cas.client.tianyuan.com:8080/cas-client/hello';
	var websocket = new WebSocket(wsServer);
	websocket.onopen = function(evt) {
		onOpen(evt);
	};
	websocket.onclose = function(evt) {
		onClose(evt);
	};
	websocket.onmessage = function(evt) {
		onMessage(evt);
	};
	websocket.onerror = function(evt) {
		onError(evt);
	};
	function onOpen(evt) {
		console.log("Connected to WebSocket server.");
		$("#message").html("已连接"+"<br>");
	}
	function onClose(evt) {
		console.log("Disconnected");
		$("#message").append("断开连接"+"<br>");
	}
	function onMessage(evt) {
		console.log('Retrieved data from server: ' + evt.data);
		$("#message").append(evt.data+"<br>");
	}
	function onError(evt) {
		console.log('Error occured: ' + evt.data);
	}
	$("#send").on("click",function(){
		websocket.send($("#smessage").val());
		$("#smessage").val("");
	})
	$("#close").on("click",function(){
		websocket.close();
	})
</script>

</body>
</html>