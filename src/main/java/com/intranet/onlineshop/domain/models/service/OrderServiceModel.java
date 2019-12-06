package com.intranet.onlineshop.domain.models.service;

import com.intranet.onlineshop.domain.entities.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderServiceModel extends BaseServiceModel {

    private List<OrderProductServiceModel> products;
    private CustomerServiceModel customer;
    private BigDecimal totalPrice;
    private LocalDateTime purchaseDate;
    private Status status;

    public OrderServiceModel() {
    }

    public List<OrderProductServiceModel> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductServiceModel> products) {
        this.products = products;
    }

    public CustomerServiceModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerServiceModel customer) {
        this.customer = customer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
