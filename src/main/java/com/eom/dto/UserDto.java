package com.eom.dto;

import com.eom.entity.DepartmentEntity;
import com.eom.entity.Subjects;
import com.eom.entity.UserRolesEntity;
import com.eom.enums.Years;

public class UserDto {

	private long userId;

	private String firstName;

	private String lastName;
	
	private String userName;
	
	private String password;

	private String email;

	private long phoneNumber;

	private String location;

	private boolean isLocked;

	private Years yearOfStudy;

	private Subjects subject;

	private DepartmentEntity departmentdata;

	private UserRolesEntity roles;
	
	private String jwtToken;
	
	
	
	

	public String getJwtToken() {
		return jwtToken;
	}



	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}



	public String getUserName() {
		return userName;
	}
	
	

	public String getPassword() {
		return password;
	}


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public Years getYearOfStudy() {
		return yearOfStudy;
	}

	public void setYearOfStudy(Years yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

	public DepartmentEntity getDepartmentdata() {
		return departmentdata;
	}

	public void setDepartmentdata(DepartmentEntity departmentdata) {
		this.departmentdata = departmentdata;
	}

	public UserRolesEntity getRoles() {
		return roles;
	}

	public void setRoles(UserRolesEntity roles) {
		this.roles = roles;
	}
	
	
	

}
