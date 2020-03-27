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
class JdbcRequestDaoTest {

    @Autowired
    JdbcRequestDao jbdcRequestDao;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setup() {
        //Setup
        jbdcRequestDao = new JdbcRequestDao(jdbcTemplate);
    }


    //CREATE


    @Test
    public void testJbdcCreate() {
        //Setup
        String customerName = "Sample customer";
        String customerAddress = "123 Any Street, SomeCity, ST, 99999";
        String phoneNumber = "111-222-3333";
        String description = "it's broke and I need it fixed!";
        Request request = new Request(customerName, customerAddress, phoneNumber, description);
        //Exercise
        String expected = customerName;
        request = jbdcRequestDao.save(request);
        String actual = request.getCustomerName();
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


    // UPDATE


    @Test
    public void testJdbcUpdateAssign() {
        //Setup
        //Exercise
        long requestNumber = 1L;
        String technician = "new tech";
        String appointmentDate = "03/17/2020 10:00AM";
        Request request = jbdcRequestDao.updateAssignByRequestNumber(requestNumber, technician, appointmentDate);
        String actual = request.getAppointmentDate();
        String expected = appointmentDate;
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

    @Test
    public void testJdbcUpdateRequestNote() {
        //Setup
        //Exercise
        long requestNumber = 1L;
        String technician = "new tech";
        String appointmentDate = "03/17/2020 10:00AM";
        String status = "RESOLVED";
        String notes = "Some notes2";
        Request request = jbdcRequestDao.updateRequestNoteByRequestNumber(requestNumber, technician, appointmentDate, status, notes);
        String actual = request.getAppointmentDate();
        String expected = appointmentDate;
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }
}