package org.haozf.api.importdata.service;

/**
 * 子系统数据库向cas数据库导入数据
 * 
 * @author haozhengfeng
 * 
 */

public abstract class AbstractImportDataService implements ImportDataService {

	public String source;

	/**
	 * 导入数据
	 */
	public abstract void importData();

	public String support() {
		return source;
	}
}
