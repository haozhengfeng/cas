package org.haozf.api.importdata.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.haozf.api.importdata.dao.ICasDataDao;
import org.haozf.api.importdata.dao.ISubDataDao;
import org.haozf.api.importdata.service.AbstractImportDataService;
import org.haozf.common.enums.RegistSource;
import org.haozf.identity.user.model.User;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("maiPeiJianImportDataService")
@Transactional
public class MaiPeiJianImportDataService extends AbstractImportDataService {

	@Resource
	private ICasDataDao casDataDao;

	@Resource
	private ISubDataDao maiPeiJianDataDao;
	
	public MaiPeiJianImportDataService(){
		source = RegistSource.maipeijian.toString();
	}

	@Override
	public void importData() {
		
		long starTime = System.currentTimeMillis();
		// 查询二手机用户
		List<User> users = maiPeiJianDataDao.getData();
		// 生成批量数据源
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(users.toArray());
		// 批量导入数据
		casDataDao.importDao(batchArgs);

		long endTime = System.currentTimeMillis();
		long Time = (endTime - starTime)/1000;
		System.out.println(Time);
		System.out.println(users.size());
	}

}
