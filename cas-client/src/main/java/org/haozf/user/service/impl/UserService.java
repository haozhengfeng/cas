package org.haozf.user.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.haozf.user.ShiroKit;
import org.haozf.user.dao.IUserDao;
import org.haozf.user.model.User;
import org.haozf.user.service.IUserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service("userService")
public class UserService implements IUserService {
	@Inject
	private IUserDao userDao;

	public User add(User user) {
		if (ShiroKit.isEmpty(user.getUsername()) || ShiroKit.isEmpty(user.getPassword())) {
			throw new RuntimeException("用户名或者密码不能为空！");
		}
		user.setPassword(ShiroKit.md5(user.getPassword(), user.getUsername()));
		userDao.add(user);
		return user;
	}
	
	public void delete(int id) {
		userDao.delete(id);
	}


	public User update(User user) {
		userDao.update(user);
		return user;
	}

	@Cacheable(value="usersCache")
	public User load(int id) {
		return userDao.load(id);
	}

	public User loadByUsername(String username) {
		return userDao.loadByUsername(username);
	}

	public User login(String username, String password) {
		User u = userDao.loadByUsername(username);
		if (u == null)
			throw new UnknownAccountException("用户名或者密码出错");
		if (!u.getPassword().equals(ShiroKit.md5(password, username)))
			throw new IncorrectCredentialsException("用户名或者密码出错");
		return u;
	}

	public List<User> list() {
		return userDao.listUser();
	}


	public User add(User user, List<Integer> rids) {
		this.add(user);
		return user;
	}

}
