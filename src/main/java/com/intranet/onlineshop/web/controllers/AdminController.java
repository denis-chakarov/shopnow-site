package com.intranet.onlineshop.web.controllers;

import com.intranet.onlineshop.domain.models.base.BaseUserBindingModel;
import com.intranet.onlineshop.domain.models.binding.CategoryBindingModel;
import com.intranet.onlineshop.domain.models.binding.UserActivityInfoBindingModel;
import com.intranet.onlineshop.domain.models.service.CategoryServiceModel;
import com.intranet.onlineshop.domain.models.service.UserActivityServiceModel;
import com.intranet.onlineshop.domain.models.service.UserServiceModel;
import com.intranet.onlineshop.domain.models.view.CategoryViewModel;
import com.intranet.onlineshop.service.CategoryService;
import com.intranet.onlineshop.service.UserActivityService;
import com.intranet.onlineshop.service.UserService;
import com.intranet.onlineshop.validation.user.UserRegisterValidator;
import com.intranet.onlineshop.web.annotations.ActionLogger;
import com.intranet.onlineshop.web.annotations.PageTitle;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {

    private final UserService userService;
    private final UserActivityService userActivityService;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;
    private final UserRegisterValidator userRegisterValidator;


    @Autowired
    public AdminController(UserService userService, UserActivityService userActivityService, ModelMapper modelMapper, CategoryService categoryService, UserRegisterValidator userRegisterValidator) {
        this.userService = userService;
        this.userActivityService = userActivityService;
        this.modelMapper = modelMapper;
        this.categoryService = categoryService;
        this.userRegisterValidator = userRegisterValidator;
    }


    @PageTitle(value = "admin.page.title.register")
    @ActionLogger(value = "admin.register.user.log.get")
    @GetMapping("/register")
    public ModelAndView registerUser(ModelAndView modelAndView,
                             @ModelAttribute(name = "model")BaseUserBindingModel model) {
        modelAndView.addObject("model", model);
        return view("admin/registerUser", modelAndView);
    }

    @ActionLogger(value = "admin.register.user.log.post")
    @PostMapping("/register")
    public ModelAndView registerUserConfirm(ModelAndView modelAndView,
                                            @ModelAttribute(name = "model") BaseUserBindingModel model,
                                            BindingResult bindingResult) {
        userRegisterValidator.validate(model, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setPassword(null);
            model.setConfirmPassword(null);
            modelAndView.addObject("model", model);
            return view("admin/registerUser", modelAndView);
        }
        UserServiceModel userServiceModel = modelMapper.map(model, UserServiceModel.class);
        userService.registerUser(userServiceModel, model.getRole());
        return redirect("/home");
    }

    @PageTitle(value = "admin.page.title.categories")
    @ActionLogger(value = "admin.categories.log.get")
    @GetMapping("/categories")
    public ModelAndView showAllCategories(ModelAndView modelAndView) {
        List<CategoryViewModel> categories = categoryService.findAllCategories()
                .stream()
                .map(c -> modelMapper.map(c, CategoryViewModel.class))
                .collect(Collectors.toList());
        modelAndView.addObject("categories", categories);

        return view("admin/categories", modelAndView);
    }

    @PageTitle(value = "admin.page.title.activities")
    @GetMapping("/activities/all")
    public ModelAndView showActivitiesLog(ModelAndView modelAndView) {
        List<UserActivityServiceModel> activities = userActivityService.findAllActivities();
        modelAndView.addObject("activities", activities);


        return view("admin/activitiesLog", modelAndView);
    }

    @GetMapping("/activities/user")
    public @ResponseBody List<UserActivityServiceModel> showActivitiesLogForUser(
            @ModelAttribute(name = "model") UserActivityInfoBindingModel model) {

        return userActivityService.findAllActivitiesForUser(model.getUsername());
    }

    @PageTitle(value = "admin.page.title.categories.delete")
    @GetMapping("/categories/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable String id) {
         categoryService.deleteCategory(id);
         return redirect("/admin/categories");
    }

    @PageTitle(value = "admin.page.title.categories.edit")
    @GetMapping("categories/edit/{id}")
    public ModelAndView editCategory(@PathVariable String id, ModelAndView modelAndView) {
        CategoryServiceModel serviceModel = categoryService.findCategoryById(id);
        CategoryBindingModel category = modelMapper.map(serviceModel, CategoryBindingModel.class);
        modelAndView.addObject("categoryId", id);
        modelAndView.addObject("category", category);

        return view("admin/edit-category", modelAndView);
    }
    @PostMapping("categories/edit/{id}")
    public ModelAndView editCategory(@PathVariable String id, @ModelAttribute(name = "model") CategoryBindingModel model) {

        CategoryServiceModel category = modelMapper.map(model, CategoryServiceModel.class);
        categoryService.editCategory(id, category);

        return redirect("/admin/categories");
    }
}
