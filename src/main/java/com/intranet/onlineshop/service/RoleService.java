package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDb();

//    void assignUserRoles(UserServiceModel userServiceModel, long numberOfUsers);

    Set<RoleServiceModel> findAllRoles();

    RoleServiceModel findByAuthority(String authority);
}
