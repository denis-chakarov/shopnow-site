package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class used for executing queries on the roles table represented by the Role class
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Role findByAuthority(String authority);
}
