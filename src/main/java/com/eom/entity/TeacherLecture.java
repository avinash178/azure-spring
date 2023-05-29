package com.eom.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.eom.enums.LectureStatus;
import com.eom.enums.Years;

@Entity
@Table(name = "teachers_lectures")
public class TeacherLecture {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long lectureId;

	@OneToOne
	@JoinColumn(name = "subject_id")
	private Subjects subject;

	@Column(name = "teacher_name")
	private String teacherName;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UsersEntity userId; // so only that teacher can get his own lectures list

	@Column(name = "lecture_start")
	private Date start;

	@Column(name = "lecture_end")
	private Date end;

	@OneToOne
	@JoinColumn(name = "department_data")
	private DepartmentEntity departmentData;
	

	@Column(name = "class_year")
	private Years classYear; // year of class for 0,1,2,3

	@Column(name = "lecture_status")
	private LectureStatus lectureStatus; //

	private String notes; //

	@Column(name = "request_for_other")
	private boolean isRequesetForOther;
	
	@Transient
	private long assingTeacherUidNumber;

	

	
	

	public TeacherLecture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TeacherLecture(TeacherLecture lec) {
		this.subject=lec.getSubject();
		this.start=lec.getStart();
		this.end=lec.getEnd();
		this.departmentData=lec.getDepartmentData();
		this.classYear=lec.getClassYear();
		this.lectureStatus=lec.getLectureStatus();
		
	}

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

	public Subjects getSubject() {
		return subject;
	}

	public void setSubject(Subjects subject) {
		this.subject = subject;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}


	public UsersEntity getUserId() {
		return userId;
	}

	public void setUserId(UsersEntity userId) {
		this.userId = userId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	

	public DepartmentEntity getDepartmentData() {
		return departmentData;
	}

	public void setDepartmentData(DepartmentEntity departmentData) {
		this.departmentData = departmentData;
	}

	

	public Years getClassYear() {
		return classYear;
	}

	public void setClassYear(Years classYear) {
		this.classYear = classYear;
	}

	

	public LectureStatus getLectureStatus() {
		return lectureStatus;
	}

	public void setLectureStatus(LectureStatus lectureStatus) {
		this.lectureStatus = lectureStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isRequesetForOther() {
		return isRequesetForOther;
	}

	public void setRequesetForOther(boolean isRequesetForOther) {
		this.isRequesetForOther = isRequesetForOther;
	}

	

	public long getAssingTeacherUidNumber() {
		return assingTeacherUidNumber;
	}

	public void setAssingTeacherUidNumber(long assingTeacherUidNumber) {
		this.assingTeacherUidNumber = assingTeacherUidNumber;
	}

	
	
	
	

	
}
