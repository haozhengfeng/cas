package org.haozf.common.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class AbstractJdbcDao<T> implements IBaseDao<T> {
	@Resource
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Resource
	private DataSource dataSource;
	
	private String sql;

	protected final NamedParameterJdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}

	protected final DataSource getDataSource() {
		return this.dataSource;
	}
	
	private Class<T> clz;

	public Class<T> getClz() {
		if (clz == null) {
			Type sType = getClass().getGenericSuperclass();
			Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
			clz = (Class<T>) (generics[0]);
		}
		return clz;
	}

	@Override
	public int add(String sql, T t) {
		//加上KeyHolder这个参数可以得到添加后主键的值 
		KeyHolder keyholder=new GeneratedKeyHolder(); 
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		getJdbcTemplate().update(sql, paramSource,keyholder);
		int k=keyholder.getKey().intValue(); 
		return k;
	}

	@Override
	public int update(String sql, T t) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		return getJdbcTemplate().update(sql, paramSource);
	}

	@Override
	public void delete(String sql, T t) {
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		getJdbcTemplate().update(sql, paramSource);
	}

	@Override
	public T load(String sql, T t) {
		RowMapper rowMapper = new BeanPropertyRowMapper(getClz());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		List<T> list = getJdbcTemplate().query(sql, paramSource, rowMapper);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public Object get(String sql, Object t) {
		RowMapper rowMapper = new BeanPropertyRowMapper(t.getClass());
		SqlParameterSource paramSource = new BeanPropertySqlParameterSource(t);
		List<Object> list = getJdbcTemplate().query(sql, paramSource, rowMapper);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public T loadByID(String sql,String paramName,String value){
		RowMapper rowMapper = new BeanPropertyRowMapper(getClz());
		SqlParameterSource paramSource = new MapSqlParameterSource().addValue(paramName, value);
		List<T> list = getJdbcTemplate().query(sql, paramSource, rowMapper);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public int count(String sql,SqlParameterSource paramSource){
		int i = getJdbcTemplate().queryForInt(sql, paramSource);
		return i;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}
