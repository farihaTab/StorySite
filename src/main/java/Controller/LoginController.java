package Controller;

import Controller.Service.UserService;
import Validator.LoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import Model.*;
/**
 * Created by MiNNiE on 28-Apr-17.
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginValidator loginValidator;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {

        model.addAttribute("user", new AccountuserEntity());
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginVerification(@ModelAttribute("user") AccountuserEntity user, BindingResult result) {

        loginValidator.validate(user,result);

        if(result.hasErrors()) {
            return "login";
        }
        this.username = user.getUsername();
        return "redirect:/story";

    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public String logout(@ModelAttribute("user") AccountuserEntity user, BindingResult result) {

        return "redirect:/login";

    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }


}
