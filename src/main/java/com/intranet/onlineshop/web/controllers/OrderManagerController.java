package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.entities.Status;
import com.intranet.onlineshop.domain.models.service.OrderServiceModel;
import com.intranet.onlineshop.domain.models.view.QuickOrderViewModel;
import com.intranet.onlineshop.repository.OrderRepository;
import com.intranet.onlineshop.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/manager")
public class OrderManagerController extends BaseController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @Autowired
    public OrderManagerController(OrderService orderService, ModelMapper modelMapper) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/orders/confirmed")
    public ModelAndView showConfirmedOrders(ModelAndView modelAndView) {
        List<OrderServiceModel> confirmedOrders = orderService.findAllOrdersWithStatus(Status.CONFIRMED);
        List<QuickOrderViewModel> orders = confirmedOrders
                .stream()
                .map(o -> modelMapper.map(o, QuickOrderViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("orders", orders);

        return view( "manager/home", modelAndView);
    }

    @GetMapping("/orders/{id}")
    public ModelAndView showOrderDetails(ModelAndView modelAndView, @PathVariable String id) {
        OrderServiceModel order = orderService.findOrderById(id);
        modelAndView.addObject("order", order);

        return view("manager/order-details", modelAndView);
    }
}
