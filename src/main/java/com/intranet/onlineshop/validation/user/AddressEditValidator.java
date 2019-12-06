package com.intranet.onlineshop.validation.user;

import com.intranet.onlineshop.repository.UserRepository;
import com.intranet.onlineshop.validation.annotation.CustomValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@CustomValidator
public class AddressEditValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
