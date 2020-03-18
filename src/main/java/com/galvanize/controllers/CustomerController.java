package com.galvanize.controllers;

import com.galvanize.repository.JpaRequestDao;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    JpaRequestDao jpaRequestDao;

    @GetMapping("/service")
    public String getAllServiceRequests(){
        List<Request> requests = jpaRequestDao.findAll();
        String json = new Gson().toJson(requests);
        return json;
    }
}
