package org.haozf.cas.client;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zhxm.utils.WebCommonUtils;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {
	public static final String CURRENT_USER = "currentUser";
	public static final String COOKIE_USER_NAME = "TIANYUAN_UN";
	
	@ModelAttribute
	public void initModel(Model model,HttpServletRequest request, HttpServletResponse response){
		
//		Cookie cookie = WebCommonUtils.getCookie(request, COOKIE_USER_NAME);
//		if (cookie != null) {
//			String username = cookie.getValue();
//			model.addAttribute(CURRENT_USER, username);
//			return ;
//		}
		
		model.addAttribute(CURRENT_USER, "");
		Object obj = request.getSession().getAttribute("_const_cas_assertion_");
		if (obj != null) {
			String cas = obj.toString();
			if (cas.length() > 0) {
				AttributePrincipal attributePrincipal = AssertionHolder.getAssertion().getPrincipal();
				Map<String, String> userMap = attributePrincipal.getAttributes();
				String username = userMap.get("username");
				WebCommonUtils.setCookie(request, response, COOKIE_USER_NAME, username, -1, true);
				model.addAttribute(CURRENT_USER, username);
			}
		}
	}
}
