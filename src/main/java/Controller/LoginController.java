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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by MiNNiE on 28-Apr-17.
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    LoginValidator loginValidator;


    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model) {

        model.addAttribute("user", new AccountuserEntity());
        return "login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginVerification(@ModelAttribute("user") AccountuserEntity user, BindingResult result, HttpServletRequest request) {

        loginValidator.validate(user,result);
        if(result.hasErrors()) {
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("username",user.getUsername());
        System.out.println("logged in as"+user.getUsername());
        return "redirect:/story/home";

    }


    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpSession session){
        session.setAttribute("username",null);
        System.out.println("logging out ");
        if((String)session.getAttribute("username") == null){
            System.out.println("logout success");
        }
        //session.removeAttribute("username");
        return "redirect:/login";
    }


    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }


}
