package com.galvanize.repository;

import com.galvanize.entities.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JbdcRequestDaoTest {

    @Autowired
    JbdcRequestDao jbdcRequestDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        //Setup
        jbdcRequestDao = new JbdcRequestDao(jdbcTemplate);
    }


    //CREATE


    @Test
    public void testJbdcCreate() {
        //Setup
        long expected = 7L;
        String customerName = "Some Customer";
        String customerAddress = "123 Any Street, SomeCity, ST, 99999";
        String phoneNumber = "111-222-3333";
        String description = "it's broke and I need it fixed!";
        Request request = new Request(customerName, customerAddress, phoneNumber, description);
        request.setRequestNumber(expected);
        //Exercise
        request = jbdcRequestDao.save(request);
        Long actual = request.getRequestNumber();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }


    //READ


    @Test
    public void testJbdcRequestFindById() {
        //Setup
        //Exercise
        long expected = 1L;
        Optional<Request> oRequest = jbdcRequestDao.findById(expected);
        //Assert
        assertTrue(oRequest.isPresent());
        Request request = (Request)oRequest.get();
        assertEquals(expected, request.getRequestNumber());
        //Teardown
    }
}