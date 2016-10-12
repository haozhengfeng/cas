package org.haozf.api.common.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.haozf.common.annotation.WithOutOauth;
import org.haozf.common.annotation.WithOutlogin;
import org.haozf.common.context.ApiContext;
import org.haozf.oauth2.authz.web.WebUtils;
import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.resources.oauth.exception.OAuth2AuthenticationException;
import org.haozf.oauth2.resources.service.OAuthRSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * access_token拦截器  验证accessToken 和  ClientDetails
 * 2016年6月28日
 * @author haozhengfeng
 */
public class OauthInterceptor implements HandlerInterceptor {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="oAuthRSService")
	private OAuthRSService rsService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		Method method = ((HandlerMethod) handler).getMethod();
		// 判断这个方法上是否有WithOutOauth这个注解
		if (method.isAnnotationPresent(WithOutOauth.class)) {
			return true;
		}
		
		// 通过oauth2.0验证授权
		try {
			//获取url参数token
			final String accessToken = getAccessToken(request);
			
			//判断url参数token是否为空 
			if (StringUtils.isEmpty(accessToken)) {
				throw new OAuth2AuthenticationException("Invalid access_token: " + accessToken);
			}
			
			//根据url参数token查询数据库  获取AccessToken对象
			final AccessToken token = rsService.loadAccessTokenByTokenId(accessToken);
			
			//验证查询出的token是否为空
			if (token != null) {
				logger.debug("Set username and clientId from AccessToken: {}", token);
				request.setAttribute(OAuth.OAUTH_CLIENT_ID, token.clientId());
			} else {
				logger.debug("Not found AccessToken by access_token: {}", accessToken);
			}
			
			// 验证 access token
			validateToken(accessToken, token);
			
			// 通过resource-id验证client details
			final ClientDetails clientDetails = rsService.loadClientDetails(token.clientId(), "");
			validateClientDetails(accessToken, token, clientDetails);
			
			ApiContext.accessToken.set(token);
			ApiContext.clientDetails.set(clientDetails);
			
		} catch (OAuth2AuthenticationException ae) {
			 final OAuthResponse oAuthResponse;
		        try {
		            oAuthResponse = OAuthRSResponse.errorResponse(401)
		                    .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
		                    .setErrorDescription(ae.getMessage())
		                    .buildJSONMessage();

		            WebUtils.writeOAuthJsonResponse((HttpServletResponse) response, oAuthResponse);

		            return false;
		        } catch (OAuthSystemException e) {
		            logger.error("Build JSON message error", e);
		            throw new IllegalStateException(e);
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
	
	
	private String getAccessToken(HttpServletRequest httpRequest) {
		final String authorization = httpRequest.getHeader("Authorization");
		if (authorization != null) {
			// bearer ab1ade69-d122-4844-ab23-7b109ad977f0
			return authorization.substring(6).trim();
		}
		return httpRequest.getParameter(OAuth.OAUTH_ACCESS_TOKEN);
	}

	private void validateToken(String token, AccessToken accessToken) throws OAuth2AuthenticationException {
		if (accessToken == null) {
			logger.debug("Invalid access_token: {}, because it is null", token);
			throw new OAuth2AuthenticationException("Invalid access_token: " + token + ", because it is null");
		}
		if (accessToken.tokenExpired()) {
			logger.debug("Invalid access_token: {}, because it is expired", token);
			throw new OAuth2AuthenticationException("Invalid access_token: " + token + ", because it is expired");
		}
	}

	private void validateClientDetails(String token, AccessToken accessToken, ClientDetails clientDetails) throws OAuth2AuthenticationException {
		if (clientDetails == null || clientDetails.archived()) {
			logger.debug("Invalid ClientDetails: {} by client_id: {}, it is null or archived", clientDetails, accessToken.clientId());
			throw new OAuth2AuthenticationException("Invalid client by token: " + token);
		}
	}

}
