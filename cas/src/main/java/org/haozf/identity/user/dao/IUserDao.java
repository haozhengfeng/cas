package org.haozf.identity.user.dao;

import org.haozf.identity.user.model.User;

public interface IUserDao{
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser(User user);
	
	/**
	 * 修改用户 
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public User updateUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public User deleteUser(User user);

	/**
	 * 通过id获取用户
	 * @param id
	 * @return
	 */
	public User loadUserByID(User user);
	
	/**
	 * 通过userID获取用户
	 * @param id
	 * @return
	 */
	public User loadUserByUserID(String userID);
	
	/**
	 * 通过custID获取用户
	 * @param id
	 * @return
	 */
	public User loadUserBycustID(String custID);
	
	/**
	 * 通过用户名获取用户
	 * @param username
	 * @return
	 */
	public User loadUserByName(String username);

	/**
	 * 通过手机号获取用户
	 * @param user
	 * @return
	 */
	public User updatePasswordByMobile(User user);

	/**
	 * 通过邮箱获取用户
	 * @param user
	 * @return
	 */
	public User updatePasswordByEmail(User user);

	/**
	 * 通过注册源id获取用户
	 * @param user
	 * @return
	 */
	public User updatePasswordBySuserID(User user);
	
}
