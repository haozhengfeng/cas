package org.haozf.oauth2.authz.oauth.validator;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.haozf.oauth2.authz.service.OauthService;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.domain.share.BeanProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 验证客户端传来的参数
 * client_id redirect_uri scope client_secret
 * 
 * @author haozhengfeng
 *
 */
public abstract class AbstractClientDetailsValidator {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractClientDetailsValidator.class);


    protected OauthService oauthService = BeanProvider.getBean(OauthService.class);

    protected OAuthRequest oauthRequest;

    private ClientDetails clientDetails;

    protected AbstractClientDetailsValidator(OAuthRequest oauthRequest) {
        this.oauthRequest = oauthRequest;
    }


    protected ClientDetails clientDetails() {
        if (clientDetails == null) {
            clientDetails = oauthService.loadClientDetails(oauthRequest.getClientId());
        }
        return clientDetails;
    }


    protected OAuthResponse invalidClientErrorResponse() throws OAuthSystemException {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                .setErrorDescription("Invalid client_id '" + oauthRequest.getClientId() + "'")
                .buildJSONMessage();
    }

    protected OAuthResponse invalidRedirectUriResponse() throws OAuthSystemException {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.CodeResponse.INVALID_REQUEST)
                .setErrorDescription("Invalid redirect_uri '" + oauthRequest.getRedirectURI() + "'")
                .buildJSONMessage();
    }

    protected OAuthResponse invalidScopeResponse() throws OAuthSystemException {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                .setError(OAuthError.CodeResponse.INVALID_SCOPE)
                .setErrorDescription("Invalid scope '" + oauthRequest.getScopes() + "'")
                .buildJSONMessage();
    }


    public final OAuthResponse validate() throws OAuthSystemException {
        final ClientDetails details = clientDetails();
        if (details == null) {
            return invalidClientErrorResponse();
        }

        return validateSelf(details);
    }


    protected boolean excludeScopes(Set<String> scopes, ClientDetails clientDetails) {
        final String clientDetailsScope = clientDetails.scope();          //read write
        for (String scope : scopes) {
            if (!clientDetailsScope.contains(scope)) {
                LOG.debug("Invalid scope - ClientDetails scopes '{}' exclude '{}'", clientDetailsScope, scope);
                return true;
            }
        }
        return false;
    }


    protected OAuthResponse invalidClientSecretResponse() throws OAuthSystemException {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                .setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                .setErrorDescription("Invalid client_secret by client_id '" + oauthRequest.getClientId() + "'")
                .buildJSONMessage();
    }


    protected abstract OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException;
}
