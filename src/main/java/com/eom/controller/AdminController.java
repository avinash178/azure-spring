package com.eom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eom.dto.UserDto;
import com.eom.entity.UsersEntity;
import com.eom.service.AdminService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/hi")
	public String getString() {
		return "is it working";
	}
	
	@GetMapping("/allstudent")
	public ResponseEntity<List<UserDto>> getAllStudents() {
		return new ResponseEntity<>(adminService.getAllStudents(), HttpStatus.OK);
	}

	@GetMapping("/allteacher")
	public ResponseEntity<List<UserDto>> getAllTeachers() {
		return new ResponseEntity<>(adminService.getAllTeachers(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/")
	public ResponseEntity<Void> deleteUser(@RequestParam(value = "username") String username) {
		this.adminService.deleteUserByUserName(username);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/updateUser/")
	public ResponseEntity<UsersEntity> updateUser(@RequestBody UsersEntity user,
			@RequestParam(value = "username") String username) {
		return new ResponseEntity<>(this.adminService.updateUser(username, user), HttpStatus.OK);
	}

}
