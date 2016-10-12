<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>远程CAS客户端登陆页面</title>
    
    <% if (request.getRemoteUser() != null) { %>
		<script type="text/javascript">
			window.parent.location.href = "/cas-client";        
        </script>
	<% } %>
</head>
<body>
    <iframe id="myframe" name="myframe" src="reallogin.jsp" style="display:none"></iframe>
    <div id="errorMessage"></div>
    <table>
	    <tr>
	        <td>用户名:</td>
	        <td><input type="text" name="username" id="username"></td>
	    </tr>
	    <tr>
	        <td>密&nbsp;&nbsp;码:</td>
	        <td><input type="password" name="password" id="password"></td>
	    </tr>
	    <tr>
	        <td colspan="2"><input type="button" value="登陆" onclick="login();"/></td>
	    </tr>
	</table>
	<script type="text/javascript">
		function login(){
			var mdocument = document.myframe.document;
			mdocument.getElementById("username").value = document.getElementById("username").value;
			mdocument.getElementById("password").value = document.getElementById("password").value;
			document.myframe.document.forms[0].submit()
		}
	</script>
</body>
</html>