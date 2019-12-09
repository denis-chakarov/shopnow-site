package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.PostOfficeConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class used for executing queries on the
 * post_office_connections table represented by the PostOfficeConnection class
 */
@Repository
public interface PostOfficeConnectionRepository extends JpaRepository<PostOfficeConnection, String> {

}
