package com.banking.userfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Created by AmitAgarwal on 4/11/19.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index.html";
    }


}
