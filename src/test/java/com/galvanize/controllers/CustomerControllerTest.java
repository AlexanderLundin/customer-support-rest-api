package com.galvanize.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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