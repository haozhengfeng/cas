package org.haozf.oauth2.core.domain.users;

import java.util.Date;
import java.util.List;

import org.haozf.oauth2.core.domain.AbstractDomain;
import org.haozf.oauth2.core.domain.share.BeanProvider;

/**
 * 2016/6/3
 * <p/>
 * Table: users
 *
 * @author Shengzhao Li
 */
public class Users extends AbstractDomain {
    private static final long serialVersionUID = -3990278799194556012L;

    private transient UsersRepository usersRepository = BeanProvider.getBean(UsersRepository.class);

    private String username;
    private String password;


    private boolean defaultUser;
    private Date lastLoginTime;

    public Users() {
    }


    public List<Roles> rolesList() {
        return usersRepository.findUsersRolesList(this.guid);
    }


    public String username() {
        return username;
    }

    public Users username(String username) {
        this.username = username;
        return this;
    }

    public String password() {
        return password;
    }

    public Users password(String password) {
        this.password = password;
        return this;
    }

    public boolean defaultUser() {
        return defaultUser;
    }

    public Users defaultUser(boolean defaultUser) {
        this.defaultUser = defaultUser;
        return this;
    }

    public Date lastLoginTime() {
        return lastLoginTime;
    }

    public Users lastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }
}
