package com.eom.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eom.dto.UserDto;
import com.eom.entity.DepartmentEntity;
import com.eom.entity.StudentEntity;
import com.eom.entity.Subjects;
import com.eom.entity.Teachers;
import com.eom.entity.UsersEntity;
import com.eom.exception.EmailExitException;
import com.eom.exception.UserLockedException;
import com.eom.exception.UserNameExitsException;
import com.eom.exception.UserNotFoundException;
import com.eom.exception.WrongPasswordException;
import com.eom.repository.DepartmentRepository;
import com.eom.repository.StudentRepository;
import com.eom.repository.SubjectsRepository;
import com.eom.repository.TeacherRepository;
import com.eom.repository.UserDataRepository;
import com.eom.repository.UserRolesRepository;
import com.eom.security.JwtUtils;

@Service
public class UserRegistrationService {

	@Autowired
	private UserDataRepository repository;

	@Autowired
	private UserDetailsServiceImpl UserDetailsService;

	@Autowired
	private UserRolesRepository userRolesrepository;

	@Autowired
	private DepartmentRepository departmentrepository;

	@Autowired
	private PasswordEncoder passwordencoder;

	@Autowired
	private JwtUtils jwtTokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private SubjectsRepository subjectRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private ModelMapper mapper;

	public UsersEntity registerNewUser(UsersEntity user)
			throws UserNameExitsException, EmailExitException, UserNotFoundException {
		validateUserdata(user.getUserName(), user.getEmail());
		user.setPassword(passwordencoder.encode(user.getPassword()));
		user.setLocked(true);
		user.setDepartmentdata(departmentrepository.findById(user.getDepartmentdata().getD_id()).get());
		user.setRoles(userRolesrepository.findById(user.getRoles().getRoleId()).get());
		UsersEntity userSaved = repository.saveAndFlush(user);
		if (user.getYearOfStudy() != null) {
			studentRepo.save(new StudentEntity(userSaved, user.getYearOfStudy()));
		}
		if (user.getSubject() != null) {
			Subjects subject = subjectRepository.findById(user.getSubject().getSid()).get();
			teacherRepository.save(new Teachers(user, subject, user.getDepartmentdata()));
		}
		return userSaved;
	}

	public void validateUserdata(String newUsername, String email)
			throws UserNameExitsException, EmailExitException, UserNotFoundException {
		if (checkUserNameExit(newUsername)) {
			throw new UserNameExitsException("This userName have already taken");
		}
		if (checkEmailExist(email)) {
			throw new EmailExitException("This email have already been registerd");
		}
	}

	public UserDto loginUser(UserDto user) throws UserNotFoundException, WrongPasswordException, UserLockedException,
			NullPointerException, BadCredentialsException {
		UsersEntity userentity = mapper.map(user, UsersEntity.class);
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		final UserDetails userDetails = UserDetailsService.loadUserByUsername(user.getUserName());
		final String jwt = jwtTokenUtils.generateToken(userDetails);

		UsersEntity userData = this.getUserByUserName(userentity.getUserName());
		studentRepo.findByuserId(userData.getUserId()).ifPresent(student -> userData.setYearOfStudy(student.getYear()));
		teacherRepository.findById(userData.getUserId())
				.ifPresent(teacher -> userData.setSubject(teacher.getSubjectName()));
		mapper.getConfiguration().setAmbiguityIgnored(true);
		UserDto loginUser = mapper.map(userData, UserDto.class);
		loginUser.setJwtToken(jwt);
		return loginUser;
	}

	public boolean checkEmailExist(String email) {
		 Optional<UsersEntity> user=this.repository.findUsersByEmail(email);
		  return (user.isEmpty()) ? false :true;
		 
	}

	public UsersEntity getUserByUserName(String username) throws UserNotFoundException {
		Optional<UsersEntity> user = repository.findUsersByUserName(username);
		if (user.isEmpty())
			throw new UserNotFoundException("user not found");
		return user.get();

	}

	public List<Subjects> getSubjectsByDepartent(DepartmentEntity id) {
		return this.subjectRepository.findByDepartments(id);
	}

	public boolean checkUserNameExit(String name) {
		Optional<UsersEntity> user = repository.findUsersByUserName(name);
		return (user.isEmpty()) ? false:true;
	}

}
