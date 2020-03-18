package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    
    @Autowired
    MockMvc mvc;

    @Test
    public void testGetAllServiceRequests() throws Exception {
        //Setup
        String url = "/api/service";
        //Exercise
        mvc.perform(get(url))
                //.andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("rob.wing@galvanize.com")))
//                .andExpect(jsonPath("$.age").value(57));
        //Assert
        //Teardown
        //Teardown
    }
}