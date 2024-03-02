package org.jsp.hospitalmangementApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dao.BranchDao;
import org.jsp.hospitalmangementApp.dto.Admin;
import org.jsp.hospitalmangementApp.dto.Branch;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.exception.AdminExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;
	public ResponseEntity<ResponseStructure<Branch>> save(Branch admin) {

		ResponseStructure<Branch> structure = new ResponseStructure<>();
		structure.setData(branchDao.save(admin));
		structure.setMessage("Branch saved");
		structure.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Branch>> update(Branch branch) {

		Optional<Branch> recbranch = branchDao.findById(branch.getId());
		ResponseStructure<Branch> structure = new ResponseStructure<>();

		if (recbranch.isPresent()) {

			Branch dbBranch = recbranch.get();
			dbBranch.setName(branch.getName());
			dbBranch.setEmail(branch.getEmail());
			dbBranch.setPhone(branch.getPhone());
			

			structure.setData(branchDao.save(dbBranch));
			structure.setMessage("Branch updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.ACCEPTED);
		}

		throw new AdminExceptions("Admin not updated as Invalid Admin id");
	}

	public ResponseEntity<ResponseStructure<Branch>> findById(int id) {

		Optional<Branch> recBranch = branchDao.findById(id);
		ResponseStructure<Branch> structure = new ResponseStructure<>();

		if (recBranch.isPresent()) {

			structure.setData(recBranch.get());
			structure.setMessage("Branch details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Branch>>(structure, HttpStatus.OK);
		}

		throw new AdminExceptions("Invalid Branch id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		Optional<Branch> recBranch = branchDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();

		if (recBranch.isPresent()) {

			branchDao.deleteById(id);
			structure.setData("Branch found");
			structure.setMessage("Branch deleted");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}

		throw new AdminExceptions("Branch not deleted as Invalid Branch id");
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> findAll() {

		List<Branch> branch = branchDao.findAll();
		ResponseStructure<List<Branch>> structure = new ResponseStructure<>();

		if (branch.size()>0) {

			structure.setData(branch);
			structure.setMessage("Admin details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Branch>>>(structure, HttpStatus.OK);
		}

		throw new AdminExceptions("No Admin Present");
	}

}
