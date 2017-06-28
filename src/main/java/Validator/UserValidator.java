package Validator;

import Controller.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import Model.*;
@Component
public class UserValidator implements Validator {

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
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "NotEmpty");

        if ((user.getUsername().length()>0 && user.getUsername().length() < 6) || user.getUsername().length() > 20) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        else if(userService.findByUsername(user.getUsername())) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }


        if ((user.getPassword().length()>0 && user.getPassword().length() < 8) || user.getPassword().length() > 20) {
            errors.rejectValue("password", "Size.userForm.password");
        }




        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(user.getEmail());
        if ((user.getEmail().length()>0 &&user.getEmail().length() < 8) || user.getEmail().length() > 30) {
            errors.rejectValue("email", "Size.userForm.email");
        }
        else if(!m.matches()){ errors.rejectValue("email", "Size.userForm.email");}

        else if (userService.findByEmail(user.getEmail())) {
            errors.rejectValue("email", "Duplicate.userForm.email");
        }




    }
}
