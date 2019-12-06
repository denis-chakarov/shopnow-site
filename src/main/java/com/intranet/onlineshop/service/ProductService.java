package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Seller;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;

import java.util.List;

public interface ProductService {

    void createProduct(ProductServiceModel productServiceModel, Seller seller);

    List<ProductServiceModel> findAllProducts();

    ProductServiceModel findProductById(String id);

    ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel);

    void deleteProduct(String id);

    List<ProductServiceModel> findAllByCategory(String category);
}
