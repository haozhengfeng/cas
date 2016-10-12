package org.haozf.api.person.service;

import org.haozf.api.person.service.dto.PersonInfoDto;
import org.haozf.identity.person.model.Person;
import org.haozf.identity.user.model.User;

public interface IPersonService {
	//通过custID获取个人信息
	public PersonInfoDto personInfo(User user);
	//修改个人信息
	public Person updatePersonInfo(PersonInfoDto person);
	//添加个人信息
	public Person addPerson(Person person);
	
}
