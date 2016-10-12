package org.haozf.api.user.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.haozf.api.user.service.IUserService;
import org.haozf.cas.encoder.PasswordHandler;
import org.haozf.common.util.DateUtils;
import org.haozf.identity.member.dao.IMemberDao;
import org.haozf.identity.member.model.Member;
import org.haozf.identity.user.dao.ICheckUserDao;
import org.haozf.identity.user.dao.IUserDao;
import org.haozf.identity.user.model.User;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService implements IUserService {

	@Resource
	public IUserDao userDao;
	
	@Resource
	public IMemberDao memberDao;

	@Resource
	public ICheckUserDao checkUserDao;

	public PasswordHandler passwordHandler = new PasswordHandler();

	/**
	 * @see IUserService#addUser(User)
	 */
	@Override
	public User addUser(User user) {
		
		//添加相应的会员信息
		Member member = new Member();
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
		
		//更新用户信息的会员id
		user.setCustID(member.getCustID());
		
		//添加用户信息
		userDao.addUser(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		userDao.updateUser(user);
		return null;
	}

	@Override
	public boolean updatePassWord(User user) {
		User u = userDao.loadUserByID(user);
		String password = user.getPassword();
//		password = passwordHandler.encode(password, ApiContext.clientDetails.get().getClientId());
//		if (password == null) {
//			throw new UserException("密码加密失败！");
//		}
		u.setPassword(password);
		userDao.updateUser(u);
		return true;
	}
	
	@Override
	public boolean updatePassWordBySuserID(User user) {
		userDao.updatePasswordBySuserID(user);
		return true;
	}
	
	@Override
	public boolean updatePassWordByMobile(User user) {
		userDao.updatePasswordByMobile(user);
		return true;
	}
	
	@Override
	public boolean updatePassWordByEmail(User user) {
		userDao.updatePasswordByEmail(user);
		return true;
	}

	@Override
	public boolean updMobile(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updEmail(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User deleteUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByParams(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hasUserByName(String username) {
		return checkUserDao.hasUserByName(username);
	}

	@Override
	public int hasUserByPhone(String phone) {
		return checkUserDao.hasUserByPhone(phone);
	}

	@Override
	public int hasUserByEmail(String email) {
		return checkUserDao.hasUserByEmail(email);
	}

	@Override
	public int hasUserByNameOrPhoneOrEmail(String param) {
		return checkUserDao.hasUserByNameOrPhoneOrEmail(param);
	}

	@Override
	public boolean updateLastLoinTime(User user) {
		User u = userDao.loadUserByID(user);
		String lastLoginTime = DateUtils.toDateText(DateUtils.now());
		u.setLastLoginTime(lastLoginTime);
		userDao.updateUser(u);
		return true;
	}

}
