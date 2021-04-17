package com.vti.academy.companymanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vti.academy.companymanagement.model.entity.User;

@Repository
public interface UserReps extends JpaRepository<User, String> {
	 @Query("select u from User u "
	 		+ "where u.isActive = true and u.employeeName like %:employeeName% and u.email like %:email% and u.phoneNumber like %:phoneNumber% "
	 		+ "and u.company.companyName like %:companyName%")
	 Page<User> searchUser(@Param("employeeName") String employeeName, @Param("email") String email, 
			 @Param("phoneNumber") String phoneNumber, @Param("companyName") String companyName, Pageable pageable);
	 
	 
	 @Query("select u from User u where u.employeeCode = :employeeCode and u.isActive = true")
	 User detailUser(@Param("employeeCode") String employeeCode);
}
