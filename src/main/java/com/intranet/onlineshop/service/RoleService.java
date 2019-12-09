package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.RoleServiceModel;

import java.util.Set;

/**
 * Class used for declaring the business logic methods regarding the Role
 */
public interface RoleService {

    //void seedRolesInDb();

    /**
     * find all roles in database
     * @return returns a list of all the roles
     */
    Set<RoleServiceModel> findAllRoles();

    /**
     * find a role by role name
     * @param authority role name
     * @return returns a service model of the role
     */
    RoleServiceModel findByAuthority(String authority);
}
