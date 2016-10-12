package org.haozf.oauth2.core.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.haozf.oauth2.core.domain.oauth.OauthCode;
import org.springframework.jdbc.core.RowMapper;

/**
 * 15-6-13
 *
 * @author Shengzhao Li
 */
public class OauthCodeRowMapper implements RowMapper<OauthCode> {


    public OauthCodeRowMapper() {
    }

    @Override
    public OauthCode mapRow(ResultSet rs, int rowNum) throws SQLException {
        final OauthCode oauthCode = new OauthCode()
                .clientId(rs.getString("client_id"))
                .username(rs.getString("username"))
                .code(rs.getString("code"));

        oauthCode.createTime(rs.getTimestamp("create_time"));
        return oauthCode;

    }
}
