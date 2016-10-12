package org.haozf.api.user.service;

import org.haozf.identity.user.model.User;

public interface IUserService {

	/**
	 * 添加用户
	 * 
	 * 添加用户时，需要更新会员表
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
	 * 修改密码
	 * @param user
	 * @return
	 */
	public boolean updatePassWord(User user);
	
	/**
	 * 修改手机
	 * @param user
	 * @return
	 */
	public boolean	updMobile(User user);
	
	/**
	 * 修改邮箱
	 * @param user
	 * @return
	 */
	public boolean updEmail(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public User deleteUser(int id);
	
	/**
	 * 通过id获取用户
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * 通过用户名获取用户
	 * @param username
	 * @return
	 */
	public User getUserByName(String username);
	
	/**
	 * 通过其他参数获取用户
	 * @param user
	 * @return
	 */
	public User getUserByParams(User user);
	
	/**
	 * 是否存在用户名
	 * @param username
	 * @return
	 */
	public int hasUserByName(String username);
	
	/**
	 * 是否存在手机号
	 * @param phone
	 * @return
	 */
	public int hasUserByPhone(String phone);

	/**
	 * 是否存在邮箱
	 * @param email
	 * @return
	 */
	public int hasUserByEmail(String email);
	
	/**
	 * 是否存在用户名、手机号、或者邮箱
	 * @param param
	 * @return
	 */
	public int hasUserByNameOrPhoneOrEmail(String param);

	/**
	 * 通过手机号修改密码
	 * @param user
	 * @return
	 */
	boolean updatePassWordByMobile(User user);

	/**
	 * 通过邮箱修改密码
	 * @param user
	 * @return
	 */
	boolean updatePassWordByEmail(User user);

	/**
	 * 通过源用户id修改密码
	 * @param user
	 * @return
	 */
	boolean updatePassWordBySuserID(User user);
	
	/**
	 * 更新最后一次登录时间
	 * @param user
	 * @return
	 */
	public boolean updateLastLoinTime(User user);
	
}
