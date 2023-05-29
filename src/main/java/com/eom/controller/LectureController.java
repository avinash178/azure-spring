package com.eom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eom.dto.AssignedLectureDto;
import com.eom.dto.LecturesDto;
import com.eom.dto.UserDto;
import com.eom.service.LectureService;
import com.eom.service.TransferLectureService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController                               
@RequestMapping("/events")
public class LectureController {

	@Autowired
	private LectureService service;
	
	@Autowired
	private TransferLectureService lecservice;

	@GetMapping("/getTeacherByUserId")
	private ResponseEntity<List<LecturesDto>> getAllEventsByTeacherUid(@RequestParam(value = "userId") long userId) {
		
		return new ResponseEntity<>(this.service.getAllEvents(userId), HttpStatus.OK);
	}

	@PostMapping("/add")
	private ResponseEntity<LecturesDto> addEvents(@RequestBody LecturesDto lecture) {
		return new ResponseEntity<>(this.service.addLecture(lecture), HttpStatus.OK);
	}

	@PutMapping("/update")
	private ResponseEntity<LecturesDto> updateEvent(@RequestBody LecturesDto lecture) {
		return new ResponseEntity<>(this.service.updateEvent(lecture), HttpStatus.OK);
	}

	
	@GetMapping("/teachers")
	private ResponseEntity<List<UserDto>> getAllTeacherInDepartment(@RequestParam(value = "id") long id) {
		return new ResponseEntity<>(this.lecservice.findTeachersBydepartmentId(id) , HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete")
	private void deleteTransferedLecture(@RequestParam(value = "id") long id,@RequestParam(value = "tLecId") long tLecId) {
		this.lecservice.deleteTransferLecture(id,tLecId);
	}
	
	
	@GetMapping("/getRequested/lectures")
	private ResponseEntity<List<AssignedLectureDto>> getAlllectureRequested(@RequestParam(value = "teacherId") long teacherId) {
		return new ResponseEntity<>(this.lecservice.findAllLectureRequested(teacherId),HttpStatus.OK);
	}
	
	@PutMapping("/requestUpdate/{lecId}/{status}")
	private void updateRequestStatus(@PathVariable(value = "lecId") long lecId,@PathVariable(value = "status") String status) {
		this.service.updateRequestStatus(lecId,status);
	}
	
}
