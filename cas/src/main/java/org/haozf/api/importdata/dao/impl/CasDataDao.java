package org.haozf.api.importdata.dao.impl;

import javax.annotation.Resource;

import org.haozf.api.importdata.dao.ICasDataDao;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository("casDataDao")
public class CasDataDao implements ICasDataDao {

	@Resource
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public void importDao(SqlParameterSource[] batchArgs) {
		
		String sql = " insert into ti_user "
				   + " (source_user_id,user_name,login_mobile,login_email,password,reg_ip, "
				   + " add_time,last_login_time,last_login_ip,login_count,active_state, "
				   + " user_state,is_wechat_bind,wechat_openid,regist_source) "
				   + " values "
				   + " (:suserID,:username,:loginMobile,:loginEmail,:password,:regIP, "
				   + " :addTime,:lastLoginTime,:lastLoginIP,:loginCount,:activeState, "
				   + " :userState,:isWechatBind,:wechatOpenID,:registSource)";
		jdbcTemplate.batchUpdate(sql, batchArgs);
	}
}
