package com.intranet.onlineshop.domain.models.binding;

import javax.validation.constraints.NotNull;

public class AddressEditBindingModel {

    @NotNull(message = "country cannot be empty!")
    private String countryName;

    @NotNull(message = "city cannot be empty!")
    private String city;

    @NotNull(message = "street cannot be empty!")
    private String street;

    @NotNull(message = "postal code cannot be empty!")
    private String postalCode;

    public AddressEditBindingModel() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
