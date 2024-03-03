package org.jsp.hospitalmangementApp.repository;

import org.jsp.hospitalmangementApp.dto.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {

}
