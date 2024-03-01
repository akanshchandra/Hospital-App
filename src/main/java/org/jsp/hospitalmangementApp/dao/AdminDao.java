package org.jsp.hospitalmangementApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dto.Admin;
import org.jsp.hospitalmangementApp.repository.AdminRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepositroy adminRepositroy;
	
	public Admin save(Admin admin) {
		
		return adminRepositroy.save(admin);
	}
	
	public Optional<Admin> findById(int id){
		
		return adminRepositroy.findById(id);
	}
	
	public List<Admin> findAll(){
		
		return adminRepositroy.findAll();
	}
	
	public boolean deleteById(int id) {
		
		Optional<Admin> recAdmin = adminRepositroy.findById(id);
		
		if(recAdmin.isPresent()) {
			
			adminRepositroy.delete(recAdmin.get());
			return true;
		}
		return false;
	}
	
}
