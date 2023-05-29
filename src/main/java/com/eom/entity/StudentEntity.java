package com.eom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.eom.enums.Years;

@Entity
@Table(name = "student_data")
public class StudentEntity {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private UsersEntity  userid;
	
	@Transient
	private long libraryNumber;
	
	private Years year;
	
	

	public StudentEntity(UsersEntity userid, Years year) {
		super();
		this.userid = userid;
		this.year = year;
	}

	public StudentEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public UsersEntity getUserid() {
		return userid;
	}

	public void setUserid(UsersEntity userid) {
		this.userid = userid;
	}

	public long getLibraryNumber() {
		return libraryNumber;
	}

	public void setLibraryNumber(long libraryNumber) {
		this.libraryNumber = libraryNumber;
	}

	public Years getYear() {
		return year;
	}

	public void setYear(Years year) {
		this.year = year;
	}

	
	
}
