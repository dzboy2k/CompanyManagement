package com.vti.academy.companymanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vti.academy.companymanagement.model.entity.Company;
@Repository
public interface CompanyReps extends JpaRepository<Company, String> {
	
	@Query("select c from Company c where c.companyCode = ?1 and c.isActive = true")
	Company findByCompanyCode(String companyCode);
}
