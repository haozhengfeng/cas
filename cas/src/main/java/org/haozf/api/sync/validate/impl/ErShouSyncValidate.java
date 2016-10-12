package org.haozf.api.sync.validate.impl;

import javax.annotation.Resource;

import org.haozf.api.sync.validate.ISyncValidate;
import org.haozf.identity.user.dao.ICheckUserDao;
import org.springframework.stereotype.Service;

@Service("erShouSyncValidate")
public class ErShouSyncValidate implements ISyncValidate{
	
	@Resource
	private ICheckUserDao checkUserDao;

	@Override
	public int checkSourceidExist(String sourceid) {
		return checkUserDao.hasUserBySuserID(sourceid);
	}

	@Override
	public int checkSourceidExist(String[] sourceids) {
		// TODO Auto-generated method stub
		return 0;
	}

}
