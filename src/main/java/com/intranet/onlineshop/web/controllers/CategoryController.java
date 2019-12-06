package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.service.CategoryServiceModel;
import com.intranet.onlineshop.domain.models.view.CategoryViewModel;
import com.intranet.onlineshop.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/fetch")
    @ResponseBody
    public List<CategoryViewModel> fetchCategories() {
        List<CategoryViewModel> categories = categoryService.findAllCategories()
                .stream()
                .map(c -> modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());

        return categories;
    }
}
