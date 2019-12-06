package com.intranet.onlineshop.domain.models.base;

import com.intranet.onlineshop.mappings.IHaveCustomMappings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class UserRegisterBindingModel extends BaseUserBindingModel {

    private String countryName;
    private String city;
    private String street;
    private String postalCode;

    public UserRegisterBindingModel() {
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
