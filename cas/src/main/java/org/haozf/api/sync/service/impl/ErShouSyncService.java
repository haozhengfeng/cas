package org.haozf.api.sync.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.haozf.api.importdata.dao.ICasDataDao;
import org.haozf.api.importdata.dao.ISubDataDao;
import org.haozf.api.sync.service.ISyncService;
import org.haozf.identity.user.model.User;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

@Service("erShouSyncService")
public class ErShouSyncService implements ISyncService {

	@Resource
	private ISubDataDao erShouDataDao;

	@Resource
	private ICasDataDao casDataDao;
	
	@Override
	public void add(String[] sourceids) {
		// 通过 源 和 源id 查询出子系统的用户信息
		// 将子系统用户信息写入到cas数据库
		List<User> users = erShouDataDao.getData(sourceids);
		System.out.println(users);

		// 生成批量数据源
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(users.toArray());
		// 批量导入数据
		casDataDao.importDao(batchArgs);
	}

}
