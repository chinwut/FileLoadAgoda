package com.springapp.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class HelloController {

    @RequestMapping(value = "/")
    public String welcomeMessage(ModelMap model) {
        model.addAttribute("message", "test");
        return "index";
    }

}