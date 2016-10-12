package org.haozf.identity.user.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/info", method = { RequestMethod.GET, RequestMethod.POST })
	public String info(HttpServletRequest request, Model model) {
		Object username = request.getSession().getAttribute("session_user_name");
		model.addAttribute("username", username);
		return "user/info";
	}
}
