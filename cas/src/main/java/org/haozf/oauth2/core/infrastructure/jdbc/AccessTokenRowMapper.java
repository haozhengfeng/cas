package org.haozf.oauth2.core.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.haozf.oauth2.core.domain.oauth.AccessToken;
import org.springframework.jdbc.core.RowMapper;

/**
 * 15-6-20
 *
 * @author Shengzhao Li
 */
public class AccessTokenRowMapper implements RowMapper<AccessToken> {


    public AccessTokenRowMapper() {
    }

    @Override
    public AccessToken mapRow(ResultSet rs, int rowNum) throws SQLException {
        final AccessToken oauthCode = new AccessToken()
                .tokenId(rs.getString("token_id"))
                .tokenExpiredSeconds(rs.getInt("token_expired_seconds"))
                .authenticationId(rs.getString("authentication_id"))

                .username(rs.getString("username"))
                .clientId(rs.getString("client_id"))
                .tokenType(rs.getString("token_type"))

                .refreshTokenExpiredSeconds(rs.getInt("refresh_token_expired_seconds"))
                .refreshToken(rs.getString("refresh_token"));

        oauthCode.createTime(rs.getTimestamp("create_time"));


        return oauthCode;

    }
}
