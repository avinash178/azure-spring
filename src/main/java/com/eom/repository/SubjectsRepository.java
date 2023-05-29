package com.eom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eom.entity.DepartmentEntity;
import com.eom.entity.Subjects;

public interface SubjectsRepository extends JpaRepository<Subjects, Long> {

	List<Subjects> findByDepartments(DepartmentEntity id);

	

}
