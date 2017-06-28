package Controller;


import Controller.Service.UserService;
import Validator.UserValidator;
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
 * Created by MiNNiE on 27-Apr-17.
 */

@Controller
public class SignUpController {


    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;




    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model) {

        //model.addAttribute("userList", userService.getAllUsers());
        return "test";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String allUsers(Model model) {

        if(userService.getAllUsers().isEmpty()) return "redirect:/error";
        model.addAttribute("userList", userService.getAllUsers());

        return "User";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String startRegistration(Model model) {
        AccountuserEntity newUser=new AccountuserEntity();
        model.addAttribute("newUser", newUser);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String SignUp(@ModelAttribute("newUser") AccountuserEntity newUser, BindingResult result) {
        userValidator.validate(newUser,result);

        if(result.hasErrors()) {
            return "signup";
        }

        userService.insertUser(newUser);
        return "redirect:/allstories";}


       // productService.addProduct(newProduct);




    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }




}
