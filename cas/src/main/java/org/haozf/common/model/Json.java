package org.haozf.common.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.haozf.common.enums.ApiStatus;

import com.alibaba.fastjson.JSON;

/**
 * json返回实体
 * @author haozhengfeng
 *
 */
public class Json {
	
	public Json (){}
	
	public Json (ApiStatus status,String message){
		this.status = status;
		this.message = message;
	}
	
	/**
	 * api接口状态 0 成功 1 失败
	 */
	private ApiStatus status;
	
	/**
	 * 返回信息
	 */
	private String message;
	
	/**
	 * 返回数据
	 */
	private Object data;


	public ApiStatus getStatus() {
		return status;
	}

	public void setStatus(ApiStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void json(HttpServletResponse response){
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");// 防止弹出的信息出现乱码
		try {
			PrintWriter out = response.getWriter();
			String jsonString = JSON.toJSONString(this);
			out.print(jsonString);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
