package com.intranet.onlineshop.domain.models.service;

import com.intranet.onlineshop.domain.entities.Customer;
import com.intranet.onlineshop.domain.entities.Role;
import com.intranet.onlineshop.mappings.IHaveCustomMappings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Set;

public class CustomerServiceModel extends UserServiceModel implements IHaveCustomMappings {

    private String firstName;
    private String lastName;
    private AddressServiceModel address;

    public CustomerServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressServiceModel getAddress() {
        return address;
    }

    public void setAddress(AddressServiceModel address) {
        this.address = address;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        TypeMap<CustomerServiceModel, Customer> typeMap = mapper.createTypeMap(CustomerServiceModel.class, Customer.class);
        typeMap.addMapping(CustomerServiceModel::getUsername, (dest, v) -> dest.getUser().setUsername((String)v));
        typeMap.addMapping(CustomerServiceModel::getPassword, (dest, v) -> dest.getUser().setPassword((String)v));
        typeMap.addMapping(CustomerServiceModel::getEmail, (dest, v) -> dest.getUser().setEmail((String)v));
        typeMap.addMapping(CustomerServiceModel::getAuthorities, (dest, v) -> dest.getUser().setAuthorities((Set<Role>) v));
    }
}
