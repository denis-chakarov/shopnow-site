package com.intranet.onlineshop.repository;
import com.intranet.onlineshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class used for executing queries on the users table represented by the User class
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * finds user by username
     * @param username the username
     * @return returns the user if he is available
     */
    Optional<User> findByUsername(String username);

    /**
     * finds user by email
     * @param email the email address
     * @return returns the user if he is available
     */
    Optional<User> findByEmail(String email);
}
