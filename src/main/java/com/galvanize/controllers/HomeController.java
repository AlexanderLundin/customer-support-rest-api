package com.galvanize.controllers;

import com.galvanize.services.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    RequestService requestService;

    @Value("${spring.application.name}")
    String appName;

    public HomeController (RequestService requestService){
        this.requestService = requestService;
    }

    @RequestMapping("/")
    public ModelAndView redirectHomeToHomePage(ModelMap model) {
        return new ModelAndView("redirect:/home", model);
    }


    @RequestMapping("/home")
    public Model showHomePage(Model model){
        model.addAttribute("appName", appName);
        model.addAttribute("officers", requestService.findAll());
        return model;
    }
}
