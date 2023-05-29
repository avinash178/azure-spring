package com.eom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eom.entity.Teachers;
import com.eom.entity.UsersEntity;

public interface TeacherRepository extends JpaRepository<Teachers, Long> {

	Optional<Teachers> findByUserId(UsersEntity userId);
	
	
	
	
}
