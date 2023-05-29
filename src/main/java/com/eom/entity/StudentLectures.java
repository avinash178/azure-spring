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

import com.eom.enums.Years;

@Entity
@Table(name = "students_lectures")
public class StudentLectures {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long lectureId;
	
	@OneToOne
	@JoinColumn(name = "teacher_lec_ref")
	private TeacherLecture teacherLectureId;
	
	@OneToOne
	@JoinColumn(name = "subject_id")
	private Subjects subject;

	@Column(name = "teacher_name")
	private String teacherName;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UsersEntity userId; // student uid

	@Column(name = "lecture_start")
	private Date start;

	@Column(name = "lecture_end")
	private Date end;

	@OneToOne
	@JoinColumn(name = "department_id")
	private DepartmentEntity departmentData;

	private Years classYear; // year of class for 1,2,3,4

	private boolean attendance; // both for student and teacher
	
	private String notes;

	public StudentLectures() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StudentLectures(TeacherLecture lec) {
		this.subject=lec.getSubject();
		this.teacherName=lec.getTeacherName();
		this.departmentData=lec.getDepartmentData();
		this.start=lec.getStart();
		this.end=lec.getEnd();
		this.classYear=lec.getClassYear();
		this.teacherLectureId=lec;
	}
	
	public StudentLectures(StudentLectures lec) {
		this.teacherName=lec.getTeacherName();
		this.start=lec.getStart();
		this.end=lec.getEnd();
		this.attendance=lec.isAttendance();
		this.departmentData=lec.getDepartmentData();
		this.subject=lec.getSubject();
		this.classYear=lec.getClassYear();
		this.userId=lec.getUserId();
		this.notes=lec.getNotes();
		this.teacherLectureId=lec.getTeacherLectureId();
	}

	public long getLectureId() {
		return lectureId;
	}

	public void setLectureId(long lectureId) {
		this.lectureId = lectureId;
	}

	public TeacherLecture getTeacherLectureId() {
		return teacherLectureId;
	}

	public void setTeacherLectureId(TeacherLecture teacherLectureId) {
		this.teacherLectureId = teacherLectureId;
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

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	

}
