package com.vti.academy.companymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.academy.companymanagement.model.entity.User;
import com.vti.academy.companymanagement.model.entity.UserRole;
@Repository
public interface UserRoleReps extends JpaRepository<UserRole, Integer>{
	@Query(value="select max(id) from tbluserrole", nativeQuery=true)
	Integer nextId();
	
	void deleteByuser(User user);
}
