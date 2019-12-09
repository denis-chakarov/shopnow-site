package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Status;
import com.intranet.onlineshop.domain.models.service.OrderServiceModel;

import java.util.List;

/**
 * Class used for declaring the business logic methods regarding the Order
 */
public interface OrderService {

    /**
     * create an order
     * @param orderServiceModel service model of the order
     */
    void createOrder(OrderServiceModel orderServiceModel);

    /**
     *  find al orders in the database
     * @return returns a list of all orders in the database
     */
    List<OrderServiceModel> findAllOrders();

    /**
     * find all orders with particular status
     * @param status the status of an order
     * @return returns a list of all orders with that status
     */
    List<OrderServiceModel> findAllOrdersWithStatus(Status status);

    /**
     * find all orders for a particular customer
     * @param username customer's username
     * @return returns a list of all the orders for that customer
     */
    List<OrderServiceModel> findOrdersByCustomer(String username);

    /**
     * find order by it's id
     * @param id order's id
     * @return returns the order with that id
     */
    OrderServiceModel findOrderById(String id);
}
