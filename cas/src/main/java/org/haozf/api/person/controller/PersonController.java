package org.haozf.api.person.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haozf.api.person.service.IPersonService;
import org.haozf.api.person.service.dto.PersonInfoDto;
import org.haozf.common.annotation.WithOutOauth;
import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.base.BaseController;
import org.haozf.common.enums.ApiStatus;
import org.haozf.common.model.Json;
import org.haozf.identity.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 个人会员接口 2016年7月4日
 * 
 * @author haozhengfeng
 */
@Controller("apiPersonController")
@RequestMapping("/api/person")
public class PersonController extends BaseController {

	@Resource
	private IPersonService personService;

	/**
	 * 获取用户信息 通过userID 获取个人会员信息 返回PersonInfoDto
	 * 
	 * @param userID
	 * @param u
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/info/{userID}", method = { RequestMethod.GET, RequestMethod.POST })
	@WithOutOauth
	@WithOutlogin
	@ResponseBody
	public Json info(User user,BindingResult result, HttpServletRequest request) {
		PersonInfoDto personInfoDto = personService.personInfo(user);
		json.setData(personInfoDto);
		json.setStatus(ApiStatus.success);
		return json;
	}

	@RequestMapping(value = "/info/update", method = RequestMethod.POST)
	@WithOutOauth
	@WithOutlogin
	@ResponseBody
	public Json update(PersonInfoDto person, BindingResult result, HttpServletRequest request) {
		personService.updatePersonInfo(person);
		return json;
	}

}
