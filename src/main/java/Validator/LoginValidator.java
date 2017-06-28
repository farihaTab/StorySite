package Validator;

import Controller.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import Model.*;
@Component
public class LoginValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return AccountuserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AccountuserEntity user = (AccountuserEntity) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");

        if (!userService.findUser(user.getUsername(),user.getPassword())) {
            errors.rejectValue("username", "Login.Error");
        }
        /*if(userService.getUser(new User(user.getUsername(),user.getPassword())))
        {
            errors.rejectValue("username", "Login.Error");
        }*/
    }
}
