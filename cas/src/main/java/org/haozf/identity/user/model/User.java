package org.haozf.identity.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ti_user")
public class User {

	/**
	 * 用户标识
	 */
	@Column(name = "user_id")
	private int userID;
	
	/**
	 * 源用户标识
	 */
	@Column(name = "source_user_id")
	private String suserID;
	
	/**
	 * 会员标识
	 */
	@Column(name = "cust_id")
	private String custID;

	/**
	 * 用户名
	 */
	@Column(name = "user_name")
	private String username;

	/**
	 * 登录手机
	 */
	@Column(name = "login_mobile")
	private String loginMobile;

	/**
	 * 登录邮箱
	 */
	@Column(name = "login_email")
	private String loginEmail;

	/**
	 * 密码
	 */
	@Column(name = "password")
	private String password;

	/**
	 * 用户状态
	 */
	@Column(name = "user_state")
	private String userState;

	/**
	 * 找回密码问题
	 */
	@Column(name = "password_ques")
	private String passwordQues;

	/**
	 * 找回密码答案
	 */
	@Column(name = "password_answ")
	private String passwordAnsw;

	/**
	 * 真实姓名
	 */
	@Column(name = "real_name")
	private String realname;
	
	/**
	 * 操作时间
	 */
	@Column(name = "in_date")
	private String inDate;
	/**
	 * 注册时间
	 */
	@Column(name = "add_time")
	private String addTime;
	/**
	 * 密保问题
	 */
	@Column(name = "pwd_question")
	private String pwdQuestion;
	/**
	 * 密保答案
	 */
	@Column(name = "pwd_answer")
	private String pwdAnswer;
	/**
	 * 注册ip
	 */
	@Column(name = "reg_ip")
	private String regIP;
	/**
	 * 激活状态
	 */
	@Column(name = "active_state")
	private String activeState;
	/**
	 * 是否激活手机
	 */
	@Column(name = "is_mobile_actived")
	private String isMobileActived;
	/**
	 * 最后一次登录时间
	 */
	@Column(name = "last_login_time")
	private String lastLoginTime;
	/**
	 * 最后一次登录ip
	 */
	@Column(name = "last_login_ip")
	private String lastLoginIP;
	/**
	 * 登录次数
	 */
	@Column(name = "login_count")
	private String loginCount;
	/**
	 * 是否关闭
	 */
	@Column(name = "is_close")
	private String isClose;
	/**
	 * 是否激活邮箱
	 */
	@Column(name = "is_mail_actived")
	private String isMailActived;
	/**
	 * 是否绑定微信账号
	 */
	@Column(name = "is_wechat_bind")
	private String isWechatBind;
	/**
	 * 与微信账号对应唯一标识
	 */
	@Column(name = "wechat_openid")
	private String wechatOpenID;
	
	/**
	 * 注册源
	 */
	@Column(name="regist_source")
	private String registSource;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getSuserID() {
		return suserID;
	}

	public void setSuserID(String suserID) {
		this.suserID = suserID;
	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLoginMobile() {
		return loginMobile;
	}

	public void setLoginMobile(String loginMobile) {
		this.loginMobile = loginMobile;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getPasswordQues() {
		return passwordQues;
	}

	public void setPasswordQues(String passwordQues) {
		this.passwordQues = passwordQues;
	}

	public String getPasswordAnsw() {
		return passwordAnsw;
	}

	public void setPasswordAnsw(String passwordAnsw) {
		this.passwordAnsw = passwordAnsw;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getInDate() {
		return inDate;
	}

	public void setInDate(String inDate) {
		this.inDate = inDate;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getPwdQuestion() {
		return pwdQuestion;
	}

	public void setPwdQuestion(String pwdQuestion) {
		this.pwdQuestion = pwdQuestion;
	}

	public String getPwdAnswer() {
		return pwdAnswer;
	}

	public void setPwdAnswer(String pwdAnswer) {
		this.pwdAnswer = pwdAnswer;
	}

	public String getRegIP() {
		return regIP;
	}

	public void setRegIP(String regIP) {
		this.regIP = regIP;
	}

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	public String getIsMobileActived() {
		return isMobileActived;
	}

	public void setIsMobileActived(String isMobileActived) {
		this.isMobileActived = isMobileActived;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIP() {
		return lastLoginIP;
	}

	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}

	public String getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	public String getIsClose() {
		return isClose;
	}

	public void setIsClose(String isClose) {
		this.isClose = isClose;
	}

	public String getIsMailActived() {
		return isMailActived;
	}

	public void setIsMailActived(String isMailActived) {
		this.isMailActived = isMailActived;
	}

	public String getIsWechatBind() {
		return isWechatBind;
	}

	public void setIsWechatBind(String isWechatBind) {
		this.isWechatBind = isWechatBind;
	}

	public String getWechatOpenID() {
		return wechatOpenID;
	}

	public void setWechatOpenID(String wechatOpenID) {
		this.wechatOpenID = wechatOpenID;
	}

	public String getRegistSource() {
		return registSource;
	}

	public void setRegistSource(String registSource) {
		this.registSource = registSource;
	}


}
