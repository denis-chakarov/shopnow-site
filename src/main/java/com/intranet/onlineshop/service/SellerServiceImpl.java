package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Product;
import com.intranet.onlineshop.domain.entities.ProductRequestMessage;
import com.intranet.onlineshop.domain.entities.Seller;
import com.intranet.onlineshop.domain.models.service.ProductRequestMessageServiceModel;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;
import com.intranet.onlineshop.repository.ProductRepository;
import com.intranet.onlineshop.repository.SellerRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, ProductService productService, ProductRepository productRepository, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    /**
     * @see SellerService#addProduct(String, ProductServiceModel)
     */
    @Override
    public void addProduct(String sellerUsername, ProductServiceModel productServiceModel) {
        Seller seller = sellerRepository.findSellerByUser_Username(sellerUsername);
        productService.createProduct(productServiceModel, seller);
        sellerRepository.save(seller);
    }

    /**
     * @see SellerService#getAllProductsByUsername(String)
     */
    @Override
    public Set<ProductServiceModel> getAllProductsByUsername(String username) {
        Seller seller = sellerRepository.findSellerByUser_Username(username);
        Set<Product> products = seller.getAvailableProducts();
        Type listType = new TypeToken<Set<ProductServiceModel>>() {}.getType();

        return modelMapper.map(products, listType);
    }

    /**
     * @see SellerService#getAllProductsByUsername(String)
     */
    @Override
    public Set<ProductRequestMessageServiceModel> getRequestedProductsByUsername(String username) {
        Seller seller = sellerRepository.findSellerByUser_Username(username);
        Set<ProductRequestMessage> messages = seller.getRequestMessages();
        return messages.stream()
                .map(m -> modelMapper.map(m, ProductRequestMessageServiceModel.class))
                .collect(Collectors.toSet());
    }
}
