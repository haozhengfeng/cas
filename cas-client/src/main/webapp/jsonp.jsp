<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%      	
	String callback = request.getParameter("callback"); 
	String jsonStr = "{\"name\":\"张三\",\"age\":28}";
	//最终返回的数据为：success_jsonpCallback({"name":"张三","age":28})
	String renderStr = callback+"("+jsonStr+")";
	response.setContentType("text/plain;charset=UTF-8");
	response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
	response.getWriter().write(renderStr);
%> 