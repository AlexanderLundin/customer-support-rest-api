package com.galvanize.controllers;

import com.galvanize.entities.Request;
import com.galvanize.entities.RequestNote;
import com.galvanize.entities.RequestStatus;
import com.galvanize.repository.JdbcRequestDao;
import com.galvanize.repository.JpaRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;


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


    //CREATE


    @PostMapping("/service")
    public Request postServiceRequest(@RequestBody Request request){
        return jdbcRequestDao.save(request);
    }

    //overloaded post endpoint
    @RequestMapping(method = RequestMethod.POST, value = "/service",
            params = {"customerName", "customerAddress", "phoneNumber", "description"})
    public RedirectView officerAdd(@RequestParam String customerName,
                                   @RequestParam String customerAddress,
                                   @RequestParam String phoneNumber,
                                   @RequestParam String description,
                                   Model model) {
        Request newRequest = new Request();
        newRequest.setCustomerName(customerName);
        newRequest.setCustomerAddress(customerAddress);
        newRequest.setPhoneNumber(phoneNumber);
        newRequest.setDescription(description);
        jdbcRequestDao.save(newRequest);
        model.addAttribute("service", newRequest);
        return new RedirectView("service/" + newRequest.getRequestNumber());
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


    //UPDATE


    @PutMapping("/service/{requestNumber}")
    public Request putRequestToAssignedByRequestNumber(@PathVariable long requestNumber, @RequestBody Request request){
        String technician = request.getTechnician();
        String appointmentDate = request.getAppointmentDate();
        Request updatedRequest = jdbcRequestDao.updateAssignByRequestNumber(requestNumber, technician, appointmentDate);
        return updatedRequest;
    }

    @PutMapping("/service/{requestNumber}/status")
    public Request putRequestToResolvedByRequestNumber(@PathVariable long requestNumber, @RequestBody Request request){
        String status = RequestStatus.RESOLVED.toString();
        String technician = request.getTechnician();
        String appointmentDate = request.getAppointmentDate();
        Set<RequestNote> requestNoteSet = request.getNotes();
        Iterator<RequestNote> iterator = requestNoteSet.iterator();
        RequestNote requestNote = null;
        String notes = "";
        if (iterator.hasNext()){
            requestNote = iterator.next();
            notes = requestNote.getNotes();
        }
        Request updatedRequest = jdbcRequestDao.updateRequestNoteByRequestNumber(requestNumber, technician, appointmentDate, status, notes);
        return updatedRequest;
    }
}
