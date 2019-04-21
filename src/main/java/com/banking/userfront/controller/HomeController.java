package com.banking.userfront.controller;

import com.banking.userfront.domain.PrimaryAccount;
import com.banking.userfront.domain.SavingsAccount;
import com.banking.userfront.domain.User;
import com.banking.userfront.domain.security.UserRole;
import com.banking.userfront.service.RoleService;
import com.banking.userfront.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by AmitAgarwal on 4/11/19.
 */
@Controller
public class HomeController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RoleService roleService;

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

            Set<UserRole> userRoles = new HashSet<>();
            userRoles.add(new UserRole(user, roleService.findByName("ROLE_USER")));

            userService.createUser(user, userRoles);
            return "redirect:/";
        }

    }

    @RequestMapping("/userFront")
    public String userFront(Principal principal, Model model){
        User user = userService.findByUserName(principal.getName());
        PrimaryAccount primaryAccount = user.getPrimaryAccount();
        SavingsAccount savingsAccount = user.getSavingsAccount();
        model.addAttribute("primaryAccount", primaryAccount);
        model.addAttribute("savingsAccount", savingsAccount);
        return "userFront";
    }

}
