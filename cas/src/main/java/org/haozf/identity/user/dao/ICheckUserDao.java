package org.haozf.identity.user.dao;

public interface ICheckUserDao {

	public int hasUserBySuserID(String suserID);
	
	public int hasUserByName(String username);

	public int hasUserByPhone(String phone);

	public int hasUserByEmail(String email);
	
	public int hasUserByNameOrPhoneOrEmail(String param);

}
