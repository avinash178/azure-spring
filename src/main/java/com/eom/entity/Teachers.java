package com.eom.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "teachers_data")
public class Teachers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private UsersEntity userId;
	
	@OneToOne
	@JoinColumn(name =  "subject_id")
	private Subjects subjectName;
	
	@OneToOne
	@JoinColumn(name="department_id")
	private DepartmentEntity departmentId;
	

	public Teachers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Teachers(UsersEntity userId, Subjects subjectName,DepartmentEntity dId) {
		super();
		this.userId = userId;
		this.subjectName = subjectName;
		this.departmentId=dId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public UsersEntity getUserId() {
		return userId;
	}

	public void setUserId(UsersEntity userId) {
		this.userId = userId;
	}

	public Subjects getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(Subjects subjectName) {
		this.subjectName = subjectName;
	}

	public DepartmentEntity getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(DepartmentEntity departmentId) {
		this.departmentId = departmentId;
	}
	
	
}
