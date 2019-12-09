package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class used for executing queries on the post_offices table represented by the PostOffice class
 */
@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, String> {

    /**
     * finds the post office by name
     * @param name name of post office
     * @return returns the post office
     */
    PostOffice findByOfficeName(String name);
}
