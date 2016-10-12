package org.haozf.identity.person.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ti_personal")
public class Person {
	
	/**
	 * 个人标识
	 */
	@Column(name="person_id")
	private String personID;
	
	/**
	 * 会员标识
	 */
	@Column(name="cust_id")
	private String custID;
	
	/**
	 * 电话号码
	 */
	@Column(name="phone")
	private String phone;
	
	/**
	 * 邮箱
	 */
	@Column(name="email")
	private String email;
	
	/**
	 * 手机
	 */
	@Column(name="cellphone")
	private String cellPhone;
	
	/**
	 * 传真
	 */
	@Column(name="fax")
	private String fax;
	
	/**
	 * qq
	 */
	@Column(name="qq")
	private String qq;
	
	/**
	 * msn
	 */
	@Column(name="msn")
	private String msn;
	
	/**
	 * 邮编
	 */
	@Column(name="post_code")
	private String postCode;
	
	/**
	 * 地址
	 */
	@Column(name="address")
	private String address;
	
	/**
	 * 性别
	 */
	@Column(name="sex")
	private String sex;
	
	/**
	 * 生日
	 */
	@Column(name="birth")
	private String birth;
	
	/**
	 * 所在地区
	 */
	@Column(name="area_attr")
	private String areaAttr;
	
	/**
	 * 婚姻状况
	 */
	@Column(name="is_marry")
	private String isMarry;
	
	/**
	 * 血型
	 */
	@Column(name="blood_type")
	private String bloodType;
	
	/**
	 * 职业
	 */
	@Column(name="career")
	private String career;
	
	/**
	 * 岗位
	 */
	@Column(name="job")
	private String job;
	
	/**
	 * 个人爱好
	 */
	@Column(name="hobby")
	private String hobby;
	
	/**
	 * 收入状况
	 */
	@Column(name="income")
	private String inCome;
	
	/**
	 * 省
	 */
	@Column(name="province")
	private String province;
	
	/**
	 * 市
	 */
	@Column(name="city")
	private String city;
	
	/**
	 * 县
	 */
	@Column(name="country")
	private String country;
	
	/**
	 * 头像
	 */
	@Column(name="head_image")
	private String headImage;
	
	/**
	 * 身份证图片
	 */
	@Column(name="identity_card_picture")
	private String identityCardPicture;
	
	/**
	 * 身份证号
	 */
	@Column(name="identity_card")
	private String identityCard;

	
	public String getCustID() {
		return custID;
	}

	public void setCustID(String custID) {
		this.custID = custID;
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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getMsn() {
		return msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getAreaAttr() {
		return areaAttr;
	}

	public void setAreaAttr(String areaAttr) {
		this.areaAttr = areaAttr;
	}

	public String getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getInCome() {
		return inCome;
	}

	public void setInCome(String inCome) {
		this.inCome = inCome;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getIdentityCardPicture() {
		return identityCardPicture;
	}

	public void setIdentityCardPicture(String identityCardPicture) {
		this.identityCardPicture = identityCardPicture;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
}
