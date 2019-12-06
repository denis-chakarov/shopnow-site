package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.ProductRequestMessageServiceModel;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;
import java.util.Set;

public interface SellerService {

    void addProduct(String sellerUsername, ProductServiceModel productServiceModel);

    Set<ProductServiceModel> getAllProductsByUsername(String username);

    Set<ProductRequestMessageServiceModel> getRequestedProductsByUsername(String username);

}
