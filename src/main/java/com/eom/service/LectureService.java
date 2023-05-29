package com.eom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eom.dto.LecturesDto;
import com.eom.entity.AssignedLectures;
import com.eom.entity.TeacherLecture;
import com.eom.entity.UsersEntity;
import com.eom.enums.LectureStatus;
import com.eom.enums.TransferLectureStatus;
import com.eom.repository.TeacherLectureRepository;
import com.eom.repository.UserDataRepository;

@Service
public class LectureService {

	@Autowired
	private TeacherLectureRepository teachereLecRepository;

	@Autowired
	private UserDataRepository userRepository;

	@Autowired
	private TransferLectureService transferLecService;

	@Autowired
	private ModelMapper mapper;

	public List<LecturesDto> getAllEvents(long uid) {
		List<TeacherLecture> lectures = this.teachereLecRepository.findLecturesByUserId(uid);
		mapper.getConfiguration().setAmbiguityIgnored(true);
		List<LecturesDto> lecdto = lectures.stream().map(lec -> mapper.map(lec, LecturesDto.class))
				.collect(Collectors.toList());
		lecdto.forEach(lec -> {
			if (lec.getLectureStatus().ordinal() == 2 && lec.isRequesetForOther()) {
				Optional<AssignedLectures> assignLec = Optional
						.ofNullable(transferLecService.getLecturesByTeacherLecId(lec.getLectureId()));
				if (assignLec.isPresent())
					lec.setAssignLecture(assignLec.get());
			}
		});

		return lecdto;
	}

	public LecturesDto addLecture(LecturesDto lecture) {
		TeacherLecture teacher = mapper.map(lecture, TeacherLecture.class);
		UsersEntity user = userRepository.findById(lecture.getUserId()).get();
		teacher.setUserId(user);
		TeacherLecture saved = this.teachereLecRepository.save(teacher);
		return mapper.map(saved, LecturesDto.class);
	}

	public LecturesDto updateEvent(LecturesDto newLecture) {
		TeacherLecture databaseLecture = this.teachereLecRepository.findById(newLecture.getLectureId()).get();
		newLecture.setTeacherLectureId(databaseLecture);
		if (newLecture.isRequesetForOther())
			transferLecService.saveTransferLecture(newLecture);
		else if (newLecture.getAssignLecture().isCancelRequest())
			transferLecService.deleteTransferLectureBylecId(newLecture.getAssignLecture().getId());
		databaseLecture.setSubject(newLecture.getSubject());
		databaseLecture.setTeacherName(newLecture.getTeacherName());
		databaseLecture.setClassYear(newLecture.getClassYear());
		databaseLecture.setDepartmentData(newLecture.getDepartmentData());
		databaseLecture.setEnd(newLecture.getEnd());
		databaseLecture.setRequesetForOther(newLecture.isRequesetForOther());
		databaseLecture.setAssingTeacherUidNumber(newLecture.getAssingTeacherUidNumber());
		databaseLecture.setStart(newLecture.getStart());
		databaseLecture.setLectureStatus(newLecture.getLectureStatus());
		databaseLecture.setNotes(newLecture.getNotes());
		TeacherLecture saved = this.teachereLecRepository.saveAndFlush(databaseLecture);
		mapper.getConfiguration().setAmbiguityIgnored(true);
		return mapper.map(saved, LecturesDto.class);
	}

	public void updateRequestStatus(long tlecid, String status) {
		AssignedLectures lec = transferLecService.assignRepository.findById(tlecid).get();
		if (status.equals("ACCEPTED")) {
			lec.setStatus(TransferLectureStatus.ACCEPTED);
			TeacherLecture preTeacherLecture = teachereLecRepository.findById(lec.getLecture().getLectureId()).get();
			preTeacherLecture.setRequesetForOther(false);
			preTeacherLecture.setUserId(lec.getAssignToWhomId());
			preTeacherLecture.setTeacherName(lec.getAssignTeacherName());
			preTeacherLecture.setLectureStatus(LectureStatus.NOT_TAKEN);
			teachereLecRepository.save(preTeacherLecture);
		} else
			lec.setStatus(TransferLectureStatus.DENIED);
		
		transferLecService.assignRepository.save(lec);
	}

}
