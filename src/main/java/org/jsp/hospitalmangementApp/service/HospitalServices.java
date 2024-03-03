package org.jsp.hospitalmangementApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dao.AdminDao;
import org.jsp.hospitalmangementApp.dao.HospitalDao;
import org.jsp.hospitalmangementApp.dao.HospitalDao;
import org.jsp.hospitalmangementApp.dto.Admin;
import org.jsp.hospitalmangementApp.dto.Hospital;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.exception.AdminExceptions;
import org.jsp.hospitalmangementApp.exception.HospitalExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HospitalServices {

	@Autowired
	private HospitalDao hospitalDao;
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Hospital>> save(Hospital hospital, int admin_id) {

		Optional<Admin> recAdmin = adminDao.findById(admin_id);

		if (recAdmin.isPresent()) {

			ResponseStructure<Hospital> structure = new ResponseStructure<>();

			Admin admin = recAdmin.get();
			admin.getHospitals().add(hospital);

			hospital.setAdmin(admin);

			structure.setData(hospitalDao.save(hospital));
			structure.setMessage("Hospital saved");
			structure.setStatusCode(HttpStatus.CREATED.value());

			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.CREATED);
		}

		throw new AdminExceptions("Hospital not found as invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<Hospital>> update(Hospital hospital) {

		Optional<Hospital> recHospital = hospitalDao.findById(hospital.getId());
		ResponseStructure<Hospital> structure = new ResponseStructure<>();

		if (recHospital.isPresent()) {

			Hospital dbHospital = recHospital.get();
			dbHospital.setName(hospital.getName());
			dbHospital.setGst(hospital.getGst());
			dbHospital.setYoe(hospital.getYoe());

			structure.setData(hospitalDao.save(dbHospital));
			structure.setMessage("Hospital updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.ACCEPTED);
		}

		throw new HospitalExceptions("Hospital not updated as Invalid Hospital id");
	}

	public ResponseEntity<ResponseStructure<Hospital>> findById(int id) {

		Optional<Hospital> recHospital = hospitalDao.findById(id);
		ResponseStructure<Hospital> structure = new ResponseStructure<>();

		if (recHospital.isPresent()) {

			structure.setData(recHospital.get());
			structure.setMessage("Hospital details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Hospital>>(structure, HttpStatus.OK);
		}

		throw new HospitalExceptions("Invalid Hospital id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		Optional<Hospital> recHospital = hospitalDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();

		if (recHospital.isPresent()) {

			hospitalDao.deleteById(id);
			structure.setData("Hospital found");
			structure.setMessage("Hospital deleted");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}

		throw new HospitalExceptions("Hospital not deleted as Invalid Hospital id");
	}

	public ResponseEntity<ResponseStructure<List<Hospital>>> findAll() {

		List<Hospital> hsopitals = hospitalDao.findAll();
		ResponseStructure<List<Hospital>> structure = new ResponseStructure<>();

		if (hsopitals.size() > 0) {

			structure.setData(hsopitals);
			structure.setMessage("Hospital details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Hospital>>>(structure, HttpStatus.OK);
		}

		throw new HospitalExceptions("No Hospital Present");
	}
}
