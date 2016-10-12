package org.haozf.api.user.controller;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haozf.api.user.service.IUserService;
import org.haozf.common.annotation.WithOutOauth;
import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.base.BaseController;
import org.haozf.common.enums.ApiStatus;
import org.haozf.common.model.Json;
import org.haozf.common.model.SessionContext;
import org.haozf.identity.user.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户模块接口 2016年6月29日
 * 
 * @author haozhengfeng
 */
@Controller("apiUserController")
@RequestMapping("/api/user")
public class UserController extends BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private IUserService userService;

	@Resource
	private UserValidator userValidator;

	@RequestMapping(value = "/checkUser", method = { RequestMethod.GET, RequestMethod.POST })
	@WithOutlogin
	@ResponseBody
	public Json checkUserExists(HttpServletRequest request, User user, BindingResult result) {
		// 验证用户名是否存在
		userValidator.validateUsernameExist(user, result, json);
		if (result.hasErrors()) {
			return json;
		}

		json.setStatus(ApiStatus.success);
		json.setMessage("可以注册");
		return json;
	}

	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST })
	@WithOutlogin
	@ResponseBody
	public Json addUser(User user, BindingResult result, HttpServletRequest request) {
		userValidator.validateSource(user, result, json);
		userValidator.validateSourceOauthz(user, result, json);
		userValidator.validatesuserID(user, result, json);
		userValidator.validateUsernameAndEmailAndPhoneIsnull(user, result, json);
		userValidator.validateUsernameExist(user, result, json);
		userValidator.validatePasswordIsnull(user, result, json);

		if (result.hasErrors()) {
			return json;
		}

		// 添加的用户需要和access_token对应的资源一致
		userService.addUser(user);

		json.setStatus(ApiStatus.success);
		json.setMessage("注册成功！");
		return json;
	}

	/**
	 * 修改密码接口 通过用户id
	 * 
	 * @param user
	 * @param result
	 * @param request
	 */
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	@WithOutlogin
	@ResponseBody
	public Json passwordByUserID(User user, BindingResult result, HttpServletRequest request) {
		userValidator.validatesuserID(user, result, json);
		userValidator.validatePasswordIsnull(user, result, json);
		userService.updatePassWord(user);
		json.setStatus(ApiStatus.success);
		json.setMessage("修改密码成功！");
		return json;
	}

	/**
	 * 修改密码接口 通过源用户id
	 * 
	 * @param user
	 * @param result
	 * @param request
	 */
	@RequestMapping(value = "/password/sid", method = RequestMethod.POST)
	@WithOutlogin
	@ResponseBody
	public Json passwordBySuserID(User user, BindingResult result, HttpServletRequest request) {
		userValidator.validatePasswordIsnull(user, result, json);
		userService.updatePassWordBySuserID(user);
		json.setStatus(ApiStatus.success);
		json.setMessage("修改密码成功！");
		return json;
	}

	/**
	 * 修改密码接口 通过登录手机号
	 * 
	 * @param user
	 * @param result
	 * @param request
	 */
	@RequestMapping(value = "/password/mobile", method = RequestMethod.POST)
	@WithOutlogin
	@ResponseBody
	public Json passwordByMobile(User user, BindingResult result, HttpServletRequest request) {
		userValidator.validatePasswordIsnull(user, result, json);
		userService.updatePassWordByMobile(user);
		json.setStatus(ApiStatus.success);
		json.setMessage("修改密码成功！");
		return json;
	}

	/**
	 * 修改密码接口 通过登录邮箱
	 * 
	 * @param user
	 * @param result
	 * @param request
	 */
	@RequestMapping(value = "/password/email", method = RequestMethod.POST)
	@WithOutlogin
	@ResponseBody
	public Json passwordByEmail(User user, BindingResult result, HttpServletRequest request) {
		userValidator.validatePasswordIsnull(user, result, json);
		userService.updatePassWordByEmail(user);
		json.setStatus(ApiStatus.success);
		json.setMessage("修改密码成功！");
		return json;
	}
}
