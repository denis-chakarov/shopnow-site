package com.intranet.onlineshop.service;

import com.intranet.onlineshop.domain.entities.Customer;
import com.intranet.onlineshop.domain.entities.Seller;
import com.intranet.onlineshop.domain.entities.User;
import com.intranet.onlineshop.domain.models.service.BaseUserEditServiceModel;
import com.intranet.onlineshop.domain.models.service.CustomerServiceModel;
import com.intranet.onlineshop.domain.models.service.SellerServiceModel;
import com.intranet.onlineshop.domain.models.service.UserServiceModel;
import com.intranet.onlineshop.repository.CustomerRepository;
import com.intranet.onlineshop.repository.SellerRepository;
import com.intranet.onlineshop.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder, CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel, String role) {
        this.roleService.seedRolesInDb();
        userServiceModel.setAuthorities(new LinkedHashSet<>());
//        if (this.userRepository.count() == 0) {
//            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ROOT"));
//        } else {
//            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_CUSTOMER"));
//        }
        userServiceModel.getAuthorities().add(roleService.findByAuthority(role));
        switch (role) {
            case "ROLE_CUSTOMER":
                CustomerServiceModel customerServiceModel = (CustomerServiceModel)userServiceModel;
                Customer customer = modelMapper.map(customerServiceModel, Customer.class);
                customer.getUser().setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
                customerRepository.saveAndFlush(customer);
                break;
            case "ROLE_SELLER":
                SellerServiceModel sellerServiceModel = (SellerServiceModel)userServiceModel;
                Seller seller = modelMapper.map(sellerServiceModel, Seller.class);
                seller.getUser().setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
                sellerRepository.saveAndFlush(seller);
                break;
            default:
                User user = modelMapper.map(userServiceModel, User.class);
                user.setPassword(bCryptPasswordEncoder.encode(userServiceModel.getPassword()));
                userRepository.saveAndFlush(user);
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    @Override
    public void editInternalUserProfile(BaseUserEditServiceModel userServiceModel) {
        User user = userRepository.findByUsername(userServiceModel.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException("Username not found!"));

        user.setPassword(userServiceModel.getNewPassword() != null ?
                bCryptPasswordEncoder.encode(userServiceModel.getNewPassword()) :
                user.getPassword());

        userRepository.save(user);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void setUserRole(String id, String role) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incorrect id!"));

        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        userServiceModel.getAuthorities().clear();

        switch (role) {
            case "user":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                break;
            case "moderator":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                break;
            case "admin":
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_USER"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_MODERATOR"));
                userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ROLE_ADMIN"));
                break;
        }

        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }
}
