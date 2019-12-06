package com.intranet.onlineshop.repository;

        import com.intranet.onlineshop.domain.entities.UserActivity;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface UserActivityRepository extends JpaRepository<UserActivity, String> {

    List<UserActivity> findAllByUsername(String username);
    List<UserActivity> findAllByOrderByTimeOfActivityDesc();
}
