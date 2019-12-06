package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.binding.BaseUserEditBindingModel;
import com.intranet.onlineshop.domain.models.binding.CustomerRegisterBindingModel;
import com.intranet.onlineshop.domain.models.binding.SellerRegisterBindingModel;
import com.intranet.onlineshop.domain.models.service.BaseUserEditServiceModel;
import com.intranet.onlineshop.domain.models.service.CustomerServiceModel;
import com.intranet.onlineshop.domain.models.service.SellerServiceModel;
import com.intranet.onlineshop.service.UserService;
import com.intranet.onlineshop.validation.user.BaseUserEditValidator;
import com.intranet.onlineshop.validation.user.UserRegisterValidator;
import com.intranet.onlineshop.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserRegisterValidator userRegisterValidator;
    private final BaseUserEditValidator baseUserEditValidator;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, UserRegisterValidator userRegisterValidator, BaseUserEditValidator baseUserEditValidator) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRegisterValidator = userRegisterValidator;
        this.baseUserEditValidator = baseUserEditValidator;
    }

    @PageTitle(value = "users.page.title.register.customer")
    @GetMapping("/register/customer")
    public ModelAndView registerCustomer(ModelAndView modelAndView, @ModelAttribute(name = "model") CustomerRegisterBindingModel model) {
        modelAndView.addObject("model", model);
        return view("user/registerCustomer", modelAndView);

    }

    @PostMapping("/register/customer")
    public ModelAndView registerCustomerConfirm(ModelAndView modelAndView,
                                        @ModelAttribute(name = "model") CustomerRegisterBindingModel model, BindingResult bindingResult) {
        userRegisterValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            return view("user/registerCustomer", modelAndView);
        }
        CustomerServiceModel customerServiceModel = this.modelMapper.map(model, CustomerServiceModel.class);
        userService.registerUser(customerServiceModel, "ROLE_CUSTOMER");

        return redirect("/login");
    }

    @PageTitle(value = "users.page.title.register.seller")
    @GetMapping("/register/seller")
    public ModelAndView registerSeller(@ModelAttribute(name = "model") SellerRegisterBindingModel model,
                                                                   ModelAndView modelAndView) {
        modelAndView.addObject("model", model);
        return view("user/registerSeller", modelAndView);
    }

    @PostMapping("/register/seller")
    public ModelAndView registerSellerConfirm(ModelAndView modelAndView,
                                              @ModelAttribute(name = "model") SellerRegisterBindingModel model, BindingResult bindingResult) {
        userRegisterValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            return view("user/registerSeller", modelAndView);
        }

        SellerServiceModel sellerServiceModel = modelMapper.map(model, SellerServiceModel.class);
        userService.registerUser(sellerServiceModel, "ROLE_SELLER");

        return super.redirect("/login");
    }

    @PageTitle(value = "users.page.title.login")
    @GetMapping("/login")
    public ModelAndView login() {
        return view("user/login");
    }

    @PageTitle(value = "users.page.title.edit")
    @GetMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_ROOT', 'ROLE_SUPPORT', 'ROLE_ORDER_MANAGER')")
    public ModelAndView editInternalUserProfile() {
        return view("profile/edit-internal");
    }

    @PostMapping("/edit")
    @PreAuthorize("hasAnyRole('ROLE_ROOT', 'ROLE_SUPPORT', 'ROLE_ORDER_MANAGER')")
    public ModelAndView editInternalUserProfileConfirm(ModelAndView modelAndView,
                                                       @ModelAttribute(name = "model") BaseUserEditBindingModel model,
                                                       Principal principal, BindingResult bindingResult) {
        model.setUsername(principal.getName());
        baseUserEditValidator.validate(model, bindingResult);
        if(bindingResult.hasErrors()) {
            model.setNewPassword(null);
            model.setConfirmNewPassword(null);
            modelAndView.addObject("model", model);
            return view("profile/edit-internal", modelAndView);
        }
        BaseUserEditServiceModel serviceModel = modelMapper.map(model, BaseUserEditServiceModel.class);
        userService.editInternalUserProfile(serviceModel);

        return view("profile/edit-success");
    }
    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}
