package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.view.ProductAllViewModel;
import com.intranet.onlineshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/fetch/{category}")
    @ResponseBody
    List<ProductAllViewModel> showProductsByCategory(@PathVariable String category) {
        return productService.findAllByCategory(category)
                .stream()
                .map(product -> modelMapper.map(product, ProductAllViewModel.class))
                .collect(Collectors.toList());
    }
}
