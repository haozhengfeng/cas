package org.haozf.cas.flow;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

import org.jasig.cas.CentralAuthenticationService;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.ticket.TicketException;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 * 天远：注册后自动登录
 * @author haozhengfeng
 *
 */
public class AutoLoginAfterRegistController extends AbstractController{

	@NotNull
	private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;
	@NotNull
	private CentralAuthenticationService centralAuthenticationService;

	/**
	 * 获取用户名密码,验证有效性,生成相关票据并绑定注册,添加cookie
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView signinView = new ModelAndView();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// 此处应根据用户名密码去数据库校验,证实传递的注册用户信息的有效性,代码略
		bindTicketGrantingTicket(username, password, request, response);
		String viewName = getSignInView(request);
		signinView.setViewName(viewName);
		return signinView;
	}

	/**
	 * 具体生成相关票据并绑定注册,添加cookie实现方法
	 * 
	 * @param loginName
	 * @param loginPassword
	 * @param request
	 * @param response
	 */
	protected void bindTicketGrantingTicket(String loginName, String loginPassword, HttpServletRequest request, HttpServletResponse response) {
		try {
			UsernamePasswordCredential credentials = new UsernamePasswordCredential();
			credentials.setUsername(loginName);
			credentials.setPassword(loginPassword);
			String ticketGrantingTicket = centralAuthenticationService.createTicketGrantingTicket(credentials);
			ticketGrantingTicketCookieGenerator.addCookie(request, response, ticketGrantingTicket);
		} catch (TicketException te) {
			logger.error("Validate the login name " + loginName + " failure, can't bind the TGT!", te);
		} catch (Exception e) {
			logger.error("bindTicketGrantingTicket has exception.", e);
		}

	}

	/**
	 * 获取service参数并跳转页面
	 * 
	 * @param request
	 * @return
	 */
	protected String getSignInView(HttpServletRequest request) {
		String service = ServletRequestUtils.getStringParameter(request, "service", "");
		return ("redirect:login" + (service.length() > 0 ? "?service=" + service : ""));
	}

	public void setTicketGrantingTicketCookieGenerator(CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator) {
		this.ticketGrantingTicketCookieGenerator = ticketGrantingTicketCookieGenerator;
	}

	public void setCentralAuthenticationService(CentralAuthenticationService centralAuthenticationService) {
		this.centralAuthenticationService = centralAuthenticationService;
	}
}
