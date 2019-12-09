package com.intranet.onlineshop.domain.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Class used for representing the table post_offices in the database
 */
@Entity
@Table(name = "post_offices")
public class PostOffice extends BaseEntity {

    private String officeName;
    private Address address;

    public PostOffice() {
    }

    @Column(name = "office_name", nullable = false)
    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
