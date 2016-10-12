package org.haozf.oauth2.authz.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.haozf.oauth2.authz.oauth.token.OAuthTokenHandleDispatcher;
import org.haozf.oauth2.authz.web.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 获取access_token请求
 * URL: oauth/token
 * @author haozhengfeng
 * 2016年6月24日
 */
@Controller
@RequestMapping("oauth/")
public class OauthTokenController {


    /**
     * Handle grant_types as follows:
     * <p/>
     * grant_type=authorization_code
     * grant_type=password
     * grant_type=refresh_token
     * grant_type=client_credentials
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws OAuthSystemException
     */
    @RequestMapping("token")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException {
        try {
            OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);

            OAuthTokenHandleDispatcher tokenHandleDispatcher = new OAuthTokenHandleDispatcher(tokenRequest, response);
            tokenHandleDispatcher.dispatch();

        } catch (OAuthProblemException e) {
            //exception
            OAuthResponse oAuthResponse = OAuthASResponse
                    .errorResponse(HttpServletResponse.SC_FOUND)
                    .location(e.getRedirectUri())
                    .error(e)
                    .buildJSONMessage();
            WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
        }

    }
}
