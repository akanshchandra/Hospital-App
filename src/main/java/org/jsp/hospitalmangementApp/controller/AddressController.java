package org.jsp.hospitalmangementApp.controller;

import java.util.List;

import org.jsp.hospitalmangementApp.dto.Address;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.service.AddressService;
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
@RequestMapping(value = "/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@PostMapping(value = "/{branch_id}")
	public ResponseEntity<ResponseStructure<Address>> save(@RequestBody Address address,@PathVariable int branch_id) {

		return addressService.save(address,branch_id);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<Address>> update(@RequestBody Address address) {
		
		return addressService.update(address);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Address>> findById(@PathVariable int id) {
		
		return addressService.findById(id);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		
		return addressService.deleteById(id);
	}
	
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Address>>> findAll() {
		
		return addressService.findAll();
	}
}
