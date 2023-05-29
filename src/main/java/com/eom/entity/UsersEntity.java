package com.eom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.eom.enums.Years;


@Entity
@Table(name = "users")
public class UsersEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;


	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private String email;

	private long phoneNumber;

	private String location;
	
	private boolean isLocked;
	
	@Transient
	private Years yearOfStudy;
	
	@Transient
	private Subjects subject;

	@ManyToOne
	@JoinColumn(name = "department_id", referencedColumnName = "d_id")
	private DepartmentEntity departmentdata;
	
	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "roleId")
	private UserRolesEntity roles;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
