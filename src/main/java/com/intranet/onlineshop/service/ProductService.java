package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Seller;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;

import java.util.List;

/**
 * Class used for declaring the business logic methods regarding the Product
 */
public interface ProductService {

    /**
     * create a product in the database
     * @param productServiceModel service model of the product
     * @param seller seller model representing the creator of the product
     */
    void createProduct(ProductServiceModel productServiceModel, Seller seller);

    /**
     * find all products in database
     * @return returns a list of all the products
     */
    List<ProductServiceModel> findAllProducts();

    /**
     * find a product by id
     * @param id product's id
     * @return returns the requested product in a service model
     */
    ProductServiceModel findProductById(String id);

    /**
     * edit a product
     * @param id product's id
     * @param productServiceModel service model carrying the product's changes
     * @return return  the edited product in a service model
     */
    ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel);

    /**
     * delete a product
     * @param id product's id
     */
    void deleteProduct(String id);

    /**
     * find all categories for a particular category
     * @param category the category name
     * @return returns all products for that category
     */
    List<ProductServiceModel> findAllByCategory(String category);
}
