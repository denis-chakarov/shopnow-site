package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.view.CategoryViewModel;
import com.intranet.onlineshop.service.CategoryService;
import com.intranet.onlineshop.web.annotations.ActionLogger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CustomerController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @ActionLogger("customer.home.get.log")
    @GetMapping("/categories")
    public ModelAndView customerHome(ModelAndView modelAndView) {
        List<CategoryViewModel> categories = categoryService.findAllCategories()
                .stream()
                .map(c -> modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("categories", categories);
        return view("customer/customerPanel", modelAndView);
    }
}
