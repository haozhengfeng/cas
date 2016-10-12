package org.haozf.oauth2.core.domain.users;

import java.util.List;

import org.haozf.oauth2.core.domain.share.Repository;

/**
 * 2016/6/3
 *
 * @author Shengzhao Li
 */

public interface UsersRepository extends Repository {

    List<Roles> findUsersRolesList(String usersGuid);

    List<String> findPermissionsByRoles(String rolesGuid);
}