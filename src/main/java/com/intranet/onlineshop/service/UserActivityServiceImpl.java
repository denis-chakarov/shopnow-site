package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.UserActivity;
import com.intranet.onlineshop.domain.models.service.UserActivityServiceModel;
import com.intranet.onlineshop.repository.UserActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of UserActivityService's business logic
 */
@Service
public class UserActivityServiceImpl implements UserActivityService {

    private final UserActivityRepository userActivityRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserActivityServiceImpl(UserActivityRepository userActivityRepository, ModelMapper modelMapper) {
        this.userActivityRepository = userActivityRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * @see UserActivityService#createUserActivity(UserActivityServiceModel)
     */
    @Override
    public void createUserActivity(UserActivityServiceModel userActivityServiceModel) {
        UserActivity userActivity = modelMapper.map(userActivityServiceModel, UserActivity.class);
        userActivityRepository.save(userActivity);
    }

    /**
     * @see UserActivityService#findAllActivitiesForUser(String)
     */
    @Override
    public List<UserActivityServiceModel> findAllActivitiesForUser(String username) {
        return userActivityRepository.findAllByUsername(username)
                .stream()
                .map(a -> modelMapper.map(a, UserActivityServiceModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @see UserActivityService#findAllActivities()
     */
    @Override
    public List<UserActivityServiceModel> findAllActivities() {
        return userActivityRepository.findAllByOrderByTimeOfActivityDesc()
                .stream()
                .map(a -> modelMapper.map(a, UserActivityServiceModel.class))
                .collect(Collectors.toList());
    }
}
