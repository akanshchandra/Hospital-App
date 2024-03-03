package org.jsp.hospitalmangementApp.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int yoe;
	@Column(nullable = false, unique = true)
	private String gst;
	@ManyToOne
	@JoinColumn(name = "admin_id")
	private Admin admin;

	@OneToMany(mappedBy = "hospital")
	@JsonIgnore
	private List<Branch> branch;

	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYoe() {
		return yoe;
	}

	public void setYoe(int yoe) {
		this.yoe = yoe;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
