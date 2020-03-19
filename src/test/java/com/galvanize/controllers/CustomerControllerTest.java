package com.galvanize.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    
    @Autowired
    MockMvc mvc;


    @Autowired
    JdbcTemplate jdbcTemplate;


    private static final String CUSTOMER_6 = "Some Customer6";


    @Test
    public void testGetAllServiceRequests() throws Exception {
        //Setup
        String url = "/api/service";
        //Exercise
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(CUSTOMER_6)));
        //Assert
        //Teardown
    }

    @Test
    public void testGetAllServiceRequestsByRequestNumber() throws Exception {
        //Setup
        String url = "/api/service/6";
        //Exercise
        mvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(CUSTOMER_6)));
        //Assert
        //Teardown
    }
}