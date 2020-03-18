package com.galvanize.repository;

import com.galvanize.entities.Request;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JbdcRequestDao {

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertRequest;

    //DB query strings
    private final String FETCH_REQUEST_BY_ID = "select * from customer_requests where requestNumber = ?";

    public JbdcRequestDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        insertRequest = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("customer_requests")
                .usingGeneratedKeyColumns("requestNumber");

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
            return Optional.ofNullable(jdbcTemplate.queryForObject(FETCH_REQUEST_BY_ID,
                    (rs, rowNum) -> new Request(
                            rs.getLong("requestNumber"),
                            rs.getString("requestDateTime"),
                            rs.getString("customerName"),
                            rs.getString("customerAddress"),
                            rs.getString("phoneNumber"),
                            rs.getString("description"),
                            rs.getString("technician"),
                            rs.getString("appointmentDate"),
                            rs.getString("requestStatus")
                    ),
                    id));

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

    }

}
