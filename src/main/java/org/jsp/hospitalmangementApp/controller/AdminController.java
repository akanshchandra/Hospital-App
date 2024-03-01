package org.jsp.hospitalmangementApp.controller;

import java.util.List;

import org.jsp.hospitalmangementApp.dto.Admin;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Admin>> save(@RequestBody Admin admin) {

		return adminService.save(admin);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Admin>> update(@RequestBody Admin admin) {

		return adminService.update(admin);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Admin>> findById(int id) {

		return adminService.findById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		return adminService.deleteById(id);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<List<Admin>>> findAll() {

		return adminService.findAll();
	}
}
