package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.models.service.RoleServiceModel;
import com.intranet.onlineshop.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of RoleService's business logic
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    /*@Override
    public void seedRolesInDb() {
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role("ROLE_CUSTOMER"));
            roleRepository.save(new Role("ROLE_SELLER"));
            roleRepository.save(new Role("ROLE_ORDER_MANAGER"));
            roleRepository.save(new Role("ROLE_SUPPORT"));
            roleRepository.save(new Role("ROLE_ROOT"));
        }
    }*/

    /**
     * @see RoleService#findAllRoles()
     */
    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return  roleRepository.findAll()
                .stream()
                .map(r -> this.modelMapper.map(r, RoleServiceModel.class))
                .collect(Collectors.toSet());
    }

    /**
     * @see RoleService#findByAuthority(String)
     */
    @Override
    public RoleServiceModel findByAuthority(String authority) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(authority), RoleServiceModel.class);
    }
}
