package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Order;
import com.intranet.onlineshop.domain.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class used for executing queries on the orders table represented by the Order class
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    /**
     * finds an order by status
     * @param status the order's status
     * @return returns the order
     */
    List<Order> findAllByStatusEquals(Status status);
}
