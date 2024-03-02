package org.jsp.hospitalmangementApp.controller;

import java.util.List;

import org.jsp.hospitalmangementApp.dto.Address;
import org.jsp.hospitalmangementApp.dto.Branch;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/branch")
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Branch>> save(@RequestBody Branch branch) {

		return branchService.save(branch);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Branch>> update(@RequestBody Branch branch) {
		
		return branchService.update(branch);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Branch>> findById(@PathVariable int id) {
		
		return branchService.findById(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		
		return branchService.deleteById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Branch>>> findAll() {
		
		return branchService.findAll();
	}

	

}
