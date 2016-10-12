package org.haozf.cas.client.validation;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl;
import org.jasig.cas.client.authentication.GatewayResolver;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;

/**
 * 天远： 远程认证过滤器. 由于AuthenticationFilter的doFilter方法被声明为final，
 * 只好重新实现一个认证过滤器，支持localLoginUrl设置.
 * 
 * @author GuoLin
 * 
 */
public class RemoteAuthenticationFilter extends AbstractCasFilter {

	public static final String CONST_CAS_GATEWAY = "_const_cas_gateway_";

	/**
	 * 本地登陆页面URL.
	 */
	private String localLoginUrl;

	/**
	 * The URL to the CAS Server login.
	 */
	private String casServerLoginUrl;

	/**
	 * Whether to send the renew request or not.
	 */
	private boolean renew = false;

	/**
	 * Whether to send the gateway request or not.
	 */
	private boolean gateway = false;
	
    private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();

	protected void initInternal(final FilterConfig filterConfig) throws ServletException {
		super.initInternal(filterConfig);
		setCasServerLoginUrl(getPropertyFromInitParams(filterConfig, "casServerLoginUrl", null));
		log.trace("Loaded CasServerLoginUrl parameter: " + this.casServerLoginUrl);
		setLocalLoginUrl(getPropertyFromInitParams(filterConfig, "localLoginUrl", null));
		log.trace("Loaded LocalLoginUrl parameter: " + this.localLoginUrl);
		setRenew(Boolean.parseBoolean(getPropertyFromInitParams(filterConfig, "renew", "false")));
		log.trace("Loaded renew parameter: " + this.renew);
		setGateway(Boolean.parseBoolean(getPropertyFromInitParams(filterConfig, "gateway", "false")));
		log.trace("Loaded gateway parameter: " + this.gateway);
	}

	public void init() {
		super.init();
		CommonUtils.assertNotNull(this.localLoginUrl, "localLoginUrl cannot be null.");
		CommonUtils.assertNotNull(this.casServerLoginUrl, "casServerLoginUrl cannot be null.");
	}

	public final void doFilter(final ServletRequest servletRequest, final ServletResponse servletResponse, final FilterChain filterChain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final HttpSession session = request.getSession(false);
		final String ticket = request.getParameter(getArtifactParameterName());
		final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;
		final boolean wasGatewayed = session != null && session.getAttribute(CONST_CAS_GATEWAY) != null;

		// 如果访问路径为localLoginUrl且带有validated参数则跳过
		URL url = new URL(localLoginUrl);
		final boolean isValidatedLocalLoginUrl = request.getRequestURI().endsWith(url.getPath()) && CommonUtils.isNotBlank(request.getParameter("validated"));

		if (!isValidatedLocalLoginUrl && CommonUtils.isBlank(ticket) && assertion == null && !wasGatewayed) {
			log.debug("no ticket and no assertion found");
			if (this.gateway) {
				log.debug("setting gateway attribute in session");
				request.getSession(true).setAttribute(CONST_CAS_GATEWAY, "yes");
			}

			final String serviceUrl = constructServiceUrl(request, response);

			if (log.isDebugEnabled()) {
				log.debug("Constructed service url: " + serviceUrl);
			}

			String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl, getServiceParameterName(), serviceUrl, this.renew, this.gateway);

			// 加入localLoginUrl
			urlToRedirectTo += (urlToRedirectTo.contains("?") ? "&" : "?") + "loginUrl=" + URLEncoder.encode(localLoginUrl, "utf-8");

			if (log.isDebugEnabled()) {
				log.debug("redirecting to \"" + urlToRedirectTo + "\"");
			}

			response.sendRedirect(urlToRedirectTo);
			return;
		}

		 if (session != null) {
			 log.debug("removing gateway attribute from session");
			 session.setAttribute(CONST_CAS_GATEWAY, null);
		 }

		 
		filterChain.doFilter(request, response);
		
//		final HttpServletRequest request = (HttpServletRequest) servletRequest;
//        final HttpServletResponse response = (HttpServletResponse) servletResponse;
//        final HttpSession session = request.getSession(false);
//        final Assertion assertion = session != null ? (Assertion) session.getAttribute(CONST_CAS_ASSERTION) : null;
//
//        if (assertion != null) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String serviceUrl = constructServiceUrl(request, response);
//        final String ticket = CommonUtils.safeGetParameter(request,getArtifactParameterName());
//        final boolean wasGatewayed = this.gatewayStorage.hasGatewayedAlready(request, serviceUrl);
//
//        if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        final String modifiedServiceUrl;
//
//        log.debug("no ticket and no assertion found");
//        if (this.gateway) {
//            log.debug("setting gateway attribute in session");
//            modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(request, serviceUrl);
//        } else {
//            modifiedServiceUrl = serviceUrl;
//        }
//
//        if (log.isDebugEnabled()) {
//            log.debug("Constructed service url: " + modifiedServiceUrl);
//        }
//
//        final String urlToRedirectTo = CommonUtils.constructRedirectUrl(this.casServerLoginUrl, getServiceParameterName(), modifiedServiceUrl, this.renew, this.gateway);
//
//        if (log.isDebugEnabled()) {
//            log.debug("redirecting to \"" + urlToRedirectTo + "\"");
//        }
//
//		URL url = new URL(modifiedServiceUrl);
//		final boolean isValidatedLocalLoginUrl = request.getRequestURI().endsWith(url.getPath()) && CommonUtils.isNotBlank(request.getParameter("validated"));
//		if(isValidatedLocalLoginUrl){
//			response.sendRedirect(urlToRedirectTo);
//		}else{
//			response.sendRedirect(localLoginUrl);
//		}
	}

	public final void setRenew(final boolean renew) {
		this.renew = renew;
	}

	public final void setGateway(final boolean gateway) {
		this.gateway = gateway;
	}

	public final void setCasServerLoginUrl(final String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public final void setLocalLoginUrl(String localLoginUrl) {
		this.localLoginUrl = localLoginUrl;
	}
	
	public final void setGatewayStorage(final GatewayResolver gatewayStorage) {
    	this.gatewayStorage = gatewayStorage;
    }
}
