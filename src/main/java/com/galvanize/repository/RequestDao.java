package com.galvanize.repository;

import com.galvanize.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestDao extends JpaRepository<Request, Integer> {

}
