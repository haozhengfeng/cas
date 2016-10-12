package org.haozf.cas.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.haozf.user.model.User;
import org.haozf.user.service.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistController extends BaseController{
	
	@Resource
	private IUserService userService;
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(User user,HttpServletRequest req,Model model){
		String oldp = user.getPassword();
		userService.add(user);
		return "redirect:https://cas.server.com:8443/cas/remoteLogin?username="+user.getUsername()+"&password="+oldp+"&service=http://cas.client.tianyuan.com:8080/cas-client/&loginUrl=http://cas.client.tianyuan.com:8080/cas-client/user/add&isSubmit=true";
	}
	
}
