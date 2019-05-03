package com.banking.userfront.controller;

import com.banking.userfront.domain.User;
import com.banking.userfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

/**
 * Created by AmitAgarwal on 4/24/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Principal principal, Model model){
        User user = userService.findByUserName(principal.getName());
        model.addAttribute("user",user);
        return "profile";
    }
    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public String profilePost(@ModelAttribute("user")User newUser, Model model){
        User user_add = userService.findByUserName(newUser.getUsername());
        User user = userService.addUser(user_add,newUser);
        model.addAttribute("user",user);

        userService.saveUser(user);

        return "profile";
    }
}
