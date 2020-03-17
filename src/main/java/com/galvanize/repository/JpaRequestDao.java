package com.galvanize.repository;

import com.galvanize.entities.Request;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaRequestDao implements RequestDao {

    @PersistenceContext
    EntityManager em;

    private final static String REQUEST_FIND_ALL = "select r from Request r";


    // READ


    @Override
    public long count() {
        Query query = em.createQuery(REQUEST_FIND_ALL);
        List<Request> Requests = (List<Request>) query.getResultList();
        return Requests.size();
    }


    
    // required by interface implementation

    @Override
    public List<Request> findAll() {
        return null;
    }

    @Override
    public List<Request> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Request> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Request> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Request entity) {

    }

    @Override
    public void deleteAll(Iterable<? extends Request> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Request> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Request> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Request> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Request> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Request> entities) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Request getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Request> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Request> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Request> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Request> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Request> long count(Example<S> example) {
        return 0;
    }
    
    @Override
    public <S extends Request> boolean exists(Example<S> example) {
        return false;
    }
}
