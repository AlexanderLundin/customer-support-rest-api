package com.galvanize.entities;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {
    private SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

    @Test
    public void testCreateRequest() {
        //Setup
        String customerName = "Some Customer";
        String customerAddress = "123 Any Street, SomeCity, ST, 99999";
        String phoneNumber = "111-222-3333";
        String description = "it's broke and I need it fixed!";
        Date date = new Date();
        String expected = sd.format(date);
        //Exercise
        Request request = new Request(customerName, customerAddress, phoneNumber, description);
        String actual = request.getRequestDateTime();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

}