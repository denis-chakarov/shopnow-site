package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.ProductRequestMessageServiceModel;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;
import java.util.Set;

/**
 * Class used for declaring the business logic methods regarding the Seller
 */
public interface SellerService {

    /**
     * add product for a particular seller
     * @param sellerUsername seller's username
     * @param productServiceModel service model of the product about be created
     */
    void addProduct(String sellerUsername, ProductServiceModel productServiceModel);

    /**
     * find all products by username
     * @param username seller's username
     * @return returns a list of all the products for that seller
     */
    Set<ProductServiceModel> getAllProductsByUsername(String username);

    /**
     * finds all the requested products by the order's manager for a particular seller
     * @param username seller's username
     * @return returns a set of all the requested products for that seller
     */
    Set<ProductRequestMessageServiceModel> getRequestedProductsByUsername(String username);

}
