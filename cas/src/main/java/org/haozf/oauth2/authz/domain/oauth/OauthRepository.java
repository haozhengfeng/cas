package org.haozf.oauth2.authz.domain.oauth;

import java.util.List;

import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.domain.oauth.OauthCode;
import org.haozf.oauth2.core.domain.share.Repository;

/**
 * 15-6-13
 *
 * @author Shengzhao Li
 */
public interface OauthRepository extends Repository {

    ClientDetails findClientDetails(String clientId);

    int saveClientDetails(ClientDetails clientDetails);

    int saveOauthCode(OauthCode oauthCode);

    OauthCode findOauthCode(String code, String clientId);

    OauthCode findOauthCodeByUsernameClientId(String username, String clientId);

    int deleteOauthCode(OauthCode oauthCode);

    AccessToken findAccessToken(String clientId, String username, String authenticationId);

    int deleteAccessToken(AccessToken accessToken);

    int saveAccessToken(AccessToken accessToken);

    AccessToken findAccessTokenByRefreshToken(String refreshToken, String clientId);


    List<ClientDetails> findClientDetailsListByClientId(String clientId);
}
