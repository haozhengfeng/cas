package org.haozf.user.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haozf.cas.client.BaseController;
import org.haozf.user.model.User;
import org.haozf.user.service.IUserService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/user")
@Controller
public class UserController extends BaseController{
	@Resource
	private IUserService userService;
	
	@RequestMapping("list")
	public String list(Model model) {
		model.addAttribute("users", userService.list());
		return "list";
	}
	
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "add";
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public String add(User user,HttpServletRequest req,Model model) {
		String oldp = user.getPassword();
		userService.add(user);
//		return "redirect:https://cas.server.com:8443/cas/registerLogin?username="+user.getUsername()+"&password="+oldp+"&service=http://cas.client.tianyuan.com:8080/cas-client/login";
		return "redirect:https://cas.server.com:8443/cas/remoteLogin?username="+user.getUsername()+"&password="+oldp+"&service=http://cas.client.tianyuan.com:8080/cas-client/&loginUrl=http://cas.client.tianyuan.com:8080/cas-client/user/add&isSubmit=true";
	}
	
	
	@RequestMapping(value="update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model) {
		User user = userService.load(id);
		model.addAttribute("user", user);
		return "update";
	}
	
	@RequestMapping(value="center")
	public String center(Model model){
		AttributePrincipal attributePrincipal = AssertionHolder.getAssertion().getPrincipal();
		Map<String, String> userMap = attributePrincipal.getAttributes();
		model.addAttribute("user", userMap);
		return "center";
	}
}
