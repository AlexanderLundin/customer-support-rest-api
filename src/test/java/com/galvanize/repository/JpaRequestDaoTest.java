package com.galvanize.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaRequestDaoTest {

    @Autowired
    JpaRequestDao jpaRequestDao;


    @Test
    public void testJPAGetRequestCount() {
        //Setup
        //Exercise
        long expected = 2L;
        long actual = jpaRequestDao.count();
        //Assert
        assertEquals(expected, actual);
        //Teardown
    }

}