package com.intranet.onlineshop.repository;

import com.intranet.onlineshop.domain.entities.Order;
import com.intranet.onlineshop.domain.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    //List<Order> findAllOrdersByCustomer_UsernameOrderByFinishedOn(String username);
    List<Order> findAllByStatusEquals(Status status);
}
