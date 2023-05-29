package com.eom.dto;

import java.util.Date;

import com.eom.entity.TeacherLecture;
import com.eom.entity.UsersEntity;
import com.eom.enums.TransferLectureStatus;

public class AssignedLectureDto {
	
	
	private long id;
	
	
	private UsersEntity assignToWhomId;
	
	private String assignTeacherName;
	
	private TeacherLecture lecture;
	
	private TransferLectureStatus status;
	
	private Date assignedDate;
	
	private boolean cancelRequest;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UsersEntity getAssignToWhomId() {
		return assignToWhomId;
	}

	

	public String getAssignTeacherName() {
		return assignTeacherName;
	}

	public void setAssignTeacherName(String assignTeacherName) {
		this.assignTeacherName = assignTeacherName;
	}

	public TeacherLecture getLecture() {
		return lecture;
	}

	public void setLecture(TeacherLecture lecture) {
		this.lecture = lecture;
	}

	public TransferLectureStatus getStatus() {
		return status;
	}

	public void setStatus(TransferLectureStatus status) {
		this.status = status;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public boolean isCancelRequest() {
		return cancelRequest;
	}

	public void setCancelRequest(boolean cancelRequest) {
		this.cancelRequest = cancelRequest;
	}
	
	

}
