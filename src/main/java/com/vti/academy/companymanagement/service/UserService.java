package com.vti.academy.companymanagement.service;

import com.vti.academy.companymanagement.model.response.ResultResponse;
import com.vti.academy.companymanagement.model.resquest.user.NewUserRequest;
import com.vti.academy.companymanagement.model.resquest.user.SearchUserRequest;
import com.vti.academy.companymanagement.model.resquest.user.UpdateUserRequest;

public interface UserService {
	
	ResultResponse getUser(String employeeCode) throws Exception;
	
	ResultResponse addUser(NewUserRequest newUserRequest) throws Exception;
	
	ResultResponse updateUser(UpdateUserRequest updateUserRequest) throws Exception;
	
	ResultResponse deleteUser(String employeeCode) throws Exception;
	
	ResultResponse searchUser(SearchUserRequest searchUserRequest) throws Exception;

}
