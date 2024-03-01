package org.jsp.hospitalmangementApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dto.Address;
import org.jsp.hospitalmangementApp.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepository addressRepository;
	
	
	public Address save(Address address) {
		
		return addressRepository.save(address);
	}
	
	public Optional<Address> findById(int id){
		
		return addressRepository.findById(id);
	}
	
	public List<Address> findAll(){
		
		return addressRepository.findAll();
	}
	
	public boolean deleteById(int id) {
		
		Optional<Address> recAddress = addressRepository.findById(id);
		
		if(recAddress.isPresent()) {
			
			addressRepository.delete(recAddress.get());
			return true;
		}
		return false;
	}
}
