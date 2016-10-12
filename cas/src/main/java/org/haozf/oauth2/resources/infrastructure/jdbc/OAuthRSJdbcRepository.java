package org.haozf.oauth2.resources.infrastructure.jdbc;

import java.util.List;

import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.haozf.oauth2.core.domain.oauth.ClientDetails;
import org.haozf.oauth2.core.infrastructure.jdbc.AbstractJdbcRepository;
import org.haozf.oauth2.core.infrastructure.jdbc.AccessTokenRowMapper;
import org.haozf.oauth2.core.infrastructure.jdbc.ClientDetailsRowMapper;
import org.haozf.oauth2.resources.domain.rs.OAuthRSRepository;
import org.springframework.stereotype.Repository;

/**
 * 15-6-13
 *
 * @author Shengzhao Li
 */
@Repository("oauthRSJdbcRepository")
public class OAuthRSJdbcRepository extends AbstractJdbcRepository implements OAuthRSRepository {


    private static ClientDetailsRowMapper clientDetailsRowMapper = new ClientDetailsRowMapper();
    private static AccessTokenRowMapper accessTokenRowMapper = new AccessTokenRowMapper();


    @Override
    public AccessToken findAccessTokenByTokenId(String tokenId) {
        final String sql = " select * from oauth_access_token where token_id = ?";
        final List<AccessToken> list = jdbc.query(sql, accessTokenRowMapper, tokenId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public ClientDetails findClientDetailsByClientIdAndResourceIds(String clientId, String resourceIds) {
        final String sql = " select * from oauth_client_details where archived = 0 and client_id = ? and resource_ids = ? ";
        final List<ClientDetails> list = jdbc.query(sql, clientDetailsRowMapper, clientId, resourceIds);
        return list.isEmpty() ? null : list.get(0);
    }
}
