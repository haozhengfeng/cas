package org.haozf.api.common.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.enums.ApiStatus;
import org.haozf.common.model.Json;
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

	public Json json;

	@Resource
	private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;

	@Resource
	private TicketRegistry ticketRegistry;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Json json = new Json();
		String tgtId = this.ticketGrantingTicketCookieGenerator.retrieveCookieValue(request);
		Ticket ticket = this.ticketRegistry.getTicket(tgtId);
		
		if (ticket == null) {
			json.setStatus(ApiStatus.fail);
			json.setMessage("请登录");
		} else {
			json.setStatus(ApiStatus.success);
		}
		
		Method method = ((HandlerMethod) handler).getMethod();
		// 判断这个方法上是否有WithOutlogin这个注解
		if (!method.isAnnotationPresent(WithOutlogin.class)) {
			//返回json
			json.json(response);
			return false;
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
