package org.haozf.user.dao.impl;

import java.util.List;

import org.haozf.basic.dao.HibernateBaseDao;
import org.haozf.user.dao.IUserDao;
import org.haozf.user.model.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends HibernateBaseDao<User> implements IUserDao {

	@Override
	public List<User> listUser() {
		return super.listByHQL("from User");
	}

	@Override
	public User loadByUsername(String username) {
		return (User) super.queryObjectByHQL("from User where username=?", username);
	}

}
