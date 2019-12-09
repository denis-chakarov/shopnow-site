package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository class used for executing queries on the user_activity_logger table represented by the UserActivity class
 */
@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, String> {

    /**
     * finds all activities by username
     * @param username the username
     * @return returns a list of user activities
     */
    List<UserActivity> findAllByUsername(String username);

    /**
     * finds all user activities and sorted in descending order
     * @return returns a list of the user activities
     */
    List<UserActivity> findAllByOrderByTimeOfActivityDesc();
}
