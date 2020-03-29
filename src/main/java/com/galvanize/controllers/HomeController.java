//package com.galvanize.controllers;
//
//import com.galvanize.repository.JpaRequestDao;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.ui.Model;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//@RestController
//public class HomeController {
//
//    private JdbcRequestDao jdbcRequestDao;
//    private JpaRequestDao jpaRequestDao;
//
//    @Value("${spring.application.name}")
//    String appName;
//
//    public HomeController(JdbcTemplate jdbcTemplate, JpaRequestDao jpaRequestDao){
//        this.jdbcRequestDao = new JdbcRequestDao(jdbcTemplate);
//        this.jpaRequestDao = jpaRequestDao;
//    }
//
//    @RequestMapping("/")
//    public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
//        return new ModelAndView("redirect:/home", model);
//    }
//
//    @RequestMapping("/home")
//    public Model showHomeTemplate(Model model){
//        model.addAttribute("appName", appName);
//        model.addAttribute("requests", jpaRequestDao.findAll());
//        return model;
//    }
//}
