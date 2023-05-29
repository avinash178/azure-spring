package com.eom.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eom.entity.StudentLectures;

@Repository
public interface StudentLectureRepository extends JpaRepository<StudentLectures, Long> {

	@Query(value = "SELECT *FROM students_lectures where (teacher_lec_ref=:lectureId and user_id=:userId)",nativeQuery = true)
	Optional<StudentLectures> findByUserIdAndLectureId(long userId,long lectureId);
}
