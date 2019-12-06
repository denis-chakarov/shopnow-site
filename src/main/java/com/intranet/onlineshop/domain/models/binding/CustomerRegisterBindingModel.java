package com.intranet.onlineshop.domain.models.binding;

import com.intranet.onlineshop.domain.models.base.UserRegisterBindingModel;
import com.intranet.onlineshop.domain.models.service.CustomerServiceModel;
import com.intranet.onlineshop.mappings.IHaveCustomMappings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class CustomerRegisterBindingModel extends UserRegisterBindingModel implements IHaveCustomMappings {

    private String firstName;
    private String lastName;

    public CustomerRegisterBindingModel() {
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

    @Override
    public void configureMappings(ModelMapper mapper) {
        TypeMap<CustomerRegisterBindingModel, CustomerServiceModel> typeMap
                = mapper.createTypeMap(CustomerRegisterBindingModel.class, CustomerServiceModel.class);

        typeMap.addMapping(CustomerRegisterBindingModel::getCity, (dest, v) -> dest.getAddress().setCity((String)v));
        typeMap.addMapping(CustomerRegisterBindingModel::getCountryName, (dest, v) -> dest.getAddress().setCountryName((String)v));
        typeMap.addMapping(CustomerRegisterBindingModel::getCity, (dest, v) -> dest.getAddress().setCity((String)v));
        typeMap.addMapping(CustomerRegisterBindingModel::getPostalCode, (dest, v) -> dest.getAddress().setPostalCode((String)v));
        typeMap.addMapping(CustomerRegisterBindingModel::getStreet, (dest, v) -> dest.getAddress().setStreet((String)v));
    }
}
