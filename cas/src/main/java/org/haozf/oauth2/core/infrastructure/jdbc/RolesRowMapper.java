package org.haozf.oauth2.core.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.haozf.oauth2.core.domain.users.Roles;
import org.springframework.jdbc.core.RowMapper;

/**
 * 2016/6/3
 *
 * @author Shengzhao Li
 */
public class RolesRowMapper implements RowMapper<Roles> {

    public RolesRowMapper() {
    }

    @Override
    public Roles mapRow(ResultSet rs, int rowNum) throws SQLException {
        Roles roles = new Roles()
                .id(rs.getInt("id"))
                .roleName(rs.getString("role_name"));

        roles.guid(rs.getString("guid"))
                .createTime(rs.getTimestamp("create_time"));
        return roles;
    }
}
