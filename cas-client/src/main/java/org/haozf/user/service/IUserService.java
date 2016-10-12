package org.haozf.user.service;

import java.util.List;

import org.haozf.user.model.User;



public interface IUserService {
	public User add(User user);
	
	public void delete(int id);
	
	public User update(User user);
	
	public User load(int id);
	
	public User loadByUsername(String username);
	
	public List<User> list();
}
