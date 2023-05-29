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

import com.eom.dto.LecturesDto;
import com.eom.enums.TransferLectureStatus;

@Entity
@Table(name = "assigned_lectures")
public class AssignedLectures {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "assign_to_whom_id")
	private UsersEntity assignToWhomId;
	
	@Column(name = "assign_teacher_name")
	private String assignTeacherName;
	
	@OneToOne
	@JoinColumn(name="teacher_lecture")
	private TeacherLecture lecture;
	
	private TransferLectureStatus status;
	
	private Date assignedDate;
	
	private boolean cancelRequest;
	
	
	public AssignedLectures() {
		super();
	}
	
	public AssignedLectures(LecturesDto lec) {
		this.lecture=lec.getTeacherLectureId();
		this.cancelRequest=lec.getAssignLecture().isCancelRequest();
		this.assignedDate=lec.getAssignLecture().getAssignedDate();
	}
	

	public boolean isCancelRequest() {
		return cancelRequest;
	}

	public void setCancelRequest(boolean cancelRequest) {
		this.cancelRequest = cancelRequest;
	}

	public String getAssignTeacherName() {
		return assignTeacherName;
	}

	public void setAssignTeacherName(String assignTeacherName) {
		this.assignTeacherName = assignTeacherName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public UsersEntity getAssignToWhomId() {
		return assignToWhomId;
	}

	public void setAssignToWhomId(UsersEntity assignToWhomId) {
		this.assignToWhomId = assignToWhomId;
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
	
	

}
