package com.intranet.onlineshop.validation.user;

import com.intranet.onlineshop.domain.entities.User;
import com.intranet.onlineshop.domain.models.binding.BaseUserEditBindingModel;
import com.intranet.onlineshop.repository.UserRepository;
import com.intranet.onlineshop.validation.ValidationConstants;
import com.intranet.onlineshop.validation.annotation.CustomValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@CustomValidator
public class BaseUserEditValidator implements Validator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public BaseUserEditValidator(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BaseUserEditBindingModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BaseUserEditBindingModel baseUserEditBindingModel = (BaseUserEditBindingModel) o;

        User user = this.userRepository.findByUsername(baseUserEditBindingModel.getUsername()).orElse(null);

        if (!bCryptPasswordEncoder.matches(baseUserEditBindingModel.getOldPassword(), user.getPassword())) {
            errors.rejectValue(
                    "oldPassword",
                    ValidationConstants.WRONG_PASSWORD,
                    ValidationConstants.WRONG_PASSWORD
            );
        }
        if(baseUserEditBindingModel.getNewPassword().equals(baseUserEditBindingModel.getOldPassword())) {
            errors.rejectValue("samePassword",
                    ValidationConstants.SAME_PASSWORD);
        }
        if (baseUserEditBindingModel.getNewPassword() != null
                && !baseUserEditBindingModel.getNewPassword().equals(baseUserEditBindingModel.getConfirmNewPassword())) {
            errors.rejectValue(
                    "password",
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH,
                    ValidationConstants.PASSWORDS_DO_NOT_MATCH
            );
        }
    }
}
