package org.haozf.api.person.service.dto;

import org.haozf.identity.member.model.Member;
import org.haozf.identity.person.model.Person;
import org.haozf.identity.user.model.User;

/**
 * 个人会员信息dto 2016年7月4日
 * 
 * @author haozhengfeng
 */
public class PersonInfoDto {
	
	private String userID;
	private String custID;
	private String custType;
	private String stateCode;
	private String userClass;

	private String phone;
	private String email;
	private String cellPhone;
	private String qq;
	private String address;

	private String username;
	private String loginMobile;
	private String loginEmail;
	private String userState;
	private String realname;
	
	public PersonInfoDto(){
		
	}

	public PersonInfoDto(User user, Member member, Person person) {
		if (member != null) {
			this.custID = member.getCustID();
			this.custType = member.getCustType();
			this.stateCode = member.getStateCode();
			this.userClass = member.getUserClass();
		}

		if (person != null) {
			this.phone = person.getPhone();
			this.email = person.getEmail();
			this.cellPhone = person.getCellPhone();
			this.qq = person.getQq();
			this.address = person.getAddress();
		}

		if (user != null) {
			this.userID = user.getUserID()+"";
			this.username = user.getUsername();
			this.loginMobile = user.getLoginMobile();
			this.loginEmail = user.getLoginEmail();
			this.userState = user.getUserState();
			this.realname = user.getRealname();
		}

	}

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

}
