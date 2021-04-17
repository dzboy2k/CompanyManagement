package com.vti.academy.companymanagement.service.impl;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.vti.academy.companymanagement.common.Validate;
import com.vti.academy.companymanagement.dao.CompanyDao;
import com.vti.academy.companymanagement.exception.CustomException;
import com.vti.academy.companymanagement.model.entity.Company;
import com.vti.academy.companymanagement.model.enums.StatusCompany;
import com.vti.academy.companymanagement.model.mapper.CompanyMapper;
import com.vti.academy.companymanagement.model.response.ResultResponse;
import com.vti.academy.companymanagement.model.response.company.CompanyResponse;
import com.vti.academy.companymanagement.model.response.company.ListCompanyResponse;
import com.vti.academy.companymanagement.model.resquest.company.NewCompanyRequest;
import com.vti.academy.companymanagement.model.resquest.company.SearchCompanyRequest;
import com.vti.academy.companymanagement.model.resquest.company.UpdateCompanyRequest;
import com.vti.academy.companymanagement.repository.CompanyReps;
import com.vti.academy.companymanagement.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyReps companyReps;
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public ResultResponse getCompany(String companyCode) throws Exception {
		if(Validate.isEmptyString(companyCode)) {
			LOGGER.warn("Company code is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_COMPANYCODE, ResultResponse.MESSEGE_NULL_COMPANYCODE);
		}else {
			Company company = companyReps.findByCompanyCode(companyCode);
			CompanyResponse companyResponse = company == null ? null : CompanyMapper.map(company);
			return ResultResponse.builder()
					.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
					.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
					.message(ResultResponse.MESSEGE_SUCCESS)
					.result(companyResponse)
					.build();
		}
	}

	public void validateNewCompanyRequest(NewCompanyRequest newCompanyRequest) throws Exception {
		//validate newCompanyRequest
		if(Validate.isEmptyString(newCompanyRequest.getCompanyCode())) {
			LOGGER.warn("Company code is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_COMPANYCODE, ResultResponse.MESSEGE_NULL_COMPANYCODE);
		}else {
			if(companyReps.existsById(newCompanyRequest.getCompanyCode())) {
				LOGGER.warn("Company code is existed! Companycode: " + newCompanyRequest.getCompanyCode());
				throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_EXSIT_COMPANYCODE, ResultResponse.MESSEGE_EXSIT_COMPANYCODE);
			}
		}
		if(Validate.isEmptyString(newCompanyRequest.getCompanyName())) {
			LOGGER.warn("Company name is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_COMPANYNAME, ResultResponse.MESSEGE_NULL_COMPANYNAME);
		}
		if(Validate.isEmptyString(newCompanyRequest.getAddress())) {
			LOGGER.warn("Address is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_ADDRESS, ResultResponse.MESSEGE_NULL_ADDRESS);
		}
		if(Validate.isEmptyString(newCompanyRequest.getEmail())) {
			LOGGER.warn("Email is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_EMAIL, ResultResponse.MESSEGE_NULL_EMAIL);
		}else if(!Validate.validateEmail(newCompanyRequest.getEmail())) {
			LOGGER.warn("Email is invalid! Email: " + newCompanyRequest.getEmail());
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_EMAIL, ResultResponse.MESSEGE_INVALID_EMAIL);			
		}
		if(Validate.isEmptyString(newCompanyRequest.getPhoneNumber())) {
			LOGGER.warn("Phone number is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_PHONENUMBER, ResultResponse.MESSEGE_NULL_PHONENUMBER);
		}else {
			if(!Validate.validatePhoneNumber(newCompanyRequest.getPhoneNumber())) {
				LOGGER.warn("Phone number is valid! Phonenumber: " + newCompanyRequest.getPhoneNumber());
				throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_PHONENUMBER, ResultResponse.MESSEGE_INVALID_PHONENUMBER);			
			}
		}
		
	}
	
	@Override
	public ResultResponse addCompany(NewCompanyRequest newCompanyRequest) throws Exception {
		validateNewCompanyRequest(newCompanyRequest);
		
		Company company = CompanyMapper.map(newCompanyRequest);
		company.setDateCreate(new Timestamp(Instant.now().toEpochMilli()));
		company.setDateModifier(new Timestamp(Instant.now().toEpochMilli()));
		company.setStatus(StatusCompany.PENDING);
		
		company = companyReps.save(company);
		CompanyResponse companyResponse = CompanyMapper.map(company);
		
		return ResultResponse.builder()
				.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
				.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
				.message(ResultResponse.MESSEGE_SUCCESS)
				.result(companyResponse)
				.build();		
	}

	@Override
	public ResultResponse updateCompany(UpdateCompanyRequest updateCompanyRequest) throws Exception {
		Company company = companyReps.findById(updateCompanyRequest.getCompanyCode()).orElseGet(null);
		if(company == null) {
			LOGGER.warn("Company code is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_COMPANY, ResultResponse.MESSEGE_NOT_EXIST_COMPANY);
		}else {
			if(Validate.isNotEmptyString(updateCompanyRequest.getCompanyCode())) {
				if(!company.getCompanyCode().equals(updateCompanyRequest.getCompanyCode())
					&& companyReps.existsById(updateCompanyRequest.getCompanyCode())) {
					LOGGER.warn("Company code is existed! CompanyCode update: " + company.getCompanyCode());
					throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_EXSIT_COMPANYCODE, ResultResponse.MESSEGE_EXSIT_COMPANYCODE);
				}
				company.setCompanyCode(updateCompanyRequest.getCompanyCode());
			}
			if(Validate.isNotEmptyString(updateCompanyRequest.getCompanyName())) {
				company.setCompanyName(updateCompanyRequest.getCompanyName());
			}
			if(Validate.isNotEmptyString(updateCompanyRequest.getAddress())) {
				company.setAddress(updateCompanyRequest.getAddress());
			}
			if(Validate.isNotEmptyString(updateCompanyRequest.getEmail())) {
				if(!Validate.validateEmail(updateCompanyRequest.getEmail())) {
					throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_EMAIL, ResultResponse.MESSEGE_INVALID_EMAIL);
				}
				company.setEmail(updateCompanyRequest.getEmail());
			}
			if(Validate.isNotEmptyString(updateCompanyRequest.getPhoneNumber())) {
				if(!Validate.validatePhoneNumber(updateCompanyRequest.getPhoneNumber())) {
					throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_PHONENUMBER, ResultResponse.MESSEGE_INVALID_PHONENUMBER);
				}
				company.setPhoneNumber(updateCompanyRequest.getPhoneNumber());
			}
			company.setWebsite(updateCompanyRequest.getWebsite());
			if(updateCompanyRequest.getStatus() != null) {
				company.setStatus(updateCompanyRequest.getStatus());
			}
		}
		
		company = companyReps.save(company);
		CompanyResponse companyResponse = CompanyMapper.map(company);
		
		return ResultResponse.builder()
				.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
				.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
				.message(ResultResponse.MESSEGE_SUCCESS)
				.result(companyResponse)
				.build();
	}

	@Override
	public ResultResponse deleteCompany(String companyCode) throws Exception{
		if(Validate.isEmptyString(companyCode)) {
			LOGGER.warn("Company code is null!");
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NULL_COMPANYCODE, ResultResponse.MESSEGE_NULL_COMPANYCODE);
		}else {
			if(!companyReps.existsById(companyCode)) {
				throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_COMPANY, ResultResponse.MESSEGE_NOT_EXIST_COMPANY);
			}
			Company company = companyReps.findById(companyCode).get();
			company.setActive(false);// need custom
			companyReps.save(company);
			return ResultResponse.builder()
					.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
					.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
					.message(ResultResponse.MESSEGE_SUCCESS)
					.build();
		}
	}

	protected void validateSearchReq(SearchCompanyRequest searchCompanyRequest, Integer page, Integer limit,
			String sortBy, String sortType) throws Exception {
		// validate SearchCompanyRequest
		
		//validate page
		if(page < 1) {
			LOGGER.warn("Page index is out of index! Page index: " + page);
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_OUT_OF_INDEX, ResultResponse.MESSEGE_OUT_OF_INDEX);
		}
		if(limit < 0) {
			LOGGER.warn("Limit must not be less than zero! Limit: " + limit);
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_INVALID_PAGESIZE, ResultResponse.MESSEGE_INVALID_PAGESIZE);
		}
		if( !sortType.equalsIgnoreCase("ASC") && !sortType.equalsIgnoreCase("DESC")) {
			LOGGER.warn("Sort type is invalid! Sort type: " + sortType);
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_SORTTYPE, ResultResponse.MESSEGE_INVALID_SORTTYPE);
		}
		Class<?> clazz = SearchCompanyRequest.class;
		Field[] fields = clazz.getDeclaredFields();
		boolean check = false;
		for(Field field : fields) {
			if(field.getName().equals(sortBy)) {
				check = true;
				break;
			}
		}
		if(!check) {
			LOGGER.warn("SortBy is invalid! SortBy: " + sortBy);
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_SORTBY, ResultResponse.MESSEGE_INVALID_SORTBY);
		}

	}
	
	@Override
	public ResultResponse searchCompany(SearchCompanyRequest searchCompanyRequest, Integer page, Integer limit,
			String sortBy, String sortType) throws Exception {
		//check validate
		validateSearchReq(searchCompanyRequest, page, limit, sortBy, sortType);
//		
//		Sort sort = Sort.by(sortBy);
//		if(sortType.equalsIgnoreCase("ASC")) {
//			sort = sort.ascending();
//		}
//		else if(sortType.equalsIgnoreCase("DESC")) {
//			sort = sort.descending();
//		}
//		else {
//			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_INVALID_SORTTYPE, ResultResponse.MESSEGE_INVALID_SORTTYPE);
//		}
//		
//		Page<Company> pageEntity = companyDao.searchCompany(searchCompanyRequest, PageRequest.of(page, limit, sort));
		Page<Company> pageEntity = companyDao.searchCompany(searchCompanyRequest, PageRequest.of(page, limit));
		Page<CompanyResponse> pageResp = pageEntity.map(CompanyMapper::map);
		ListCompanyResponse listCompanyResponse = new ListCompanyResponse(pageResp.toList(), pageResp.getTotalElements(), pageResp.getPageable());
		
		return ResultResponse.builder()
				.statusCode(200)
				.messageCode("api.success")
				.message("Success!")
				.result(listCompanyResponse)
				.build();
	}

}