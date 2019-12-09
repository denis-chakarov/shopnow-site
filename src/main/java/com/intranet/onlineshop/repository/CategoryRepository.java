package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository class used for executing queries on the categories table represented by the Category class
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    /**
     * finds a category by name
     * @param name category name
     * @return returns the category if available
     */
    Optional<Category> findByName(String name);
}
