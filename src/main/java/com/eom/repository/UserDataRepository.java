package com.eom.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eom.entity.UsersEntity;

@Repository
public interface UserDataRepository extends JpaRepository<UsersEntity, Long> {


	Optional<UsersEntity> findUsersByUserName(String username);
	
	Optional<UsersEntity> findUsersByEmail(String email);
	
	
	@Query(value = "SELECT *FROM users as  u where u.role_id=:Id",nativeQuery = true)
	List<UsersEntity> findByRoles(long Id);
	
	@Query(value = "SELECT *FROM users where department_id=:id and role_id=1",nativeQuery = true)
	List<UsersEntity> findTeachers(long id);
		
}
