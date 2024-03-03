package org.jsp.hospitalmangementApp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.hospitalmangementApp.dao.AddressDao;
import org.jsp.hospitalmangementApp.dao.BranchDao;
import org.jsp.hospitalmangementApp.dto.Address;
import org.jsp.hospitalmangementApp.dto.Branch;
import org.jsp.hospitalmangementApp.dto.ResponseStructure;
import org.jsp.hospitalmangementApp.exception.AddressExceptions;
import org.jsp.hospitalmangementApp.exception.AdminExceptions;
import org.jsp.hospitalmangementApp.exception.BranchExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;
	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<ResponseStructure<Address>> save(Address address, int branch_id) {

		ResponseStructure<Address> structure = new ResponseStructure<>();
		Optional<Branch> recBranch = branchDao.findById(branch_id);
		
		if(recBranch.isPresent()) {
			
			Branch branch = recBranch.get();
			branch.setAddress(address);
			
			address.setBranch(branch);
		
		structure.setData(addressDao.save(address));
		structure.setMessage("Address saved");
		structure.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
		}
		
		throw new  BranchExceptions("Address not saved as branch id invalid");
	}

	public ResponseEntity<ResponseStructure<Address>> update(Address address) {

		Optional<Address> recAddress = addressDao.findById(address.getId());
		ResponseStructure<Address> structure = new ResponseStructure<>();

		if (recAddress.isPresent()) {

			Address dbAddress = recAddress.get();
		 
			dbAddress.setBuilding_name(address.getBuilding_name());
			dbAddress.setLandmark(address.getLandmark());
			dbAddress.setStreet(address.getStreet());
			dbAddress.setPincode(address.getPincode());
			dbAddress.setCity(address.getCity());
            dbAddress.setState(address.getState());
            dbAddress.setCountry(address.getCountry());
			
			structure.setData(addressDao.save(dbAddress));
			structure.setMessage("Address updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.ACCEPTED);
		}

		throw new AddressExceptions("Address not updated as Invalid Address id");
	}

	public ResponseEntity<ResponseStructure<Address>> findById(int id) {

		Optional<Address> recAddress = addressDao.findById(id);
		ResponseStructure<Address> structure = new ResponseStructure<>();

		if (recAddress.isPresent()) {

			structure.setData(recAddress.get());
			structure.setMessage("Address details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		}

		throw new AddressExceptions("Invalid Address id");
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {

		Optional<Address> recAddress = addressDao.findById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();

		if (recAddress.isPresent()) {

			addressDao.deleteById(id);
			structure.setData("Address found");
			structure.setMessage("Address deleted");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		}

		throw new AddressExceptions("Address not deleted as Invalid Address id");
	}

	public ResponseEntity<ResponseStructure<List<Address>>> findAll() {

		List<Address> addresss = addressDao.findAll();
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();

		if (addresss.size() > 0) {

			structure.setData(addresss);
			structure.setMessage("Address details");
			structure.setStatusCode(HttpStatus.OK.value());

			return new ResponseEntity<ResponseStructure<List<Address>>>(structure, HttpStatus.OK);
		}

		throw new AddressExceptions("No Address Present");
	}
}
