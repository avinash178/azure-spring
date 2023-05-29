package com.eom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eom.entity.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

	@Query(value = "SELECT *FROM student_data where user_id=:userId",nativeQuery = true)
	Optional<StudentEntity> findByuserId(long userId);
}
