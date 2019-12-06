package com.intranet.onlineshop.domain.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "sellers")
public class Seller extends BaseEntity {

    private String name;
    private Address address;
    private User user;
    private int successfulOrders;
    private int allOrders;
    private Set<Product> availableProducts;
    private Set<ProductRequestMessage> requestMessages;
    public Seller() {
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "successful_orders")
    public int getSuccessfulOrders() {
        return successfulOrders;
    }

    public void setSuccessfulOrders(int successfulOrders) {
        this.successfulOrders = successfulOrders;
    }

    @Column(name = "all_orders")
    public int getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(int allOrders) {
        this.allOrders = allOrders;
    }

    @OneToMany(targetEntity = Product.class, mappedBy = "seller", cascade = CascadeType.ALL)
    public Set<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(Set<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    @OneToMany(targetEntity = ProductRequestMessage.class, mappedBy = "seller", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<ProductRequestMessage> getRequestMessages() {
        return requestMessages;
    }

    public void setRequestMessages(Set<ProductRequestMessage> requestMessages) {
        this.requestMessages = requestMessages;
    }
}
