package org.haozf.identity.user.dao.impl;

import org.haozf.common.base.AbstractJdbcDao;
import org.haozf.identity.user.dao.IUserDao;
import org.haozf.identity.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends AbstractJdbcDao<User> implements IUserDao{

	@Override
	public User addUser(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into ti_user( ");
		sb.append(" 	source_user_id,cust_id,user_name,login_mobile,login_email,password,user_state, ");
		sb.append(" 	password_ques,password_answ,real_name, ");
		sb.append(" 	in_date,add_time,pwd_question,pwd_answer,reg_ip, ");
		sb.append(" 	active_state,is_mobile_actived,last_login_time, ");
		sb.append(" 	last_login_ip,login_count,is_close,is_mail_actived, ");
		sb.append(" 	is_wechat_bind,wechat_openid,regist_source) ");
		sb.append(" values ( ");
		sb.append(" 	:suserID,:custID,:username,:loginMobile,:loginEmail,:password,:userState, ");
		sb.append(" 	:passwordQues,:passwordAnsw,:realname,");
		sb.append(" 	:inDate,:addTime,:pwdQuestion,:pwdAnswer,:regIP,");
		sb.append(" 	:activeState,:isMobileActived,:lastLoginTime, ");
		sb.append(" 	:lastLoginIP,:loginCount,:isClose,:isMailActived, ");
		sb.append(" 	:isWechatBind,:wechatOpenID,:registSource ");
		sb.append(" )  ");
		setSql(sb.toString());
		int id = add(getSql(), user);
		user.setUserID(id);
		return user;
	}

	@Override
	public User updateUser(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ti_user set source_user_id=:suserID,cust_id=:custID, ");
		sb.append(" user_name=:username,login_mobile=:loginMobile,login_email=:loginEmail, ");
		sb.append(" password=:password,user_state=:userState,password_ques=:passwordQues, ");
		sb.append(" password_answ=:passwordAnsw,real_name=:realname,in_date=:inDate,add_time=:addTime, ");
		sb.append(" pwd_question=:pwdQuestion,pwd_answer=:pwdAnswer,reg_ip=:regIP, ");
		sb.append(" active_state=:activeState,is_mobile_actived=:isMobileActived,last_login_time=:lastLoginTime, ");
		sb.append(" last_login_ip=:lastLoginIP,login_count=:loginCount,is_close=:isClose,is_mail_actived=:isMailActived, ");
		sb.append(" is_wechat_bind=:isWechatBind,wechat_openid=:wechatOpenID,regist_source=:registSource ");
		sb.append(" where user_id=:userID ");
		
		setSql(sb.toString());
		update(getSql(), user);
		return user;
	}
	
	@Override
	public User updatePasswordBySuserID(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ti_user set password=:password where source_user_id=:suserID ");
		setSql(sb.toString());
		update(getSql(), user);
		return user;
	}
	
	@Override
	public User updatePasswordByMobile(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ti_user set password=:password where login_mobile=:loginMobile ");
		setSql(sb.toString());
		update(getSql(), user);
		return user;
	}
	
	@Override
	public User updatePasswordByEmail(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" update ti_user set password=:password where login_email=:loginEmail ");
		setSql(sb.toString());
		update(getSql(), user);
		return user;
	}

	@Override
	public User deleteUser(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" delete from ti_user where user_id = :userID");
		setSql(sb.toString());
		update(getSql(), user);
		return user;
	}

	@Override
	public User loadUserByID(User user) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select user_id as userID,source_user_id as suserID,cust_id as custID, ");
		sb.append(" user_name as username,login_mobile as loginMobile,login_email as loginEmail, ");
		sb.append(" password as password,user_state as userState,password_ques as passwordQues, ");
		sb.append(" password_answ as passwordAnsw,real_name as realname,in_date as inDate,add_time as addTime, ");
		sb.append(" pwd_question as pwdQuestion,pwd_answer as pwdAnswer,reg_ip as regIP, ");
		sb.append(" active_state as activeState,is_mobile_actived as isMobileActived,last_login_time as lastLoginTime, ");
		sb.append(" last_login_ip as lastLoginIP,login_count as loginCount,is_close as isClose,is_mail_actived as isMailActived, ");
		sb.append(" is_wechat_bind as isWechatBind,wechat_openid as wechatOpenID,regist_source as registSource ");
		sb.append(" from ti_user where user_id=:userID ");
		setSql(sb.toString());
		user = load(getSql(), user);
		return user;
	}

	@Override
	public User loadUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User loadUserByUserID(String userID) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select user_id as userID,source_user_id as suserID,cust_id as custID, ");
		sb.append(" user_name as username,login_mobile as loginMobile,login_email as loginEmail, ");
		sb.append(" password as password,user_state as userState,password_ques as passwordQues, ");
		sb.append(" password_answ as passwordAnsw,real_name as realname,in_date as inDate,add_time as addTime, ");
		sb.append(" pwd_question as pwdQuestion,pwd_answer as pwdAnswer,reg_ip as regIP, ");
		sb.append(" active_state as activeState,is_mobile_actived as isMobileActived,last_login_time as lastLoginTime, ");
		sb.append(" last_login_ip as lastLoginIP,login_count as loginCount,is_close as isClose,is_mail_actived as isMailActived, ");
		sb.append(" is_wechat_bind as isWechatBind,wechat_openid as wechatOpenID,regist_source as registSource ");
		sb.append(" from ti_user where user_id=:userID ");
		setSql(sb.toString());
		User user = loadByID(sb.toString(), "userID", userID);
		return user;
	}

	@Override
	public User loadUserBycustID(String custID) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select user_id as userID,source_user_id as suserID,cust_id as custID, ");
		sb.append(" user_name as username,login_mobile as loginMobile,login_email as loginEmail, ");
		sb.append(" password as password,user_state as userState,password_ques as passwordQues, ");
		sb.append(" password_answ as passwordAnsw,real_name as realname,in_date as inDate,add_time as addTime, ");
		sb.append(" pwd_question as pwdQuestion,pwd_answer as pwdAnswer,reg_ip as regIP, ");
		sb.append(" active_state as activeState,is_mobile_actived as isMobileActived,last_login_time as lastLoginTime, ");
		sb.append(" last_login_ip as lastLoginIP,login_count as loginCount,is_close as isClose,is_mail_actived as isMailActived, ");
		sb.append(" is_wechat_bind as isWechatBind,wechat_openid as wechatOpenID,regist_source as registSource ");
		sb.append(" from ti_user where cust_id=:custID ");
		setSql(sb.toString());
		User user = loadByID(sb.toString(), "custID", custID);
		return user;
	}
}
