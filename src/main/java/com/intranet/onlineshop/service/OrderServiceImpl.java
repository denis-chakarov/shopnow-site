package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Order;
import com.intranet.onlineshop.domain.entities.Status;
import com.intranet.onlineshop.domain.models.service.OrderServiceModel;
import com.intranet.onlineshop.error.OrderNotFoundException;
import com.intranet.onlineshop.repository.OrderRepository;
import com.intranet.onlineshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserService userService,
            ModelMapper modelMapper
    ) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public void createOrder(OrderServiceModel orderServiceModel) {
        orderServiceModel.setPurchaseDate(LocalDateTime.now());

        orderRepository.saveAndFlush(modelMapper.map(orderServiceModel, Order.class));
    }

    @Override
    public List<OrderServiceModel> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderServiceModel> orderServiceModels = orders
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());

        return orderServiceModels;
    }

    @Override
    public List<OrderServiceModel> findAllOrdersWithStatus(Status status) {
        List<Order> orders = orderRepository.findAllByStatusEquals(status);
        List<OrderServiceModel> orderServiceModels = orders
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());

        return orderServiceModels;
    }

    @Override
    public List<OrderServiceModel> findOrdersByCustomer(String username) {
        return null;/*this.orderRepository.findAllOrdersByCustomer_UsernameOrderByFinishedOn(username)
                .stream()
                .map(o -> modelMapper.map(o, OrderServiceModel.class))
                .collect(Collectors.toList());*/
    }

    @Override
    public OrderServiceModel findOrderById(String id) {
        return orderRepository.findById(id)
                .map(o -> this.modelMapper.map(o, OrderServiceModel.class))
                .orElseThrow(() -> new OrderNotFoundException("Nqma Go"));
    }
}
