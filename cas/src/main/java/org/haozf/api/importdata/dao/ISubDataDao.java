package org.haozf.api.importdata.dao;

import java.util.List;

import org.haozf.identity.user.model.User;

/**
 * 子系统数据接口
 * @author haozhengfeng
 *
 */
public interface ISubDataDao {
	/**
	 * 获取子系统数据
	 * @return
	 */
	public List<User> getData();
	
	/**
	 * 通过子系统id获取user
	 * @param source
	 * @param sourceID
	 * @return
	 */
	public List<User> getData(String[] sourceID);
}
