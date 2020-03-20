package com.galvanize.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.entities.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    @Autowired
    private ObjectMapper objectMapper;


    private static final String CUSTOMER_6 = "Some Customer6";


    // CREATE


    @Test
    public void testPostServiceRequest() throws Exception {
        String body = "{\"requestNumber\":\"1\",\"requestDateTime\":\"03/16/2020 6:07PM\",\"customerName\":\"Me\",\"customerAddress\":\"Some place\",\"phoneNumber\":\"123-456-7891\",\"description\":\"Leaky Roof\"}";
        String url = "/api/service";
        ResultActions resultActions = mvc.perform(post(url)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Me")));

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        Request request = objectMapper.readValue(contentAsString, Request.class);
        //Assert
        //Teardown
    }


    //READ


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


    //UPDATE


    @Test
    public void testUpdateRequestAssignedByRequestNumber() throws Exception {
        //Setup
        String body = "{\"technician\":\"Bob Builder\",\"appointmentDate\":\"01/20/2020\",\"appointmentTime\":\"08:30AM\"}";
        String url = "/api/service/6";
        //Exercise
        mvc.perform(put(url)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bob Builder")));
        //Assert
        //Teardown
    }


    @Test
    public void testUpdateRequestNoteByRequestNumber() throws Exception {
        //Setup
        String body = "{\"technician\":\"Bob Builder\",\"appointmentDate\":\"01/20/2020\",\"appointmentTime\":\"08:30AM\",\"requestStatus\":\"RESOLVED\",\"note\":\"words\"}";
        String url = "/api/service/6/status";
        //Exercise
        mvc.perform(put(url)
                .content(body)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("RESOLVED")));
        //Assert
        //Teardown
    }

}