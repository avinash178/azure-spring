package com.eom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "subjects")
public class Subjects {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long sid;

	private String subjectName;
	
	
	@ManyToOne
	private DepartmentEntity departments;
	

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@JsonIgnore
	public DepartmentEntity getDepartments() {
		return departments;
	}

	@JsonIgnore
	public void setDepartments(DepartmentEntity departments) {
		this.departments = departments;
	}

	
}
