package com.vti.academy.companymanagement.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.companymanagement.model.response.ResultResponse;
import com.vti.academy.companymanagement.model.resquest.company.NewCompanyRequest;
import com.vti.academy.companymanagement.model.resquest.company.SearchCompanyRequest;
import com.vti.academy.companymanagement.model.resquest.company.UpdateCompanyRequest;
import com.vti.academy.companymanagement.service.CompanyService;

/**
 * 
 * @Description: Company controller - register, get detail, update and delete company
 * @author: Truong.NguyenThe
 * @create_date: Mar 22, 2021 11:42:58
 * @version: 1.0
 * @modifier: Truong.NguyenThe
 * @modified_date: Mar 22, 2021 11:42:58
 *
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	@GetMapping("/search")
	public ResultResponse search(@RequestParam(name = "companycode", required = false) String companyCode, 
			@RequestParam(name = "companyname", required = false) String companyName, 
			@RequestParam(name = "email", required = false) String email, 
			@RequestParam(name = "phonenumber", required = false) String phoneNumber, 
			@RequestParam(name = "status", required = false) Byte status,
			@RequestParam(name = "page", required = false, defaultValue = "1") Integer page, 
			@RequestParam(name = "limit", required = false, defaultValue = "5") Integer limit, 
			@RequestParam(name = "sortby", required = false, defaultValue = "companyCode") String sortBy, 
			@RequestParam(name = "sorttype", required = false, defaultValue = "ASC") String sortType) throws Exception {
		
		SearchCompanyRequest companyRequest = new SearchCompanyRequest(companyCode, companyName, email, phoneNumber, status);
		return companyService.searchCompany(companyRequest, page, limit, sortBy, sortType);
	}
	
	@PostMapping("")
	public ResultResponse insertNewCompany(@RequestBody NewCompanyRequest newCompanyRequest) throws Exception {
		return companyService.addCompany(newCompanyRequest);
	}
	
	@PutMapping("")
	public ResultResponse updateCompany(@RequestBody UpdateCompanyRequest updateCompanyRequest) throws Exception{
		return companyService.updateCompany(updateCompanyRequest);
	}
	
	@GetMapping("/{companyCode}")
	public ResultResponse detailCompany(@PathVariable("companyCode") String companyCode) throws Exception{
		return companyService.getCompany(companyCode);
	}
	
	@DeleteMapping("/{companyCode}")
	public ResultResponse deleteCompany(@PathVariable("companyCode") String companyCode) throws Exception{
		return companyService.deleteCompany(companyCode);
	}
}
