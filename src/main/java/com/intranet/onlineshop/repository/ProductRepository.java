package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository class used for executing queries on the products table represented by the Product class
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByName(String name);
    List<Product> findAllByNameIn(List<String> names);
}
