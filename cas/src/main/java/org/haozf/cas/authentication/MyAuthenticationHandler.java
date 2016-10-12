package org.haozf.cas.authentication;

import java.security.GeneralSecurityException;
import java.util.Map;

import javax.annotation.Resource;
import javax.security.auth.login.AccountLockedException;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

import org.haozf.api.user.service.IUserService;
import org.haozf.cas.encoder.impl.ErshouEncoder;
import org.haozf.common.enums.RegistSource;
import org.haozf.identity.user.model.User;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

/**
 * 天远：添加数据库验证
 * @author haozhengfeng
 *
 */
public class MyAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

	@NotNull
	private String sql;
	
    @NotNull
    private String getUsernameSql;
    
    @Resource
	private IUserService userService;
	
	@Override
	protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential credential) throws GeneralSecurityException, PreventedException {
		
		Map<String, Object> userDetail = null;
		
		//页面中传来的用户名  可以是用户名 、手机号、邮箱
		String[] ur = credential.getUsername().split("\\|"); 
		String username = ur[0];
		String source = "";
		
		Object[] object = new Object[] {username, username, username};
		if(ur.length>1){
			source = ur[1];
			object = new Object[] {username, username, username ,source};
			this.getUsernameSql = this.getUsernameSql+" and regist_source=? ";
		}
		
		try {
			//数据库中对应的的username
			userDetail = getJdbcTemplate().queryForMap(this.getUsernameSql, object);

			String user_name  = userDetail.get("user_name").toString();
			//如果查询出的username不为空，更新credential为username
			if(!"".equals(user_name)){
				username = user_name;
				credential.setUsername(user_name);
			}
			Object regist_source = userDetail.get("regist_source");
			
			//根据regist_source字段  实例化对应加密实现类
			if(regist_source.equals(RegistSource.ershou.toString())){
				setPasswordEncoder(new ErshouEncoder()); 
			}
			
			//比较生成的密码和数据库中的密码			
			final String encryptedPassword = getPasswordEncoder().encode(credential.getPassword());
			final String dbPassword = userDetail.get("password").toString();
			if (!dbPassword.equals(encryptedPassword)) {
				throw new FailedLoginException("Password does not match value on record.");
			}

			//查询详细信息
//			userDetail = getJdbcTemplate().queryForMap(
//				this.sql, new Object[] { username,dbPassword }
//			);
			
			//判断停用状态（对用户的判断  是否单独创建实现类？）
			if("0".equals(userDetail.get("user_state").toString())){
				throw new AccountLockedException(username + "已停用");
			}
			
		}catch(AccountLockedException e){
			e.printStackTrace();
			throw e;
		}catch (final IncorrectResultSizeDataAccessException e) {
			e.printStackTrace();
			if (e.getActualSize() == 0) {
				throw new AccountNotFoundException(username + " not found with SQL query");
			} else {
				throw new FailedLoginException("Multiple records found for " + username);
			}
		} catch (final DataAccessException e) {
			e.printStackTrace();
			throw new PreventedException("SQL exception while executing query for " + username, e);
		}
		
		//更新登录时间
		User user = new User();
		user.setUserID(Integer.parseInt(userDetail.get("user_id").toString()));
		userService.updateLastLoinTime(user);
		
//		SessionContext.principal.remove();
//		SessionContext.principal.set(new SimplePrincipal(username,userDetail));
		
		return createHandlerResult(credential, new SimplePrincipal(username,userDetail), null);
	}

	/**
	 * @param sql
	 *            The sql to set.
	 */
	public void setSql(final String sql) {
		this.sql = sql;
	}

	public void setGetUsernameSql(String getUsernameSql) {
		this.getUsernameSql = getUsernameSql;
	}
}
