package org.haozf.api.importdata.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

/**
 * 导入数据工具类
 * @author haozhengfeng
 *
 */

@Service("importDataMain")
public class ImportDataMain {

	@Resource
	public DataSource dataSource;

	public Connection conn;

	public Statement st;

	public ResultSet rs;

	public void importData() {
		try {
			conn = dataSource.getConnection();
			st = conn.createStatement();

			String sql = " select * from t_user";
			rs = st.executeQuery(sql);
			
			if(rs!=null){
				while(rs.next()){
					String username = rs.getString("username");
					System.out.println(username);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
