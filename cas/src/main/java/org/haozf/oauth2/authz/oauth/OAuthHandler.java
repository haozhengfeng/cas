package org.haozf.oauth2.authz.oauth;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.haozf.oauth2.authz.service.OauthService;
import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.domain.share.BeanProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/7/3
 *
 * @author Shengzhao Li
 */
public abstract class OAuthHandler {


    private static final Logger LOG = LoggerFactory.getLogger(OAuthHandler.class);

    protected transient OauthService oauthService = BeanProvider.getBean(OauthService.class);


    private ClientDetails clientDetails;


    protected ClientDetails clientDetails() {
        if (clientDetails == null) {
            final String clientId = clientId();
            clientDetails = oauthService.loadClientDetails(clientId);
            LOG.debug("Load ClientDetails: {} by clientId: {}", clientDetails, clientId);
        }
        return clientDetails;
    }


    /**
     * Create  AccessToken response
     *
     * @param accessToken AccessToken
     * @param queryOrJson True is QueryMessage, false is JSON message
     * @return OAuthResponse
     * @throws org.apache.oltu.oauth2.common.exception.OAuthSystemException
     */
    protected OAuthResponse createTokenResponse(AccessToken accessToken, boolean queryOrJson) throws OAuthSystemException {
        final ClientDetails tempClientDetails = clientDetails();

        final OAuthASResponse.OAuthTokenResponseBuilder builder = OAuthASResponse
                .tokenResponse(HttpServletResponse.SC_OK)
                .location(tempClientDetails.getRedirectUri())
                .setAccessToken(accessToken.tokenId())
                .setExpiresIn(String.valueOf(accessToken.currentTokenExpiredSeconds()))
                .setTokenType(accessToken.tokenType());

        final String refreshToken = accessToken.refreshToken();
        if (StringUtils.isNotEmpty(refreshToken)) {
            builder.setRefreshToken(refreshToken);
        }

        return queryOrJson ? builder.buildQueryMessage() : builder.buildJSONMessage();
    }


    protected abstract String clientId();

}
