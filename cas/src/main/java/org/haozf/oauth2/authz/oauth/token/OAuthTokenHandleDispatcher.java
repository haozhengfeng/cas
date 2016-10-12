package org.haozf.oauth2.authz.oauth.token;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * token处理程序分发器  分发给以下四种模式的处理器进行处理
 * 授权码模式(Authorization Code)
 * 隐式授权模式(Implicit)
 * 资源拥有者密码凭证模式(Resource Owner Password Credentials)
 * 客户端模式(Client Credentials) ClientCredentialsTokenHandler
 * 
 * @author haozhengfeng
 *
 */
public class OAuthTokenHandleDispatcher {

    private static final Logger LOG = LoggerFactory.getLogger(OAuthTokenHandleDispatcher.class);

    private List<OAuthTokenHandler> handlers = new ArrayList<>();

    private final OAuthTokenRequest tokenRequest;
    private final HttpServletResponse response;

    public OAuthTokenHandleDispatcher(OAuthTokenRequest tokenRequest, HttpServletResponse response) {
        this.tokenRequest = tokenRequest;
        this.response = response;

        initialHandlers();
    }

    /**
     * 初始化handler处理器
     */
    private void initialHandlers() {
        handlers.add(new RefreshTokenHandler());
        handlers.add(new ClientCredentialsTokenHandler());
        LOG.debug("Initialed '{}' OAuthTokenHandler(s): {}", handlers.size(), handlers);
    }

    public void dispatch() throws OAuthProblemException, OAuthSystemException {
        for (OAuthTokenHandler handler : handlers) {
            if (handler.support(tokenRequest)) {
                LOG.debug("Found '{}' handle OAuthTokenxRequest: {}", handler, tokenRequest);
                handler.handle(tokenRequest, response);
                return;
            }
        }
        throw new IllegalStateException("Not found 'OAuthTokenHandler' to handle OAuthTokenxRequest: " + tokenRequest);
    }
}
