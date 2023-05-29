package com.eom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eom.dto.UserDto;
import com.eom.entity.DepartmentEntity;
import com.eom.entity.Subjects;
import com.eom.entity.UsersEntity;
import com.eom.exception.EmailExitException;
import com.eom.exception.UserLockedException;
import com.eom.exception.UserNameExitsException;
import com.eom.exception.UserNotFoundException;
import com.eom.exception.WrongPasswordException;
import com.eom.service.UserRegistrationService;

@CrossOrigin(origins= "http://localhost:4200")
@RestController
@RequestMapping("/college")
public class UsersModalResource {

	@Autowired
	private UserRegistrationService service;
	
	
	@GetMapping("/hi")
	public String checking() {
		return "it is working fine";
	}
	
	
	@PostMapping("/registerUser")        // new User registration
	public ResponseEntity<UsersEntity> registerNewUser(@RequestBody UsersEntity user) throws UserNameExitsException, EmailExitException, UserNotFoundException{
		return new ResponseEntity<>(this.service.registerNewUser(user),HttpStatus.OK);
	}

	
	@PostMapping("/login")
	public ResponseEntity<UserDto> loginUser(@RequestBody UserDto user) throws UserNotFoundException, WrongPasswordException, UserLockedException{
		return new ResponseEntity<>(service.loginUser(user),HttpStatus.OK);
	}
	
	
	@GetMapping("/subjects")
	public ResponseEntity<List<Subjects>> getSubjectBydepartment(@RequestParam(value = "id") DepartmentEntity id){
		return new ResponseEntity<>(service.getSubjectsByDepartent(id),HttpStatus.OK);
	}
	
	
	
}
