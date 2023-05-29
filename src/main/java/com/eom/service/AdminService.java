package com.eom.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eom.dto.UserDto;
import com.eom.entity.StudentEntity;
import com.eom.entity.Teachers;
import com.eom.entity.UsersEntity;
import com.eom.repository.DepartmentRepository;
import com.eom.repository.StudentRepository;
import com.eom.repository.TeacherRepository;
import com.eom.repository.UserDataRepository;
import com.eom.repository.UserRolesRepository;

@Service
public class AdminService {

	@Autowired
	private UserDataRepository userDataRepository;

	@Autowired
	private UserRolesRepository userRoleRepository;

	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public List<UserDto> getAllStudents() {
		List<UsersEntity> students = this.userDataRepository.findByRoles(2);
		students.stream().forEach(stu->{
			StudentEntity user = studentRepository.findByuserId(stu.getUserId()).get();
			stu.setYearOfStudy(user.getYear());
		}); 
		return students.stream().map(stu-> mapper.map(stu, UserDto.class)).collect(Collectors.toList());
	}

	public List<UserDto> getAllTeachers() {
		List<UsersEntity> teachers = this.userDataRepository.findByRoles(1);
		teachers.stream().forEach(tea->{
			Teachers user = teacherRepository.findByUserId(tea).get();
			tea.setSubject(user.getSubjectName());
		});
		return  teachers.stream().map(tea->mapper.map(tea, UserDto.class)).collect(Collectors.toList());
	}

	public void deleteUserByUserName(String username) {
		this.userDataRepository.delete(this.userDataRepository.findUsersByUserName(username).get());
	}

	public UsersEntity updateUser(String username, UsersEntity user) {
		UsersEntity oldUser = this.userDataRepository.findUsersByUserName(username).get();
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		oldUser.setEmail(user.getEmail());
		oldUser.setLocation(user.getLocation());
		oldUser.setLocked(user.isLocked());
		oldUser.setPhoneNumber(user.getPhoneNumber());
		oldUser.setDepartmentdata((departmentRepository.findById(user.getDepartmentdata().getD_id())).get());
		oldUser.setRoles(userRoleRepository.findById(user.getRoles().getRoleId()).get());
		return this.userDataRepository.save(oldUser);
	}

}
