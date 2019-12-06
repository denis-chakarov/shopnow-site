package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.PostOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, String> {

    PostOffice findByOfficeName(String name);
}
