package org.haozf.user.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.jdbc.core.RowMapper;

@Entity
@Table(name="t_user")
public class User implements RowMapper<User>, Serializable{
	private int id;
	private String username;
	private String password;
	private String nickname;
	private int status;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", nickname=" + nickname + ", status=" + status
				+ "]";
	}
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		 User user = new User();
		 user.setId(rs.getInt(id));
		 user.setNickname(rs.getString(nickname));
		 user.setPassword(rs.getString(password));
		 user.setStatus(rs.getInt(status));
		 user.setUsername(rs.getString(username));
	     return user;
	}
}
