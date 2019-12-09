package com.intranet.onlineshop.domain.entities;
import javax.persistence.*;
import java.util.List;

/**
 * Class used for representing the table customers in the database
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private Address address;
    private int purchasePoints;
    private List<Order> madeOrders;
    private User user;

    public Customer() {
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Column(name = "purchase_points", columnDefinition = "int default 30")
    public int getPurchasePoints() {
        return purchasePoints;
    }

    public void setPurchasePoints(int purchasePoints) {
        this.purchasePoints = purchasePoints;
    }

    @OneToMany(targetEntity = Order.class, mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Order> getMadeOrders() {
        return madeOrders;
    }

    public void setMadeOrders(List<Order> madeOrders) {
        this.madeOrders = madeOrders;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
