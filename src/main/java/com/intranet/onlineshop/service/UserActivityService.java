package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.UserActivityServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserActivityService {

    void createUserActivity(UserActivityServiceModel userActivityServiceModel);
    List<UserActivityServiceModel> findAllActivitiesForUser(String username);
    List<UserActivityServiceModel> findAllActivities();

}
