package org.haozf.cas.flow;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.webflow.action.AbstractAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

/**
* 远程登陆票据提供Action.
* 根据InitialFlowSetupAction修改.
* 由于InitialFlowSetupAction为final类，因此只能将代码复制过来再进行修改.
* 
* @author haozhengfeng
*/
public class RemoteLoginAction extends AbstractAction {
    protected Event doExecute(final RequestContext context) throws Exception {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        
        // 客户端必须传递loginUrl参数过来，否则无法确定登陆目标页面
        if (StringUtils.hasText(request.getParameter("loginUrl"))) {
            context.getFlowScope().put("remoteLoginUrl", request.getParameter("loginUrl"));
        } else {
            request.setAttribute("remoteLoginMessage", "loginUrl parameter must be supported.");
            return result("checkTicketGrantingTicket");
        }
        
        // 若参数包含submit则进行提交，否则进行验证
        if (StringUtils.hasText(request.getParameter("isSubmit"))) {
        	
        	//判断是否传递用户名、密码
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String source = request.getParameter("source")==null?"":request.getParameter("source");
        	if(username == null || password == null){
            	return error();
            }
        	
        	UsernamePasswordCredential credential = new UsernamePasswordCredential();
        	credential.setUsername(username+"|"+source);
        	credential.setPassword(password);
            context.getFlowScope().put("credential", credential);
            return result("submit");
        } else {
        	return result("checkTicketGrantingTicket");
        }
    }
}