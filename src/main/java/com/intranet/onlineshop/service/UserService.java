package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.BaseUserEditServiceModel;
import com.intranet.onlineshop.domain.models.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Class used for declaring the business logic regarding the User
 */
public interface UserService extends UserDetailsService {

    /**
     * register a user
     * @param userServiceModel service model of user's data about to be registered
     * @param role the role name for that user
     */
    void registerUser(UserServiceModel userServiceModel, String role);

    /**
     * find a user by username
     * @param username the username
     * @return returns a service model of that user
     */
    UserServiceModel findUserByUserName(String username);

    /**
     * edit user's data only applicable for internal users like orders manager, admin, support
     * @param userServiceModel service model of the user
     */
    void editInternalUserProfile(BaseUserEditServiceModel userServiceModel);

    /**
     * find all users
     * @return returns a list of all the users in database
     */
    List<UserServiceModel> findAllUsers();

    /**
     * set a user role
     * @param id user's id
     * @param role role name
     */
    void setUserRole(String id, String role);
}
