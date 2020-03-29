package com.galvanize.controllers;

import com.galvanize.entities.Request;
import com.galvanize.entities.RequestNote;
import com.galvanize.entities.RequestStatus;
import com.galvanize.repository.JpaRequestDao;
import com.galvanize.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;


@RestController
@RequestMapping("/api")
public class CustomerController {


    RequestService requestService;


    public CustomerController(RequestService requestService){
        this.requestService = requestService;
    }


    //CREATE


    @PostMapping("/service")
    public Request postServiceRequest(@RequestBody Request request){
        return requestService.save(request);
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
        requestService.save(newRequest);
        model.addAttribute("service", newRequest);
        return new RedirectView("service/" + newRequest.getRequestNumber());
    }

    // READ


    @GetMapping("/service")
    public List<Request> getAllServiceRequests(){
        List<Request> requests = requestService.findAll();
        return requests;
    }

    @GetMapping("/service/{requestNumber}")
    public Request getAllServiceRequestByRequestNumber(@PathVariable long requestNumber){
        Request request = requestService.findById(requestNumber);
        return request;
    }


    //UPDATE


    @PutMapping("/service/{requestNumber}")
    public Request putRequestToAssignedByRequestNumber(@PathVariable long requestNumber, @RequestBody Request request){
        if(request == null){
            request = requestService.findById(requestNumber);
        }
        Request updatedRequest = requestService.updateById(requestNumber, request);
        return updatedRequest;
    }

    @PutMapping("/service/{requestNumber}/status")
    public Request putRequestToResolvedByRequestNumber(@PathVariable long requestNumber, @RequestBody Request request){
        RequestStatus status = RequestStatus.RESOLVED;
        if(request == null){
            request = requestService.findById(requestNumber);
            request.setRequestStatus(status);
        }
        Request updatedRequest = requestService.updateById(requestNumber, request);
        return updatedRequest;
    }
}
