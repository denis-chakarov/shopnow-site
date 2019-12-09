package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.UserActivityServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class used for declaring the business logic methods regarding the user activities
 */
public interface UserActivityService {

    /**
     * create a user activity
     * @param userActivityServiceModel service model of the user activity
     */
    void createUserActivity(UserActivityServiceModel userActivityServiceModel);

    /**
     * find all activities for a particular user
     * @param username the username
     * @return returns a list of all the activities for that user
     */
    List<UserActivityServiceModel> findAllActivitiesForUser(String username);

    /**
     * find all activities for all users in database
     * @return returns a list of all the activities in the database
     */
    List<UserActivityServiceModel> findAllActivities();

}
