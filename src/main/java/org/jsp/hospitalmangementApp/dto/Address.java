package org.jsp.hospitalmangementApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String street;
	@Column(nullable = false)
	private String city;
	@Column(nullable = false)
	private String state;
	@Column(nullable = false)
	private String country;
	@Column(nullable = false)
	private int pincode;
	@Column(nullable = false)
	private String landmark;
	@Column(nullable = false)
	private String building_name;
	@OneToOne
	@JoinColumn(name = "branch_id")
	@JsonIgnore
	private Branch branch;

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
	
}
