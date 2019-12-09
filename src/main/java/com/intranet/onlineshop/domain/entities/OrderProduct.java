package com.intranet.onlineshop.domain.entities;

import javax.persistence.*;

/**
 * Class used for representing the table order_products in the database
 */
@Entity
@Table(name = "order_products")
public class OrderProduct extends BaseEntity {

    private Product product;
    private int quantity;

    public OrderProduct() {
    }

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
