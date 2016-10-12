package org.haozf.user.dao;

import java.util.List;

import org.haozf.basic.dao.IBaseDao;
import org.haozf.user.model.User;


public interface IUserDao extends IBaseDao<User> {
	public List<User> listUser();
	
	public User loadByUsername(String username);
	
}
