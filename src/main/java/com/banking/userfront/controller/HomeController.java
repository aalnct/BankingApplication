package com.banking.userfront.controller;

import com.banking.userfront.domain.User;
import com.banking.userfront.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by AmitAgarwal on 4/11/19.
 */
@Controller
public class HomeController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }

    @RequestMapping(value = "/signup" , method = RequestMethod.GET)
    public String signUp(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "signup";
    }
    @RequestMapping(value = "/signup" , method = RequestMethod.POST)
    public String signUpPost(@ModelAttribute("user") User user, Model model){
        if(userService.checkUserExist(user.getUsername() , user.getEmail())){
            if(userService.checkUsernameExists(user.getUsername())){
                model.addAttribute("usernameExists", true);
            }
            if(userService.checkEmailExists(user.getEmail())){
                model.addAttribute("emailExists", true);
            }
            return "signup";
        }else{
                userService.save(user);
                return "redirect:/";
        }

    }

}
