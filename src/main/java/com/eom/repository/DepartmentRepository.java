package com.eom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eom.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

}
