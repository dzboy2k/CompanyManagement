package com.vti.academy.companymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.academy.companymanagement.model.entity.Role;

public interface RoleReps extends JpaRepository<Role, Integer>{
	Role findByroleName(String roleName);
}
