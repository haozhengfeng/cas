package org.haozf.oauth2.authz.domain.users;

import java.util.List;

import org.haozf.oauth2.core.domain.users.Roles;
import org.haozf.oauth2.core.domain.users.Users;
import org.haozf.oauth2.core.domain.users.UsersRepository;

/**
 * 2016/6/3
 *
 * @author Shengzhao Li
 */

public interface UsersAuthzRepository extends UsersRepository {

    List<Users> findUsersByUsername(String username);

    List<Roles> findAvailableRolesList();

    Users findByUsername(String username);

    int saveUsers(Users users);

    Roles findRolesByGuid(String guid);

    void insertUserRoles(int userId, int rolesId);
}