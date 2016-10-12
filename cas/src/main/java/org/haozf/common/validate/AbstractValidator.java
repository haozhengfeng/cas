package org.haozf.common.validate;

import java.util.List;

import org.haozf.common.enums.ApiStatus;
import org.haozf.common.model.Json;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
/**
 * 重构：
 * @TODO 此类可以通过aop进行重构
 */
/**
 * 后台验证抽象类  所有验证类需要继承此抽象类
 * 2016年6月29日
 * @author haozhengfeng
 */
public abstract class AbstractValidator {
	/**
	 * 生成返回信息
	 * 
	 * @param field
	 * @param message
	 * @param errors
	 * @param json
	 */
	public void initMessage(String field, String message, Errors errors, Json json) {
		String ms = "";
		errors.rejectValue(field, null, message);
		List<ObjectError> es = errors.getAllErrors();
		for (ObjectError oe : es) {
			ms = ms + oe.getDefaultMessage();
		}
		json.setStatus(ApiStatus.fail);
		json.setMessage(ms);
	}
}
