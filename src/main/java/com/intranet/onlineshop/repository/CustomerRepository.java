package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class used for executing queries on the customers table represented by the Customer class
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
