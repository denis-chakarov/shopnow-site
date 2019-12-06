package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Status;
import com.intranet.onlineshop.domain.models.service.OrderServiceModel;

import java.util.List;

public interface OrderService {

    void createOrder(OrderServiceModel orderServiceModel);

    List<OrderServiceModel> findAllOrders();

    List<OrderServiceModel> findAllOrdersWithStatus(Status status);

    List<OrderServiceModel> findOrdersByCustomer(String username);

    OrderServiceModel findOrderById(String id);
}
