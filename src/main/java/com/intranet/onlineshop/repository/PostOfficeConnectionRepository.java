package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.PostOfficeConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostOfficeConnectionRepository extends JpaRepository<PostOfficeConnection, String> {

}
