<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>远程CAS客户端登陆页面</title>
    
    <script type="text/javascript" src="resources/jquery-1.11.3.min.js"></script>
    
    <script type="text/javascript">
    function getParam(name) {
        var queryString = window.location.search;
        var param = queryString.substr(1, queryString.length - 1).split("&");
        for (var i = 0; i < param.length; i++) {
            var keyValue = param[i].split("=");
            if (keyValue[0] == name) return keyValue[1];
        }
        return null;
    }
    function init() {
        // 显示异常信息
        var error = getParam("errorMessage");
        if (error) {
            parent.document.getElementById("errorMessage").innerHTML = decodeURIComponent(error);
        }
        // 注入service
        var service = getParam("service");
        if (service)
            document.getElementById("service").value = decodeURIComponent(service);
        else
            document.getElementById("service").value = location.href;
    }
    </script>
</head>
<body>
    <h1>远程CAS客户端登陆页面</h1>
    <% if (request.getRemoteUser() == null) { %>
        <div id="errorMessage"></div>
        <form id="myLoginForm" name="myform" action="https://cas.server.com:8443/cas/remoteLogin" method="post">
            <input type="hidden" id="service" name="service" value="">
            <input type="hidden" id="loginUrl" name="loginUrl" value="http://cas.client.tianyuan.com:8080/cas-client/reallogin.jsp">
            <input type="hidden" id="isSubmit" name="isSubmit" value="true" />
            <input type="hidden" id="source" name="source" value="ershou" />
            <table>
                <tr>
                    <td>用户名:</td>
                    <td><input type="text" name="username" id="username"/></td>
                </tr>
                <tr>
                    <td>密&nbsp;&nbsp;码:</td>
                    <td><input type="password" name="password" id="password"/></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" value="登陆" /></td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">init()</script>
    <% } else { %>
        <%-- <div class="welcome">您好：<%= request.getRemoteUser() %></div>
        <div id="logout">
            <a href="logout">退出</a>
        </div> --%>
        <script type="text/javascript">
			window.parent.location.href = "/cas-client";        
        </script>
    <% } %>
</body>
</html>