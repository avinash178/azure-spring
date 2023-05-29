package com.eom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eom.dto.LecturesDto;
import com.eom.service.StudentLectureService;

@CrossOrigin(origins= "http://localhost:4200")
@RestController
@RequestMapping("/slecture")
public class StudentLectureController {
	
	@Autowired
	private StudentLectureService studentLec;

	
	@GetMapping("/gets")     
	private ResponseEntity<List<LecturesDto>> getAllLectursByYearAndDepartment(@RequestParam(value ="year") String year, @RequestParam(value = "department") long did,@RequestParam(value = "userId") long userId){
		return new ResponseEntity<>(this.studentLec.getAllByDepartmentsAndYear(year, did,userId),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	private ResponseEntity<LecturesDto> updateEvent(@RequestBody LecturesDto lecture) {
		return new ResponseEntity<>(this.studentLec.updateLectureData(lecture) , HttpStatus.OK);
	}

}
