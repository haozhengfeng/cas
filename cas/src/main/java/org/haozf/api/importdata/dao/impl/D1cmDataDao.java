package org.haozf.api.importdata.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.haozf.api.importdata.dao.ISubDataDao;
import org.haozf.identity.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("d1cmDataDao")
public class D1cmDataDao implements ISubDataDao{
	
	public static final String SOURCE = "d1cm";

	/**
	 * 注入子系统 jdbc
	 */
	@Resource
	private NamedParameterJdbcTemplate d1cmJdbcTemplate;

	@Override
	public List<User> getData() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" m_ID as suserID, ");
		sb.append(" m_UserName as username, ");
		sb.append(" m_Mobile as loginMobile, ");
		sb.append(" m_Email as loginEmail, ");
		sb.append(" m_PassWord as password, ");
		sb.append(" reg_ip as regIP, ");
		sb.append(" reg_time as addTime, ");
		sb.append(" last_login_time as lastLoginTime, ");
		sb.append(" last_login_ip as lastLoginIP, ");
		sb.append(" login_count as loginCount, ");
		sb.append(" IsMobileActived as activeState, ");
		sb.append(" IsState as userState, ");
		sb.append(" 0 as isWechatBind, ");
		sb.append(" '' as wechatOpenID, ");
		sb.append(" '" + SOURCE + "' as registSource ");

		sb.append(" from IndividualMembers ");

		String sql = sb.toString();

		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		List<User> users = d1cmJdbcTemplate.query(sql, rowMapper);
		return users;
	}

	@Override
	public List<User> getData(String[] sourceID) {
		// TODO Auto-generated method stub
		return null;
	}
}
