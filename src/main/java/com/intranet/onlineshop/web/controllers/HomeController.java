package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.view.CategoryViewModel;
import com.intranet.onlineshop.service.CategoryService;
import com.intranet.onlineshop.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController extends BaseController {

    private ModelMapper modelMapper;
    private CategoryService categoryService;

    @Autowired
    public HomeController(ModelMapper modelMapper, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    @PageTitle("Welcome")
    public ModelAndView index() {
        return view("index");
    }


    @GetMapping("/home")
    @PageTitle("Home")
    public ModelAndView home(ModelAndView modelAndView) {

//        List<CategoryViewModel> categories = this.categoryService.findAllCategories()
//                .stream()
//                .map(category -> this.modelMapper.map(category, CategoryViewModel.class))
//                .collect(Collectors.toList());
//
//        modelAndView.addObject("categories", categories);

        return super.view("home", modelAndView);
    }
}
