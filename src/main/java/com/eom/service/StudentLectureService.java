package com.eom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eom.dto.LecturesDto;
import com.eom.entity.StudentLectures;
import com.eom.entity.TeacherLecture;
import com.eom.entity.UsersEntity;
import com.eom.enums.Years;
import com.eom.repository.StudentLectureRepository;
import com.eom.repository.TeacherLectureRepository;
import com.eom.repository.UserDataRepository;

@Service
public class StudentLectureService {
	
	@Autowired
	private TeacherLectureRepository teacherLecture;
	
	@Autowired
	private StudentLectureRepository studentLecture;
	
	@Autowired
	private UserDataRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	public List<LecturesDto> getAllByDepartmentsAndYear(String year,long did,long userId) {
		List<TeacherLecture> teacherLecList = this.teacherLecture.findLectures(Years.valueOf(year).ordinal(),(int)did);
		List<StudentLectures> studentList=new ArrayList<>();
		
		teacherLecList.forEach((lec)->{
			
			Optional<StudentLectures> studentLect = studentLecture.findByUserIdAndLectureId(userId, lec.getLectureId());
			if(studentLect.isEmpty())studentList.add(new StudentLectures(lec));
			else studentList.add(studentLect.get());
			
			});
		mapper.getConfiguration().setAmbiguityIgnored(true);
		List<LecturesDto> lectureDtoList=studentList.stream().map(user->mapper.map(user, LecturesDto.class)).collect(Collectors.toList());
		return lectureDtoList;
		
	}
	public LecturesDto updateLectureData(LecturesDto newLecture) {
		Optional<StudentLectures> lecture = this.studentLecture.findByUserIdAndLectureId(newLecture.getUserId(), newLecture.getTeacherLectureId().getLectureId());
		if(lecture.isEmpty()) {
			StudentLectures studentLect = mapper.map(newLecture, StudentLectures.class);
			StudentLectures lec=new StudentLectures(studentLect);
			UsersEntity user = repository.findById(newLecture.getUserId()).get();
			lec.setUserId(user);
			StudentLectures saved = studentLecture.save(lec);
			return mapper.map(saved, LecturesDto.class);
		}else if(lecture.isPresent()){
			lecture.ifPresent(user->{
				user.setNotes(newLecture.getNotes());
				user.setAttendance(newLecture.isAttendance());
			});
			studentLecture.save(lecture.get());
		}
		return mapper.map(lecture, LecturesDto.class);
	}

}
