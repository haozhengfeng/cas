package org.haozf.oauth2.resources.domain.rs;

import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.domain.share.Repository;

/**
 * 2015/10/7
 *
 * @author Shengzhao Li
 */

public interface OAuthRSRepository extends Repository {

    AccessToken findAccessTokenByTokenId(String tokenId);

    ClientDetails findClientDetailsByClientIdAndResourceIds(String clientId, String resourceIds);
}