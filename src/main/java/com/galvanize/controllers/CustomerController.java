package com.galvanize.controllers;

import com.galvanize.entities.Request;
import com.galvanize.repository.JdbcRequestDao;
import com.galvanize.repository.JpaRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    JdbcRequestDao jdbcRequestDao;
    JpaRequestDao jpaRequestDao;


    public CustomerController(JdbcTemplate jdbcTemplate, JpaRequestDao jpaRequestDao){
        jdbcRequestDao = new JdbcRequestDao(jdbcTemplate);
        this.jpaRequestDao = jpaRequestDao;
    }
    // READ


    @GetMapping("/service")
    public List<Request> getAllServiceRequests(){
        List<Request> requests = jpaRequestDao.findAll();
        return requests;
    }

    @GetMapping("/service/{requestNumber}")
    public Request getAllServiceRequestByRequestNumber(@PathVariable long requestNumber){
        Optional<Request> oRequest = jdbcRequestDao.findById(requestNumber);
        Request request = oRequest.get();
        return request;
    }
}
