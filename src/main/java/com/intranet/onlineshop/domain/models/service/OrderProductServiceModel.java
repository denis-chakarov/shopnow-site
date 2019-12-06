package com.intranet.onlineshop.domain.models.service;

public class OrderProductServiceModel extends BaseServiceModel {

    private ProductServiceModel product;
    private int quantity;

    public OrderProductServiceModel() {
    }

    public ProductServiceModel getProduct() {
        return product;
    }

    public void setProduct(ProductServiceModel product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
