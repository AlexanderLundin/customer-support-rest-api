package com.galvanize.services;

import com.galvanize.entities.Request;
import com.galvanize.repository.JpaRequestDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private JpaRequestDao jpaDao;

    public RequestService (JpaRequestDao jpaDao){
        this.jpaDao = jpaDao;
    }


    // CREATE


    public Request save (Request request){
        return jpaDao.save(request);
    }


    // READ


    public List<Request> findAll() {
        return jpaDao.findAll();
    }

    public Request findById(Long id) {
        if (jpaDao.existsById(id)){
            return jpaDao.findById(id).get();
        }else{
            return null;
        }

    }


    // UPDATE


    public Request updateById(Long id, Request request) {
        if (jpaDao.existsById(id)){
            return jpaDao.save(request);
        }else {
            throw new RuntimeException ("Request not found");
        }
    }


    // DELETE


    public void deleteById(Long id) {
        if (jpaDao.existsById(id)){
            jpaDao.deleteById(id);
        }else {
            throw new RuntimeException ("Request not found");
        }
    }



}
