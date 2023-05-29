package com.eom.dto;

import java.util.Date;

import com.eom.entity.AssignedLectures;
import com.eom.entity.DepartmentEntity;
import com.eom.entity.Subjects;
import com.eom.entity.TeacherLecture;
import com.eom.enums.LectureStatus;
import com.eom.enums.Years;

public class LecturesDto {

	private long lectureId;

	private Subjects subject;

	private String teacherName;

	private long userId;

	private Date start;

	private Date end;

	private DepartmentEntity departmentData;

	private Years classYear; // year of class for 0,1,2,3

	private LectureStatus lectureStatus; //

	private String notes; //

	private boolean isRequesetForOther;

	private long assingTeacherUidNumber;
	
	private boolean attendance; // both for student and teacher
	
	
	private TeacherLecture teacherLectureId;
	
	private AssignedLectures assignLecture;
	
	
	
	
	

	public AssignedLectures getAssignLecture() {
		return assignLecture;
	}

	public void setAssignLecture(AssignedLectures assignLecture) {
		this.assignLecture = assignLecture;
	}

	

	public boolean isAttendance() {
		return attendance;
	}

	public void setAttendance(boolean attendance) {
		this.attendance = attendance;
	}

	public TeacherLecture getTeacherLectureId() {
		return teacherLectureId;
	}

	public void setTeacherLectureId(TeacherLecture teacherLectureId) {
		this.teacherLectureId = teacherLectureId;
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

	

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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
