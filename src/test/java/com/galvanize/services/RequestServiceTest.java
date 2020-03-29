package com.galvanize.services;

import com.galvanize.entities.Request;
import com.galvanize.entities.RequestNote;
import com.galvanize.entities.RequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.*;
import javax.transaction.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class RequestServiceTest {


    @Autowired
    RequestService requestService;


    private SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy hh:mm a");


    private RequestNote requestNote;
    private String dateTime;
    private String notes;


    private String requestDateTime;
    private String customerName;
    private String customerAddress;
    private String phoneNumber;
    private String description;
    private String technician;
    private String appointmentDate;
    private RequestStatus requestStatus;
    private Set<RequestNote> noteList = new LinkedHashSet<>();
    private Request request1;
    private Request request2;


    @BeforeEach
    public void setup() {
        //Setup
        Date date = new Date();
        String today = sd.format(date);
        // request note
        notes = "initial request submitted";
        requestNote = new RequestNote(today, notes);
        //request
        requestDateTime = today;
        customerName = "Big Al";
        customerAddress = "Mockingbird Lane";
        phoneNumber = "12345678";
        description = "My sink is RUINT";
        technician = "Bubba";
        appointmentDate = today;
        requestStatus = RequestStatus.UNASSIGNED;
        noteList.add(requestNote);
        request1 = new Request(customerName, customerAddress, phoneNumber, description);
        request2 = new Request(customerName, customerAddress, phoneNumber, description);
        requestService.save(request1);
        requestService.save(request2);
    }


    // CREATE ,  covered by setup


    // READ


    @Test
    public void testFindAllOfficers() {
        //Setup
        int notExpected = 0;
        //Exercise
        List<Request> requests = requestService.findAll();
        int actual = requests.size();
        //Assert
        assertNotEquals(notExpected, actual);
        //Teardown
    }

    @Test
    public void testFindOfficerById() {
        //Setup
        Request expected = request1;
        //Exercise
        Request actual = requestService.findById(request1.getRequestNumber());
        //Assert
        assertEquals(expected.hashCode(), actual.hashCode());
        //Teardown
    }


    // UPDATE

    @Test
    public void testUpdateOfficerById() {
        //Setup
        request1.setCustomerName("New");
        Request expected = request1;
        //Exercise
        Request actual = requestService.updateById(request1.getRequestNumber(), request1);
        //Assert
        assertEquals(expected.hashCode(), actual.hashCode());
        assertEquals(expected.getCustomerName(), actual.getCustomerName());
        //Teardown
    }


    // DELETE

    @Test
    public void testDeleteOfficerById() {
        //Exercise
        requestService.deleteById(request1.getRequestNumber());
        Request actual = requestService.findById(request1.getRequestNumber());
        //Assert
        assertNull(actual);
        //Teardown
    }
}