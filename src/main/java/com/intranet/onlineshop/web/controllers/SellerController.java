package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.binding.ProductBindingModel;
import com.intranet.onlineshop.domain.models.service.CategoryServiceModel;
import com.intranet.onlineshop.domain.models.service.ProductRequestMessageServiceModel;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;
import com.intranet.onlineshop.domain.models.view.ProductAllViewModel;
import com.intranet.onlineshop.service.CategoryService;
import com.intranet.onlineshop.service.CloudinaryService;
import com.intranet.onlineshop.service.ProductService;
import com.intranet.onlineshop.service.SellerService;
import com.intranet.onlineshop.web.annotations.ActionLogger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller")
public class SellerController extends BaseController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;
    private final SellerService sellerService;
    private final CloudinaryService cloudinaryService;

    @Autowired
    public SellerController(ProductService productService, CategoryService categoryService, ModelMapper modelMapper, SellerService sellerService, CloudinaryService cloudinaryService) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
        this.sellerService = sellerService;
        this.cloudinaryService = cloudinaryService;
    }

    @ActionLogger("seller.create.product.get.log")
    @GetMapping("/create/product")
    public ModelAndView createProduct() {
        return view("seller/create-product");
    }

    @ActionLogger("seller.create.product.post.log")
    @PostMapping("/create/product")
    public ModelAndView createProductConfirm(@ModelAttribute(name = "model") ProductBindingModel model,
                                             Principal user) throws IOException {
        ProductServiceModel productServiceModel = modelMapper.map(model, ProductServiceModel.class);
        List<CategoryServiceModel> categories =  categoryService.findAllCategories()
                        .stream()
                        .filter(c -> model.getCategories().contains(c.getId()))
                        .collect(Collectors.toList());
        productServiceModel.setCategories(categories);
        productServiceModel.setImageUrl(cloudinaryService.uploadImage(model.getImage()));
        sellerService.addProduct(user.getName(), productServiceModel);

        return redirect("/seller/all");
    }

    @ActionLogger("seller.view.all.products.get.log")
    @GetMapping("/all")
    public ModelAndView showAllProducts(ModelAndView modelAndView, Principal principal) {
        Set<ProductServiceModel> products = sellerService.getAllProductsByUsername(principal.getName());
        Set<ProductAllViewModel> productsView = products
                .stream()
                .map(p -> modelMapper.map(p, ProductAllViewModel.class))
                .collect(Collectors.toSet());
        modelAndView.addObject("products", productsView);

        return view("seller/all-products", modelAndView);
    }

    @ActionLogger("seller.view.requests.get.log")
    @GetMapping("/requests")
    public ModelAndView showRequestedProducts(ModelAndView modelAndView, Principal principal) {
        Set<ProductRequestMessageServiceModel> productRequests = sellerService.getRequestedProductsByUsername(principal.getName());
        modelAndView.addObject("productRequests", productRequests);

        return view("seller/sellerPanel", modelAndView);
    }
}
