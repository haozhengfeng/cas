package org.haozf.common.base;

import org.haozf.common.model.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public Json json = new Json();

	public Json getJson() {
		return json;
	}

	public void setJson(Json json) {
		this.json = json;
	}

//	
//	用户登录状态的验证已经已修改为通过interceptor拦截器实现
//	@see org.haozf.api.common.interceptor.UserInterceptor
//	
//	
//	@Resource
//	private CookieRetrievingCookieGenerator ticketGrantingTicketCookieGenerator;
//
//	@Resource
//	private TicketRegistry ticketRegistry;
//
//	/**
//	 * 执行controller之前先判断TGT
//	 * 
//	 * @param model
//	 * @param request
//	 * @param response
//	 */
//	@ModelAttribute
//	public void initModel(Model model, HttpServletRequest request, HttpServletResponse response) {
//		json = new Json();
//		if (logger.isDebugEnabled()) {
//			logger.debug("判断ticket");
//		}
//		String tgtId = this.ticketGrantingTicketCookieGenerator.retrieveCookieValue(request);
//		Ticket ticket = this.ticketRegistry.getTicket(tgtId);
//		if (logger.isDebugEnabled()) {
//			logger.debug("当前ticket" + ticket);
//		}
//
//		if (ticket == null) {
//			json.setStatus(ApiStatus.fail);
//			json.setMessage("请登录");
//		} else {
//			json.setStatus(ApiStatus.success);
//		}
//
//	}
	
}
