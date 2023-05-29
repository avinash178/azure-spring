package com.eom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eom.entity.TeacherLecture;


@Repository
public interface TeacherLectureRepository extends JpaRepository<TeacherLecture, Long> {

	@Query(value = "SELECT *FROM teachers_lectures where user_id=:userId",nativeQuery = true)
	List<TeacherLecture> findLecturesByUserId(long userId);
	
	@Query(value = "SELECT *FROM teachers_lectures where department_data=:depart and class_year=:year",nativeQuery = true)
	List<TeacherLecture> findLectures(Integer year,Integer depart);

	
	
}
