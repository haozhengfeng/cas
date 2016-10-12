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

@Service("d1cmImportDataService")
@Transactional
public class D1cmImportDataService extends AbstractImportDataService {

	@Resource
	private ICasDataDao casDataDao;
	
	@Resource
	private ISubDataDao d1cmDataDao;
	
	public D1cmImportDataService(){
		source = RegistSource.d1cm.toString();
	}

	/**
	 * 从二手机数据库向cas数据库导入数据
	 */
	@Override
	public void importData() {
		//查询二手机用户
		List<User> users = d1cmDataDao.getData();
		//生成批量数据源
		SqlParameterSource[] batchArgs = SqlParameterSourceUtils.createBatch(users.toArray());
		//批量导入数据
		casDataDao.importDao(batchArgs);
	}

}
