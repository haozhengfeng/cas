package org.haozf.api.importdata.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.haozf.api.importdata.dao.ISubDataDao;
import org.haozf.identity.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("maiPeiJianDataDao")
public class MaiPeiJianDataDao implements ISubDataDao {

	public static final String SOURCE = "maipeijian";
	
	/**
	 * 注入子系统 jdbc
	 */
	@Resource
	private NamedParameterJdbcTemplate maiPeiJianJdbcTemplate;
	
	@Override
	public List<User> getData() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" user_id as suserID, ");
		sb.append(" user_name as username, ");
		sb.append(" '' as loginMobile, ");
		sb.append(" '' as loginEmail, ");
		sb.append(" passwd as password, ");
		sb.append(" '' as regIP, ");
		sb.append(" in_date as addTime, ");
		sb.append(" last_login as lastLoginTime, ");
		sb.append(" '' as lastLoginIP, ");
		sb.append(" 0 as loginCount, ");
		sb.append(" 0 as activeState, ");
		sb.append(" case when user_state='' then 0 else user_state end as userState, ");
		sb.append(" 0 as isWechatBind, ");
		sb.append(" '' as wechatOpenID, ");
		sb.append(" '" + SOURCE + "' as registSource ");

		sb.append(" from ti_user ");

		String sql = sb.toString();

		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		List<User> users = maiPeiJianJdbcTemplate.query(sql, rowMapper);
		return users;
	}

	@Override
	public List<User> getData(String[] sourceID) {
		// TODO Auto-generated method stub
		return null;
	}

}
