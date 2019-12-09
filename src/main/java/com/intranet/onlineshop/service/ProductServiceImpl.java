package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Category;
import com.intranet.onlineshop.domain.entities.Product;
import com.intranet.onlineshop.domain.entities.Seller;
import com.intranet.onlineshop.domain.models.service.ProductServiceModel;
import com.intranet.onlineshop.error.ProductNameAlreadyExistsException;
import com.intranet.onlineshop.error.ProductNotFoundException;
import com.intranet.onlineshop.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of ProductService's business logic
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService,
            ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    /**
     * @see ProductService#createProduct(ProductServiceModel, Seller)
     */
    @Override
    public void createProduct(ProductServiceModel productServiceModel, Seller seller) {
        Product product = this.productRepository
                .findByName(productServiceModel.getName())
                .orElse(null);

        if (product != null) {
            throw new ProductNameAlreadyExistsException("Product already exists");
        }

        product = modelMapper.map(productServiceModel, Product.class);
        product.setSeller(seller);
        productRepository.save(product);
    }

    /**
     * @see ProductService#findAllProducts()
     */
    @Override
    public List<ProductServiceModel> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    /**
     * @see ProductService#findProductById(String)
     */
    @Override
    public ProductServiceModel findProductById(String id) {
       return null;
    }

    /**
     * @see ProductService#editProduct(String, ProductServiceModel)
     */
    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with the given id was not found!"));

        /*productServiceModel.setCategories(
                this.categoryService.findAllCategories()
                        .stream()
                        .filter(c -> productServiceModel.getCategories().contains(c.getId()))
                        .collect(Collectors.toList())
        );*/

        product.setName(productServiceModel.getName());
        product.setDescription(productServiceModel.getDescription());
        product.setPrice(productServiceModel.getPrice());
        product.setCategories(
                productServiceModel.getCategories()
                        .stream()
                        .map(c -> this.modelMapper.map(c, Category.class))
                        .collect(Collectors.toList())
        );

        /*this.offerRepository.findByProduct_Id(product.getId())
                .ifPresent((o) -> {
                    o.setPrice(product.getPrice().multiply(new BigDecimal(0.8)));

                    this.offerRepository.save(o);
                });*/

        return this.modelMapper.map(productRepository.saveAndFlush(product), ProductServiceModel.class);
    }

    /**
     * @see ProductService#deleteProduct(String)
     */
    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product with the given id was not found!"));

        productRepository.delete(product);
    }

    /**
     * @see ProductService#findAllByCategory(String)
     */
    @Override
    public List<ProductServiceModel> findAllByCategory(String category) {
        //TODO: OPTIMIZE FILTERING

        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories().stream().anyMatch(categoryStream -> categoryStream.getName().equals(category)))
                .map(product -> modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

}
