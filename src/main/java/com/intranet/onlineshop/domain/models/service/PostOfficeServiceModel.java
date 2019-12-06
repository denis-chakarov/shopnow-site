package com.intranet.onlineshop.domain.models.service;

public class PostOfficeServiceModel extends BaseServiceModel {

    private String officeName;
    private AddressServiceModel address;

    public PostOfficeServiceModel() {
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public AddressServiceModel getAddress() {
        return address;
    }

    public void setAddress(AddressServiceModel address) {
        this.address = address;
    }
}
