package org.haozf.api.user.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.haozf.api.user.service.IUserService;
import org.haozf.common.context.ApiContext;
import org.haozf.common.model.Json;
import org.haozf.common.validate.AbstractValidator;
import org.haozf.identity.user.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("userValidator")
public class UserValidator extends AbstractValidator implements Validator {

	@Resource
	private IUserService userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
	}

	/**
	 * 验证数据来源
	 * 
	 * @param user
	 * @param errors
	 * @param json
	 */
	public void validateSource(User user, Errors errors, Json json) {
		if (StringUtils.isEmpty(user.getRegistSource())) {
			String message = "未提供资源标识！";
			String field = "registSource";
			initMessage(field, message, errors, json);
		}
	}

	/**
	 * 验证用户登录名 手机 邮箱是否为空
	 * 
	 * @param user
	 * @param errors
	 * @param json
	 */
	public void validateUsernameAndEmailAndPhoneIsnull(User user, Errors errors, Json json) {
		if (StringUtils.isEmpty(user.getUsername()) && StringUtils.isEmpty(user.getLoginEmail()) && StringUtils.isEmpty(user.getLoginMobile())) {
			String message = "用户名不能为空！";
			String field = "username";
			initMessage(field, message, errors, json);
		}
	}

	/**
	 * 验证密码是否为空
	 * 
	 * @param user
	 * @param errors
	 * @param json
	 */
	public void validatePasswordIsnull(User user, Errors errors, Json json) {
		if (StringUtils.isEmpty(user.getPassword())) {
			String message = "用户名不能为空！";
			String field = "username";
			initMessage(field, message, errors, json);
		}
	}

	/**
	 * 验证用户名
	 * 
	 * @param user
	 * @param errors
	 * @param json
	 */
	public void validateUsernameExist(User user, Errors errors, Json json) {
		int num = 0;
		String username = user.getUsername();
		if (!StringUtils.isEmpty(username)) {
			num = userService.hasUserByNameOrPhoneOrEmail(username);
			if (num > 0) {
				String message = "用户名已存在！";
				String field = "username";
				initMessage(field, message, errors, json);
				return;
			}
		}

		String email = user.getLoginEmail();
		if (!StringUtils.isEmpty(email)) {
			num = userService.hasUserByNameOrPhoneOrEmail(email);
			if (num > 0) {
				String message = "邮箱已存在！";
				String field = "username";
				initMessage(field, message, errors, json);
				return;
			}
		}

		String phone = user.getLoginMobile();
		if (!StringUtils.isEmpty(phone)) {
			num = userService.hasUserByNameOrPhoneOrEmail(phone);
			if (num > 0) {
				String message = "手机号已存在！";
				String field = "username";
				initMessage(field, message, errors, json);
				return;
			}
		}
	}

	/**
	 * 验证源id
	 * 
	 * @param user
	 * @param errors
	 * @param json
	 */
	public void validatesuserID(User user, Errors errors, Json json) {
		if (StringUtils.isEmpty(user.getSuserID())) {
			String message = "用户源id不能为空！";
			String field = "username";
			initMessage(field, message, errors, json);
		}
	}
	
	/**
	 * 验证授权
	 * @param user
	 * @param errors
	 * @param json
	 */
	public void validateSourceOauthz(User user, Errors errors, Json json){
		String resourceid = ApiContext.clientDetails.get().getClientId();
		String registsource = user.getRegistSource();
		if(!registsource.equals(resourceid)){
			String message = "授权错误！";
			String field = "username";
			initMessage(field, message, errors, json);
		}
		
	}

}
