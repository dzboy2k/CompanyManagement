package com.vti.academy.companymanagement.service;

import com.vti.academy.companymanagement.model.response.ResultResponse;
import com.vti.academy.companymanagement.model.resquest.company.NewCompanyRequest;
import com.vti.academy.companymanagement.model.resquest.company.SearchCompanyRequest;
import com.vti.academy.companymanagement.model.resquest.company.UpdateCompanyRequest;

public interface CompanyService {
	
	ResultResponse getCompany(String companyCode) throws Exception;
	
	ResultResponse addCompany(NewCompanyRequest newCompanyRequest) throws Exception;
	
	ResultResponse updateCompany(UpdateCompanyRequest updateCompanyRequest) throws Exception;
	
	ResultResponse deleteCompany(String companyCode) throws Exception;
	
	ResultResponse searchCompany(SearchCompanyRequest searchCompanyRequest, Integer page, Integer limit, String sortBy, String sortType) throws Exception;
}
