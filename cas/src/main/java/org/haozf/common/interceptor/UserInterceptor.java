package org.haozf.common.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.haozf.common.annotation.WithOutlogin;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.registry.TicketRegistry;
import org.jasig.cas.web.support.CookieRetrievingCookieGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;

	@Resource
	private TicketRegistry ticketRegistry;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String tgtId = this.ticketGrantingTicketCookieGenerator.retrieveCookieValue(request);
		Ticket ticket = this.ticketRegistry.getTicket(tgtId);
		
		if (ticket == null) {
			request.getSession().invalidate();
		}
		
		Object username = request.getSession().getAttribute("session_user_name");
		Method method = ((HandlerMethod) handler).getMethod();
		// 判断这个方法上是否有WithOutlogin这个注解
		if (!method.isAnnotationPresent(WithOutlogin.class)) {
			if (username == null){
				response.sendRedirect("../logout");
				return false;
			} 
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}

}
