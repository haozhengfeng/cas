package org.haozf.api.importdata.dao;

import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public interface ICasDataDao {
	/**
	 * 批量导入数据
	 * @param batchArgs
	 */
	public void importDao(SqlParameterSource[] batchArgs);
}
