package org.haozf.identity.person.dao;

import org.haozf.identity.person.model.Person;

/**
 * 个人信息dao
 * 2016年7月4日
 * @author haozhengfeng
 */
public interface IPersonDao {

	/**
	 * 加载个人会员信息
	 * @return
	 */
	public Person loadPersonByID(Person person);
	
	/**
	 * 通过custID加载个人会员信息
	 * @return
	 */
	public Person loadPersonByCustID(String custID);

	/**
	 * 修改个人会员信息
	 * @param person
	 * @return
	 */
	public Person updatePerson(Person person);
	
	/**
	 * 添加个人会员信息
	 * @param person
	 * @return
	 */
	public Person addPerson(Person person);
}
