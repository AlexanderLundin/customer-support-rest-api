package com.galvanize.repository;

import com.galvanize.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRequestDao extends JpaRepository <Request, Long> {
}
