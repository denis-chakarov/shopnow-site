package com.intranet.onlineshop.validation.user;

import com.intranet.onlineshop.domain.models.base.BaseUserBindingModel;
import com.intranet.onlineshop.repository.UserRepository;
import com.intranet.onlineshop.validation.ValidationConstants;
import com.intranet.onlineshop.validation.annotation.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@CustomValidator
public class UserRegisterValidator implements Validator {

    private final UserRepository userRepository;

    @Autowired
    public UserRegisterValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BaseUserBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BaseUserBindingModel baseUserBindingModel = (BaseUserBindingModel) o;

        if (this.userRepository.findByUsername(baseUserBindingModel.getUsername()).isPresent()) {
            errors.rejectValue(
                    "username",
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, baseUserBindingModel.getUsername()),
                    String.format(ValidationConstants.USERNAME_ALREADY_EXISTS, baseUserBindingModel.getUsername())
            );
        }

        if (baseUserBindingModel.getUsername().length() < 3 || baseUserBindingModel.getUsername().length() > 10) {
            errors.rejectValue(
                    "username",
                    ValidationConstants.USERNAME_LENGTH,
                    ValidationConstants.USERNAME_LENGTH
            );
        }

        if (!baseUserBindingModel.getPassword().equals(baseUserBindingModel.getConfirmPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }

        if (this.userRepository.findByEmail(baseUserBindingModel.getEmail()).isPresent()) {
            errors.rejectValue(
                    "email",
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, baseUserBindingModel.getEmail()),
                    String.format(ValidationConstants.EMAIL_ALREADY_EXISTS, baseUserBindingModel.getEmail())
            );
        }
    }
}
