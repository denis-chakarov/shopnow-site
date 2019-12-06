package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.BaseUserEditServiceModel;
import com.intranet.onlineshop.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    void registerUser(UserServiceModel userServiceModel, String role);

    UserServiceModel findUserByUserName(String username);

    void editInternalUserProfile(BaseUserEditServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    void setUserRole(String id, String role);
}
