package org.jsp.hospitalmangementApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dto.Hospital;
import org.jsp.hospitalmangementApp.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepository  hospitalRepository;

	public Hospital save(Hospital hospital) {

		return hospitalRepository.save(hospital);
	}

	public Optional<Hospital> findById(int id) {

		 return hospitalRepository.findById(id);
	}

	public List<Hospital> findAll() {

		 return hospitalRepository.findAll();
	}

	public boolean deleteById(int id) {

		Optional<Hospital> recHospital =  hospitalRepository.findById(id);

		if (recHospital.isPresent()) {

			 hospitalRepository.delete(recHospital.get());
			return true;
		}
		return false;
	}
}
