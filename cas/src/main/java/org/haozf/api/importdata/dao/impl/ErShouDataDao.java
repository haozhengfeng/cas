package org.haozf.api.importdata.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.haozf.api.importdata.dao.ISubDataDao;
import org.haozf.identity.user.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

@Repository("erShouDataDao")
public class ErShouDataDao implements ISubDataDao {

	public static final String SOURCE = "ershou";

	/**
	 * 注入子系统 jdbc
	 */
	@Resource
	private NamedParameterJdbcTemplate ershouJdbcTemplate;

	@Override
	public List<User> getData() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" UserID as suserID, ");
		sb.append(" userloginname as username, ");
		sb.append(" UserLoginMobile as loginMobile, ");
		sb.append(" UserLoginEmail as loginEmail, ");
		sb.append(" userpassword as password, ");
		sb.append(" UserRegIP as regIP, ");
		sb.append(" UserRegTime as addTime, ");
		sb.append(" UserLastLoginTime as lastLoginTime, ");
		sb.append(" UserLastLoginIP as lastLoginIP, ");
		sb.append(" UserLoginCount as loginCount, ");
		sb.append(" UserActive as activeState, ");
		sb.append(" UserState as userState, ");
		sb.append(" WeChatBind as isWechatBind, ");
		sb.append(" WeChatOpenID as wechatOpenID, ");
		sb.append(" '" + SOURCE + "' as registSource ");

		sb.append(" from Passport_User ");

		String sql = sb.toString();

		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		List<User> users = ershouJdbcTemplate.query(sql, rowMapper);
		return users;
	}

	@Override
	public List getData(String[] sourceIDs) {
		StringBuilder sb = new StringBuilder();
		sb.append(" select ");
		sb.append(" UserID as suserID, ");
		sb.append(" userloginname as username, ");
		sb.append(" UserLoginMobile as loginMobile, ");
		sb.append(" UserLoginEmail as loginEmail, ");
		sb.append(" userpassword as password, ");
		sb.append(" UserRegIP as regIP, ");
		sb.append(" UserRegTime as addTime, ");
		sb.append(" UserLastLoginTime as lastLoginTime, ");
		sb.append(" UserLastLoginIP as lastLoginIP, ");
		sb.append(" UserLoginCount as loginCount, ");
		sb.append(" UserActive as activeState, ");
		sb.append(" UserState as userState, ");
		sb.append(" WeChatBind as isWechatBind, ");
		sb.append(" WeChatOpenID as wechatOpenID, ");
		sb.append(" '" + SOURCE + "' as registSource ");
		sb.append(" from Passport_User ");
		sb.append(" where  ");

		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		
		List list = new ArrayList();
		for(String sourceID:sourceIDs){
			list.add(" UserID = :UserID ");
			paramSource.addValue("UserID", sourceID);
		}
		
		String param = StringUtils.join(list, " or ");
		sb.append(param);

		RowMapper<User> rowMapper = ParameterizedBeanPropertyRowMapper.newInstance(User.class);
		List<User> users = ershouJdbcTemplate.query(sb.toString(), paramSource, rowMapper);
		return users;
	}
}
