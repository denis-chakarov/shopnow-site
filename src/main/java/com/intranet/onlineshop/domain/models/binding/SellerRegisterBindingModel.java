package com.intranet.onlineshop.domain.models.binding;

import com.intranet.onlineshop.domain.models.base.UserRegisterBindingModel;
import com.intranet.onlineshop.domain.models.service.SellerServiceModel;
import com.intranet.onlineshop.mappings.IHaveCustomMappings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class SellerRegisterBindingModel extends UserRegisterBindingModel implements IHaveCustomMappings {

    private String name;

    public SellerRegisterBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void configureMappings(ModelMapper mapper) {
        TypeMap<SellerRegisterBindingModel, SellerServiceModel> typeMap
                = mapper.createTypeMap(SellerRegisterBindingModel.class, SellerServiceModel.class);
        typeMap.addMapping(SellerRegisterBindingModel::getCity, (dest, v) -> dest.getAddress().setCity((String)v));
        typeMap.addMapping(SellerRegisterBindingModel::getCountryName, (dest, v) -> dest.getAddress().setCountryName((String)v));
        typeMap.addMapping(SellerRegisterBindingModel::getCity, (dest, v) -> dest.getAddress().setCity((String)v));
        typeMap.addMapping(SellerRegisterBindingModel::getPostalCode, (dest, v) -> dest.getAddress().setPostalCode((String)v));
        typeMap.addMapping(SellerRegisterBindingModel::getStreet, (dest, v) -> dest.getAddress().setStreet((String)v));
    }
}
