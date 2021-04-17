package com.vti.academy.companymanagement.model.mapper;

import org.springframework.stereotype.Component;

import com.vti.academy.companymanagement.model.entity.Company;
import com.vti.academy.companymanagement.model.response.company.CompanyResponse;
import com.vti.academy.companymanagement.model.resquest.company.NewCompanyRequest;

@Component
public class CompanyMapper{
	public static CompanyResponse map(Company company) {
		CompanyResponse companyResponse = new CompanyResponse();
		companyResponse.setCompanyCode(company.getCompanyCode());
		companyResponse.setCompanyName(company.getCompanyName());
		companyResponse.setAddress(company.getAddress());
		companyResponse.setEmail(company.getEmail());
		companyResponse.setPhoneNumber(company.getPhoneNumber());
		companyResponse.setWebsite(company.getWebsite());
		companyResponse.setStatus(company.getStatus());
		return companyResponse;
	}
	
	public static Company map(NewCompanyRequest newCompanyRequest) {
		Company company = new Company();
		company.setCompanyCode(newCompanyRequest.getCompanyCode());
		company.setCompanyName(newCompanyRequest.getCompanyName());
		company.setAddress(newCompanyRequest.getAddress());
		company.setEmail(newCompanyRequest.getEmail());
		company.setPhoneNumber(newCompanyRequest.getPhoneNumber());
		company.setWebsite(newCompanyRequest.getWebsite());
	
		return company;
	}
}
