package org.haozf.identity.member.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 会员实体 一个会员可以对应多个用户账号 2016年7月4日
 * 
 * @author haozhengfeng
 */

@Entity
@Table(name = "ti_member")
public class Member {

	public Member() {
		//设置初始会员默认值
		this.custType = "1";
		this.stateCode = "0";
		this.userClass = "0";
	}

	/**
	 * 会员标识
	 */
	@Column(name = "cust_id")
	private String custID;

	/**
	 * 会员名称
	 */
	@Column(name = "cust_name")
	private String custName;

	/**
	 * 会员类型
	 */
	@Column(name = "cust_type")
	private String custType;

	/**
	 * 会员状态
	 */
	@Column(name = "state_code")
	private String stateCode;

	/**
	 * 会员级别
	 */
	@Column(name = "user_class")
	private String userClass;

	/**
	 * 会员停用时间
	 */
	@Column(name = "cust_disable_time")
	private String custDisableTime;

	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getCustDisableTime() {
		return custDisableTime;
	}

	public void setCustDisableTime(String custDisableTime) {
		this.custDisableTime = custDisableTime;
	}

}
