package org.haozf.common.base;

/**
 * DAO基础接口，提供基本增删改查功能
 * @author haozhengfeng
 *
 * @param <T>
 */
public interface IBaseDao<T> {
	/**
	 * 添加对象
	 * 
	 * @param t
	 * @return
	 */
	public int add(String sql,T t);

	/**
	 * 更新对象
	 * 
	 * @param t
	 *            更新的对象
	 * @return 
	 */
	public int update(String sql,T t);

	/**
	 * 根据id删除对象
	 * 
	 * @param id
	 *            删除的对象的id
	 */
	public void delete(String sql,T t);

	/**
	 * 根据id加载对象
	 * 
	 * @param id
	 *            加载对象的id
	 * @return 返回加载对象
	 */
	public T load(String sql,T t);
}
