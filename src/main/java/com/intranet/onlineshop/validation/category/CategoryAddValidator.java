package com.intranet.onlineshop.validation.category;

import com.intranet.onlineshop.domain.models.binding.CategoryBindingModel;
import com.intranet.onlineshop.repository.CategoryRepository;
import com.intranet.onlineshop.validation.ValidationConstants;
import com.intranet.onlineshop.validation.annotation.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;

@CustomValidator
public class CategoryAddValidator implements org.springframework.validation.Validator {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryAddValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryBindingModel categoryAddBindingModel = (CategoryBindingModel) o;

        if (categoryAddBindingModel.getName().length() < 3) {
            errors.rejectValue(
                    "name",
                    ValidationConstants.NAME_LENGTH,
                    ValidationConstants.NAME_LENGTH
            );
        }

        if (this.categoryRepository.findByName(categoryAddBindingModel.getName()).isPresent()) {
            errors.rejectValue(
                    "name",
                    String.format(ValidationConstants.NAME_ALREADY_EXISTS, "Category", categoryAddBindingModel.getName()),
                    String.format(ValidationConstants.NAME_ALREADY_EXISTS, "Category", categoryAddBindingModel.getName())
            );
        }
    }
}
