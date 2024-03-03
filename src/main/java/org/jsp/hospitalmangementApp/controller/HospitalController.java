package org.jsp.hospitalmangementApp.controller;

import java.util.List;

import org.jsp.hospitalmangementApp.dto.Hospital;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.service.HospitalServices;
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
@RequestMapping(value = "/hospital")
public class HospitalController {

	@Autowired
	private HospitalServices hospitalServices;
	
	@PostMapping(value = "/{admin_id}")
	public ResponseEntity<ResponseStructure<Hospital>> save(@RequestBody Hospital hospital, @PathVariable(name = "admin_id")int admin_id) {
		
		return hospitalServices.save(hospital, admin_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Hospital>> update(@RequestBody Hospital hospital) {
		
		return hospitalServices.update(hospital);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Hospital>> findById(@PathVariable(name = "id") int id) {
		
		return hospitalServices.findById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Hospital>>> findAll() {
		
		return hospitalServices.findAll();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable(name = "id") int id) {
		
		return hospitalServices.deleteById(id);
	}
}
