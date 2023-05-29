package com.eom.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "department_names")
public class DepartmentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long d_id;
	
	private String departmentName;
	
	
	@OneToMany(mappedBy = "departments")
	private List<Subjects> subjects;

	

	public long getD_id() {
		return d_id;
	}

	public void setD_id(long d_id) {
		this.d_id = d_id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@JsonIgnore
	public List<Subjects> getSubjects() {
		return subjects;
	}

	@JsonIgnore
	public void setSubjects(List<Subjects> subjects) {
		this.subjects = subjects;
	}

	
	
	
	
	
	
}
