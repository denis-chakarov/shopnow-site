package com.intranet.onlineshop.domain.models.service;
import java.util.List;

public class ProductRequestMessageServiceModel extends BaseServiceModel {

    private List<ProductServiceModel> products;
    private PostOfficeServiceModel postOffice;

    public ProductRequestMessageServiceModel() {
    }

    public List<ProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductServiceModel> products) {
        this.products = products;
    }

    public PostOfficeServiceModel getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOfficeServiceModel postOffice) {
        this.postOffice = postOffice;
    }
}
