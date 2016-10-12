package org.haozf.oauth2.resources.oauth.resources;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.rsfilter.OAuthDecision;
import org.apache.oltu.oauth2.rsfilter.OAuthRSProvider;
import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.domain.share.BeanProvider;
import org.haozf.oauth2.resources.domain.rs.RSOAuthClient;
import org.haozf.oauth2.resources.service.OAuthRSService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2015/7/8
 *
 * @author Shengzhao Li
 */
public class MkkOAuthRSProvider implements OAuthRSProvider {

    private static final Logger LOG = LoggerFactory.getLogger(MkkOAuthRSProvider.class);


    private transient OAuthRSService rsService = BeanProvider.getBean(OAuthRSService.class);

    @Override
    public OAuthDecision validateRequest(String rsId, String token, HttpServletRequest req) throws OAuthProblemException {
        LOG.debug("Call OAuthRSProvider, rsId: {},token: {},req: {}", new Object[]{rsId, token, req});

        AccessToken accessToken = rsService.loadAccessTokenByTokenId(token);
        validateToken(token, accessToken);

        ClientDetails clientDetails = rsService.loadClientDetails(accessToken.clientId(), rsId);
        validateClientDetails(token, accessToken, clientDetails);


        //TODO: It is OK?
        MkkOAuthDecision oAuthDecision = new MkkOAuthDecision().setOAuthClient(new RSOAuthClient(clientDetails));
        oAuthDecision.setPrincipal(new MkkOAuthPrincipal(accessToken.username()));
        oAuthDecision.setAuthorized(true);

        return oAuthDecision;
    }

    private void validateClientDetails(String token, AccessToken accessToken, ClientDetails clientDetails) throws OAuthProblemException {
        if (clientDetails == null || clientDetails.archived()) {
            LOG.debug("Invalid ClientDetails: {} by client_id: {}, it is null or archived", clientDetails, accessToken.clientId());
            throw OAuthProblemException.error(OAuthError.ResourceResponse.INVALID_TOKEN)
                    .description("Invalid client by token: " + token);
        }
    }

    private void validateToken(String token, AccessToken accessToken) throws OAuthProblemException {
        if (accessToken == null) {
            LOG.debug("Invalid access_token: {}, because it is null", token);
            throw OAuthProblemException.error(OAuthError.ResourceResponse.INVALID_TOKEN)
                    .description("Invalid access_token: " + token);
        }
        if (accessToken.tokenExpired()) {
            LOG.debug("Invalid access_token: {}, because it is expired", token);
            throw OAuthProblemException.error(OAuthError.ResourceResponse.EXPIRED_TOKEN)
                    .description("Expired access_token: " + token);
        }
    }
}
