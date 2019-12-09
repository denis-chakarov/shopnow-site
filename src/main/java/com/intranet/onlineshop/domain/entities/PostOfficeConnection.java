package com.intranet.onlineshop.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class used for representing the table post_office_connections in the database
 */
@Entity
@Table(name = "post_office_connections")
public class PostOfficeConnection extends BaseEntity {

    private String firstPostOfficeName;
    private String secondPostOfficeName;
    private int distance;

    public PostOfficeConnection() {
    }

    @Column(name = "first_post_office_name")
    public String getFirstPostOfficeName() {
        return firstPostOfficeName;
    }

    public void setFirstPostOfficeName(String firstPostOfficeName) {
        this.firstPostOfficeName = firstPostOfficeName;
    }

    @Column(name = "second_post_office_name")
    public String getSecondPostOfficeName() {
        return secondPostOfficeName;
    }

    public void setSecondPostOfficeName(String secondPostOfficeName) {
        this.secondPostOfficeName = secondPostOfficeName;
    }

    @Column(name = "distance")
    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
