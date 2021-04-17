package com.vti.academy.companymanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultResponse {
	@Getter @Setter private int statusCode;
	@Getter @Setter private String messageCode;
	@Getter @Setter private String message;
	@Getter @Setter private Object result;
	
	public static final int STATUS_CODE_SUCCESS = 200;
	public static final int STATUS_CODE_BAD_REQUEST = 400;
	public static final int STATUS_CODE_NOT_FOUND = 404;
	public static final int STATUS_CODE_UNKNOWN_FAILED = 500;
	public static final int STATUS_CODE_UNAUTHORIZED = 401;
	public static final int STATUS_CODE_FORBIDDEN = 403;
	public static final int STATUS_CODE_NOT_SUPPORT = 300;
	public static final int STATUS_CODE_TOO_MANY_REQUEST = 309;
	
	public static final String MESSEGE_CODE_SUCCESS = "api.success";
	public static final String MESSEGE_SUCCESS = "Success!";
	
	public static final String MESSEGE_CODE_NULL_COMPANYCODE = "api.error.nullCompanyCode";
	public static final String MESSEGE_NULL_COMPANYCODE = "Company code is not null!";

	public static final String MESSEGE_CODE_EXSIT_COMPANYCODE = "api.error.existCompanyCode";
	public static final String MESSEGE_EXSIT_COMPANYCODE = "Company code is existed!";	
	
	public static final String MESSEGE_CODE_NULL_COMPANYNAME = "api.error.nullCompanyName";
	public static final String MESSEGE_NULL_COMPANYNAME = "Company name is not null!";
	
	public static final String MESSEGE_CODE_NULL_ADDRESS = "api.error.nullAddress";
	public static final String MESSEGE_NULL_ADDRESS = "Address is not null!";
	
	public static final String MESSEGE_CODE_NULL_EMAIL = "api.error.nullEmail";
	public static final String MESSEGE_NULL_EMAIL = "Email is not null!";
	
	public static final String MESSEGE_CODE_NULL_PHONENUMBER = "api.error.nullPhoneNumber";
	public static final String MESSEGE_NULL_PHONENUMBER = "Phone number is not null!";
	
	public static final String MESSEGE_CODE_INVALID_EMAIL = "api.error.invalidEmail";
	public static final String MESSEGE_INVALID_EMAIL = "Email is invalid!";
	
	public static final String MESSEGE_CODE_INVALID_PHONENUMBER = "api.error.invalidPhoneNumber";
	public static final String MESSEGE_INVALID_PHONENUMBER = "Phone number is invalid!";
	
	public static final String MESSEGE_CODE_INVALID_SORTTYPE = "api.error.invalidSortType";
	public static final String MESSEGE_INVALID_SORTTYPE = "Sort type is invalid!";
	
	public static final String MESSEGE_CODE_OUT_OF_INDEX = "api.error.outOfIndex";
	public static final String MESSEGE_OUT_OF_INDEX = "Page index must not be less than zero!";
	
	public static final String MESSEGE_CODE_INVALID_PAGESIZE = "api.error.invalidPageSize";
	public static final String MESSEGE_INVALID_PAGESIZE = "Page size must not be less than one!";
	
	public static final String MESSEGE_CODE_INVALID_SORTBY = "api.error.invalidSortBy";
	public static final String MESSEGE_INVALID_SORTBY = "Param sortBy is invalid!";

	public static final String MESSEGE_CODE_NOT_EXIST_COMPANY = "api.error.notExistsCompany";
	public static final String MESSEGE_NOT_EXIST_COMPANY = "Company is not exist!";
	
	public static final String MESSEGE_CODE_NOT_EXIST_ROLE = "api.error.notExistsRole";
	public static final String MESSEGE_NOT_EXIST_ROLE = "Role is not exist!";
	
	public static final String MESSEGE_CODE_EXISTED_EMPLOYEECODE = "api.error.existedEmployeeCode";
	public static final String MESSEGE_EXISTED_EMPLOYEECODE = "EmployeeCode is exist!";
	
	public static final String MESSEGE_CODE_NOT_EXIST_EMPLOYEECODE = "api.error.notExistEmployeeCode";
	public static final String MESSEGE_NOT_EXIST_EMPLOYEECODE = "EmployeeCode is not exist!";
}
