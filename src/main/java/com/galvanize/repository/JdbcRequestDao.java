package com.galvanize.repository;

import com.galvanize.entities.Request;
import com.galvanize.entities.RequestNote;
import com.galvanize.entities.RequestStatus;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcRequestDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertRequest;
    private SimpleJdbcInsert insertRequestNote;

    //DB query strings
    private final String FETCH_REQUEST_BY_REQUEST_NUMBER = "select * from customer_requests where request_number = ?";
    private final static String UPDATE_REQUEST_TECH_DATE_STATUS_BY_REQUEST_NUMBER = "update customer_requests set technician = ?, appointment_date = ?, request_status = ? where request_number = ?";

    public JdbcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertRequest = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("customer_requests")
                .usingGeneratedKeyColumns("request_number");
        insertRequestNote = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("request_notes")
                .usingColumns("date_time", "notes", "fk_request_number")
                .usingGeneratedKeyColumns("note_id");
    }


    //CREATE


    public Request save(Request request) {
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(request);
        long finalRequestNumber = insertRequest.executeAndReturnKey(parameters).longValue();
        request.setRequestNumber(finalRequestNumber);
        return request;
    }


    //READ


    public Optional<Request> findById(long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(FETCH_REQUEST_BY_REQUEST_NUMBER,
                    (rs, rowNum) -> new Request(
                            rs.getLong("request_number"),
                            rs.getString("request_date_time"),
                            rs.getString("customer_name"),
                            rs.getString("customer_address"),
                            rs.getString("phone_number"),
                            rs.getString("description"),
                            rs.getString("technician"),
                            rs.getString("appointment_date"),
                            rs.getString("request_status")
                    ),
                    id));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }



    //UPDATE


    public Request updateAssignByRequestNumber(long requestNumber, String technician, String appointmentDate){
        Optional<Request> oRequest;
        try {
            oRequest = Optional.ofNullable(jdbcTemplate.queryForObject(FETCH_REQUEST_BY_REQUEST_NUMBER,
                    (rs, rowNum) -> new Request(
                            rs.getLong("request_number"),
                            rs.getString("request_date_time"),
                            rs.getString("customer_name"),
                            rs.getString("customer_address"),
                            rs.getString("phone_number"),
                            rs.getString("description"),
                            rs.getString("technician"),
                            rs.getString("appointment_date"),
                            rs.getString("request_status")
                    ),
                    requestNumber));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        Request request = oRequest.get();
        request.setTechnician(technician);
        request.setAppointmentDate(appointmentDate);
        request.setRequestStatus(RequestStatus.ASSIGNED);
        String updateQuery = UPDATE_REQUEST_TECH_DATE_STATUS_BY_REQUEST_NUMBER;
        int rowEffected = jdbcTemplate.update(updateQuery, technician, appointmentDate, RequestStatus.ASSIGNED.toString() ,requestNumber);

        return request;
    }


    public Request updateRequestNoteByRequestNumber (long requestNumber, String technician, String appointmentDate, String status, String notes) {
        Optional<Request> oRequest;
        try {
            oRequest = Optional.ofNullable(jdbcTemplate.queryForObject(FETCH_REQUEST_BY_REQUEST_NUMBER,
                    (rs, rowNum) -> new Request(
                            rs.getLong("request_number"),
                            rs.getString("request_date_time"),
                            rs.getString("customer_name"),
                            rs.getString("customer_address"),
                            rs.getString("phone_number"),
                            rs.getString("description"),
                            rs.getString("technician"),
                            rs.getString("appointment_date"),
                            rs.getString("request_status")
                    ),
                    requestNumber));

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
        Request request = oRequest.get();
        request.setTechnician(technician);
        request.setAppointmentDate(appointmentDate);
        request.setRequestStatus(RequestStatus.valueOf(status));
        String updateQuery = UPDATE_REQUEST_TECH_DATE_STATUS_BY_REQUEST_NUMBER;
        jdbcTemplate.update(updateQuery, technician, appointmentDate, status, requestNumber);

        RequestNote requestNote;
        if (notes != "") {
            long note_id = 1L;
            requestNote = new RequestNote(note_id, appointmentDate, notes);
            request.addNote(requestNote);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("node_id", note_id);
            parameters.put("date_time", appointmentDate);
            parameters.put("notes", notes);
            parameters.put("fk_request_number", requestNumber);
            long finalRequestNumber = insertRequestNote.executeAndReturnKey(parameters).longValue();
            request.setRequestNumber(finalRequestNumber);

        }
        return request;
    }


}
