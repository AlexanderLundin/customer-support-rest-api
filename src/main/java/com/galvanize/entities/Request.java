package com.galvanize.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "customer_requests", schema = "customer_api")
public class Request implements Serializable {

    // database columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "requestNumber", nullable = false)
    private long requestNumber;
    @Column(name = "requestDateTime", nullable = false)
    private String requestDateTime;
    @Column(name = "customerName", nullable = false)
    private String customerName;
    @Column(name = "customerAddress", nullable = false)
    private String customerAddress;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "technician")
    private String technician;
    @Column(name = "appointmentDate")
    private String appointmentDate;
    @Column(name = "requestStatus")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<RequestNote> notes;

    // class fields
    private static SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy hh:mm a");


    public Request(){

    }

    public Request(long requestNumber, String requestDateTime, String customerName, String customerAddress, String phoneNumber, String description, String technician, String appointmentDate, String requestStatus) {
        this.requestNumber = requestNumber;
        this.requestDateTime = requestDateTime;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.technician = technician;
        this.appointmentDate = appointmentDate;
        this.requestStatus = Enum.valueOf(RequestStatus.class, requestStatus);
    }

    public Request(String customerName, String customerAddress, String phoneNumber, String description) {
        Date date = new Date();
        this.requestDateTime = sd.format(date);
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.technician = "";
        this.appointmentDate = "";
        this.requestStatus = RequestStatus.ASSIGNED;
    }

    public Long getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(Long requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(String requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public SimpleDateFormat getSd() {
        return sd;
    }

    public void setSd(SimpleDateFormat sd) {
        this.sd = sd;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Set<RequestNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<RequestNote> notes) {
        this.notes = notes;
    }

    public void addNote(RequestNote note){
        this.notes.add(note);
    }
}
