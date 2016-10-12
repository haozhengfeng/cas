package org.haozf.cas.client2;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.zhxm.utils.WebCommonUtils;

import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 天远：子系统 login logout
 * 
 * @author haozhengfeng
 * 
 */
@Controller
public class LoginController {

	private String casServerLogout;

	private static final String CURRENT_USER = "currentUser";
	private static final String COOKIE_USER_NAME = "TIANYUAN_UN";

	@RequestMapping(value = { "", "/" })
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute(CURRENT_USER, "");
		Cookie cookie = WebCommonUtils.getCookie(request, COOKIE_USER_NAME);
		if (cookie != null) {
			String username = cookie.getValue();
			model.addAttribute(CURRENT_USER, username);
		}
		
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
		
		return "index";
	}

	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request, HttpServletResponse response) {
		// cas 返回后的逻辑
		// 将登录用户记录到cookie
//		Object obj = request.getSession().getAttribute("_const_cas_assertion_");
//		if (obj != null) {
//			String cas = obj.toString();
//			if (cas.length() > 0) {
//				AttributePrincipal attributePrincipal = AssertionHolder.getAssertion().getPrincipal();
//				Map<String, String> userMap = attributePrincipal.getAttributes();
//				String username = userMap.get("username");
//
////				WebCommonUtils.setCookie(request, response, COOKIE_USER_NAME, username, -1, true);
//				model.addAttribute(CURRENT_USER, username);
//			}
//		}
		return "redirect:/";
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,HttpServletResponse response) {
		//清除cookie
		WebCommonUtils.deleteCookie(request, response, COOKIE_USER_NAME, true);
		String URL = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		URL = URL.substring(0, URL.indexOf(contextPath) + contextPath.length());
		request.getSession().invalidate();
		return "redirect:" + casServerLogout + "?service=" + URL;
	}

	/**
	 * 登录头文件
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/top")
	public String top(Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute(CURRENT_USER, "");
		Object obj = request.getSession().getAttribute("_const_cas_assertion_");
		if (obj != null) {
			String cas = obj.toString();
			if (cas.length() > 0) {
				AttributePrincipal attributePrincipal = AssertionHolder.getAssertion().getPrincipal();
				Map<String, String> userMap = attributePrincipal.getAttributes();
				String username = userMap.get("username");
				// 将登录用户记录到cookie
//				WebCommonUtils.setCookie(request, response, COOKIE_USER_NAME, username, -1, true);
				model.addAttribute(CURRENT_USER, userMap);
			}
		}
		return "common/top";
	}

	@Value("#{configProperties['cas.server.logout']}")
	public void setCasServerLogout(String casServerLogout) {
		this.casServerLogout = casServerLogout;
	}

}
