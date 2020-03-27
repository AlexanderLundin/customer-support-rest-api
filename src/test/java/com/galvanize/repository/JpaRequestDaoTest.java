package com.galvanize.repository;

import com.galvanize.entities.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
class JpaRequestDaoTest {

    @Autowired
    JpaRequestDao jpaRequestDao;


    //READ


    @Test
    public void testJPARequestGetCount() {
        //Setup
        //Exercise
        long notExpected = 0L;
        long actual = jpaRequestDao.count();
        //Assert
        assertNotEquals(notExpected, actual);
        //Teardown
    }

    @Test
    public void testJPARequestFindAll() {
        //Setup
        //Exercise
        long notExpected = 6L;
        List<Request> requests = jpaRequestDao.findAll();
        long actual = requests.size();
        //Assert
        assertNotEquals(notExpected, actual);
        //Teardown
    }

}