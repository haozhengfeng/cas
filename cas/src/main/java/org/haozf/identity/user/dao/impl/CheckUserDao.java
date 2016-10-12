package org.haozf.identity.user.dao.impl;

import org.haozf.common.base.AbstractJdbcDao;
import org.haozf.identity.user.dao.ICheckUserDao;
import org.haozf.identity.user.model.User;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("checkUserDao")
public class CheckUserDao extends AbstractJdbcDao<User> implements ICheckUserDao {

	@Override
	public int hasUserByName(String username) {
		String sql = " select count(user_id) from ti_user where user_name = :user_name ";
		SqlParameterSource paramSource = new MapSqlParameterSource()
										 .addValue("user_name", username);
		return count(sql, paramSource);
	}

	@Override
	public int hasUserByPhone(String phone) {
		String sql = " select count(user_id) from ti_user where login_mobile = :login_mobile ";
		SqlParameterSource paramSource = new MapSqlParameterSource()
										 .addValue("login_mobile", phone);
		return count(sql, paramSource);
	}

	@Override
	public int hasUserByEmail(String email) {
		String sql = " select count(user_id) from ti_user where login_email = :login_email ";
		SqlParameterSource paramSource = new MapSqlParameterSource()
		                                 .addValue("username", email);
		return count(sql, paramSource);
	}

	@Override
	public int hasUserByNameOrPhoneOrEmail(String param) {
		String sql = " select count(user_id) from ti_user where user_name = :user_name or login_mobile = :login_mobile or login_email = :login_email ";
		SqlParameterSource paramSource = new MapSqlParameterSource()
										 .addValue("user_name", param)
										 .addValue("login_mobile", param)
										 .addValue("login_email", param);
		return count(sql, paramSource);
	}

	@Override
	public int hasUserBySuserID(String suserID) {
		String sql = " select count(user_id) from ti_user where source_user_id = :source_user_id ";
		SqlParameterSource paramSource = new MapSqlParameterSource()
										 .addValue("source_user_id", suserID);
		return count(sql, paramSource);
	}

}
