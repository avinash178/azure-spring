package com.eom.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eom.dto.AssignedLectureDto;
import com.eom.dto.LecturesDto;
import com.eom.dto.UserDto;
import com.eom.entity.AssignedLectures;
import com.eom.entity.TeacherLecture;
import com.eom.entity.UsersEntity;
import com.eom.enums.LectureStatus;
import com.eom.enums.TransferLectureStatus;
import com.eom.repository.AssignedLectureRepository;
import com.eom.repository.TeacherLectureRepository;
import com.eom.repository.UserDataRepository;

@Service
public class TransferLectureService {

	@Autowired
	private UserDataRepository usersRepository;

	@Autowired
	private TeacherLectureRepository teachereRepository;

	@Autowired
	public AssignedLectureRepository assignRepository;

	@Autowired
	private ModelMapper mapper;

	public List<UserDto> findTeachersBydepartmentId(long id) {
		List<UsersEntity> teachers = this.usersRepository.findTeachers(id);
		List<UserDto> users = teachers.stream().map((user) -> mapper.map(user, UserDto.class))
				.collect(Collectors.toList());
		return users;
	}

	public void saveTransferLecture(LecturesDto lec) {
		if (lec.getAssignLecture().getId() != 0) {
			Optional<AssignedLectures> assignLec = assignRepository.findById(lec.getAssignLecture().getId());
			if (assignLec.isPresent()) {
				assignLec.get().setStatus(lec.getAssignLecture().getStatus());
				assignLec.get().setAssignTeacherName(lec.getAssignLecture().getAssignTeacherName());
				assignLec.get().setAssignToWhomId(lec.getAssignLecture().getAssignToWhomId());
				assignLec.get().setAssignedDate(lec.getAssignLecture().getAssignedDate());
				assignRepository.save(assignLec.get());
			}
		} else {
			AssignedLectures newTraLec = new AssignedLectures(lec);
			Optional<UsersEntity> assignToWhom = usersRepository.findById(lec.getAssingTeacherUidNumber());
			if (assignToWhom.isPresent()) {
				newTraLec.setAssignToWhomId(assignToWhom.get());
				newTraLec.setAssignTeacherName(
						assignToWhom.get().getFirstName() + " " + assignToWhom.get().getLastName());
			}
			newTraLec.setStatus(TransferLectureStatus.PENDING);
			assignRepository.save(newTraLec);
		}
	}

	public AssignedLectures getLecturesByTeacherLecId(long id) {
		return this.assignRepository.findLectureByteacherLecId(id).get();
	}

	public void deleteTransferLectureBylecId(long id) {
		assignRepository.deleteById(id);
	}

	public void deleteTransferLecture(long id, long tLecId) {
		Optional<TeacherLecture> tLecture = teachereRepository.findById(tLecId);
		if (tLecture.isPresent()) {
			tLecture.get().setLectureStatus(LectureStatus.NOT_TAKEN);
			tLecture.get().setRequesetForOther(false);
		}
		this.deleteTransferLectureBylecId(id);
	}

	public List<AssignedLectureDto> findAllLectureRequested(long teacherId) {
		List<AssignedLectures> requestLectures = assignRepository.findAllRequestedLectures(teacherId).get();

		return requestLectures.stream().filter(lec -> lec.getStatus() == TransferLectureStatus.PENDING)
				.map(lec -> mapper.map(lec, AssignedLectureDto.class)).collect(Collectors.toList());

	}

}
