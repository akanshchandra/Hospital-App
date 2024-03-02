package org.jsp.hospitalmangementApp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dto.Branch;
import org.jsp.hospitalmangementApp.repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepository branchRepository;
	
	public Branch save(Branch branch) {
		return branchRepository.save(branch);
		
	}
	public Optional<Branch> findById(int id){
		return branchRepository.findById(id);

	}
	public boolean deleteById(int id) {
		Optional<Branch> result = branchRepository.findById(id);
		if(!result.isEmpty()) {
			branchRepository.delete(result.get());
			return true;
		}
		return false;
	}
	public List<Branch> findAll(){
		return branchRepository.findAll();
	}

}
