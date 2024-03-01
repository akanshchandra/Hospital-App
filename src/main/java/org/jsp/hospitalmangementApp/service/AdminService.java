package org.jsp.hospitalmangementApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dao.AdminDao;
import org.jsp.hospitalmangementApp.dto.Admin;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.exception.AdminExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<Admin>> save(Admin admin) {

		ResponseStructure<Admin> structure = new ResponseStructure<>();
		structure.setData(adminDao.save(admin));
		structure.setMessage("Admin saved");
		structure.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Admin>> update(Admin admin) {

		Optional<Admin> recAdmin = adminDao.findById(admin.getId());
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			Admin dbAdmin = recAdmin.get();
			dbAdmin.setName(admin.getName());
			dbAdmin.setEmail(admin.getEmail());
			dbAdmin.setPhone(admin.getPhone());
			dbAdmin.setPassword(admin.getPassword());

			structure.setData(adminDao.save(dbAdmin));
			structure.setMessage("Admin updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.ACCEPTED);
		}

		throw new AdminExceptions("Admin not updated as Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {

		Optional<Admin> recAdmin = adminDao.findById(id);
		ResponseStructure<Admin> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			structure.setData(recAdmin.get());
			structure.setMessage("Admin details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Admin>>(structure, HttpStatus.OK);
		}

		throw new AdminExceptions("Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		Optional<Admin> recAdmin = adminDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();

		if (recAdmin.isPresent()) {

			adminDao.deleteById(id);
			structure.setData("Admin found");
			structure.setMessage("Admin deleted");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}

		throw new AdminExceptions("Admin not deleted as Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<List<Admin>>> findAll() {

		List<Admin> admins = adminDao.findAll();
		ResponseStructure<List<Admin>> structure = new ResponseStructure<>();

		if (admins.size()>0) {

			structure.setData(admins);
			structure.setMessage("Admin details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Admin>>>(structure, HttpStatus.OK);
		}

		throw new AdminExceptions("No Admin Present");
	}
}
