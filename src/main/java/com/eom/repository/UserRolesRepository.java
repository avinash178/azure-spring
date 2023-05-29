package com.eom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eom.entity.UserRolesEntity;

public interface UserRolesRepository extends JpaRepository<UserRolesEntity, Long> {
	
	

}
