package com.intranet.onlineshop.domain.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class used for representing the table product_request_messages in the database
 */
@Entity
@Table(name = "product_request_messages")
public class ProductRequestMessage extends BaseEntity {

    private Seller seller;
    private List<OrderProduct> products;
    private PostOffice postOffice;
    private LocalDateTime requestDate;

    public ProductRequestMessage() {
    }

    @ManyToOne
    @JoinColumn(name = "request_message_id", referencedColumnName = "id")
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @ManyToMany(targetEntity = OrderProduct.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "messages_products",
            joinColumns = @JoinColumn(
                    name = "product_messages_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "products_id",
                    referencedColumnName = "id"
            )
    )
    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    @ManyToOne
    @JoinColumn(name = "post_office_id", referencedColumnName = "id")
    public PostOffice getPostOffice() {
        return postOffice;
    }

    public void setPostOffice(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    @Column(name = "request_date")
    public LocalDateTime getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
}
