package com.intranet.onlineshop.domain.models.service;

import com.intranet.onlineshop.domain.entities.Role;
import com.intranet.onlineshop.domain.entities.Seller;
import com.intranet.onlineshop.mappings.IHaveCustomMappings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Set;

public class SellerServiceModel extends UserServiceModel implements IHaveCustomMappings {

    private String name;
    private AddressServiceModel address;

    public SellerServiceModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressServiceModel getAddress() {
        return address;
    }

    public void setAddress(AddressServiceModel address) {
        this.address = address;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        TypeMap<SellerServiceModel, Seller> typeMap = mapper.createTypeMap(SellerServiceModel.class, Seller.class);
        typeMap.addMapping(SellerServiceModel::getUsername, (dest, v) -> dest.getUser().setUsername((String)v));
        typeMap.addMapping(SellerServiceModel::getPassword, (dest, v) -> dest.getUser().setPassword((String)v));
        typeMap.addMapping(SellerServiceModel::getEmail, (dest, v) -> dest.getUser().setEmail((String)v));
        typeMap.addMapping(SellerServiceModel::getAuthorities, (dest, v) -> dest.getUser().setAuthorities((Set<Role>) v));
    }
}
