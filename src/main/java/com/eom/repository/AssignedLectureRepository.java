package com.eom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eom.entity.AssignedLectures;

public interface AssignedLectureRepository extends JpaRepository<AssignedLectures, Long> {


	@Query(value = "select*from assigned_lectures where teacher_lecture=:id",nativeQuery = true)
	Optional<AssignedLectures> findLectureByteacherLecId(long id);
	
	AssignedLectures deleteByLecture(long id);
	
	@Query(value = "select*from assigned_lectures where assign_to_whom_id=:teacherId",nativeQuery = true)
	Optional<List<AssignedLectures>> findAllRequestedLectures(long teacherId);
}
