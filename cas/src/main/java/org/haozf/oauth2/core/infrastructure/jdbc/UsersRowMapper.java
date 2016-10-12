package org.haozf.oauth2.core.infrastructure.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.haozf.oauth2.core.domain.users.Users;
import org.springframework.jdbc.core.RowMapper;

/**
 * 2016/6/3
 *
 * @author Shengzhao Li
 */
public class UsersRowMapper implements RowMapper<Users> {

    public UsersRowMapper() {
    }

    @Override
    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
        Users users = new Users()
                .username(rs.getString("username"))
                .password(rs.getString("password"))
                .defaultUser(rs.getBoolean("default_user"))
                .lastLoginTime(rs.getTimestamp("last_login_time"));

        users.guid(rs.getString("guid"))
                .createTime(rs.getTimestamp("create_time"));
        return users;
    }
}
