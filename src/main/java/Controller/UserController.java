package Controller;

/**
 * Created by MiNNiE on 16-May-17.
 */
import Controller.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Model.*;

/**
 * Created by MiNNiE on 28-Apr-17.
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    private String username = "fariha";//loginController.getUsername();

    @RequestMapping(value = "/userprofile", method = RequestMethod.GET)
    public String userprofile(Model model) {


        AccountuserEntity user=new AccountuserEntity();
        String username="tamanna";
        userService.getUserProfile(username);
        model.addAttribute("user",user);
        return "userprofile";
    }

}
