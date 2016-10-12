package org.haozf.api.person.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.haozf.api.person.service.IPersonService;
import org.haozf.api.person.service.dto.PersonInfoDto;
import org.haozf.identity.member.dao.IMemberDao;
import org.haozf.identity.member.model.Member;
import org.haozf.identity.person.dao.IPersonDao;
import org.haozf.identity.person.model.Person;
import org.haozf.identity.user.dao.IUserDao;
import org.haozf.identity.user.model.User;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonService implements IPersonService {

	@Resource
	private IPersonDao personDao;
	
	@Resource
	private IMemberDao memberDao;
	
	@Resource
	private IUserDao userDao;
	
	/**
	 * @see org.haozf.api.person.service.IPersonService#personInfo(Person)
	 */
	@Override
	public PersonInfoDto personInfo(User user) {
		/*
		 * 1、获取通过用户id获取用户信息
		 */
		user = userDao.loadUserByID(user);
		
		/*
		 * 2、根据用户的cust_id获取个人信息
		 */
		Person person = new Person();
		person.setCustID(user.getCustID());
		person = personDao.loadPersonByID(person);
		
		/*
		 * 3、根据用户cust_id获取会员信息
		 */
		Member member = new Member();
		member.setCustID(user.getCustID());
		member = memberDao.loadMemeberByID(member);
		
		/*
		 * 4、查询个人会员信息时  如果会员信息个人信息为空  添加
		 */
		if(member == null){
			//添加相应的会员信息
			member = new Member();
			if(!StringUtils.isEmpty(user.getLoginEmail())){
				member.setCustName(user.getLoginEmail());
			}
			if(!StringUtils.isEmpty(user.getLoginMobile())){
				member.setCustName(user.getLoginMobile());
			}
			if(!StringUtils.isEmpty(user.getUsername())){
				member.setCustName(user.getUsername());
			}
			//设置初始会员
			member = memberDao.addMember(member);
			user.setCustID(member.getCustID());
			userDao.updateUser(user);
		}
		
		return new PersonInfoDto(user, member, person);
	}

	/**
	 * @see org.haozf.api.person.service.IPersonService#addPerson(Person)
	 */
	@Override
	public Person addPerson(Person person) {
		personDao.addPerson(person);
		return person;
	}

	/**
	 * @see org.haozf.api.person.service.IPersonService#updatePerson(Person)
	 */
	@Override
	public Person updatePersonInfo(PersonInfoDto personInfo) {
		
		/*
		 * 更新用户信息
		 */
		User user = userDao.loadUserByUserID(personInfo.getUserID());
		user.setRealname(personInfo.getRealname());
		user.setLoginEmail(personInfo.getLoginEmail());
		user.setLoginMobile(personInfo.getLoginMobile());
		userDao.updateUser(user);
		
		/*
		 * 1、更新个人信息
		 *    如果数据库没有个人信息 创建
		 *    如果有 更新
		 */
		Person p = personDao.loadPersonByCustID(user.getCustID());
		if(p == null){
			p = new Person();
			p.setCustID(user.getCustID());
			personDao.addPerson(p);
		}
		
		p.setPhone(personInfo.getPhone());
		p.setAddress(personInfo.getAddress());
		p.setQq(personInfo.getQq());
		personDao.updatePerson(p);
		
		return p;
	}

}
