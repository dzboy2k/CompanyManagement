package com.vti.academy.companymanagement.model.mapper;

import java.util.ArrayList;
import java.util.List;

import com.vti.academy.companymanagement.model.entity.User;
import com.vti.academy.companymanagement.model.entity.UserRole;
import com.vti.academy.companymanagement.model.response.user.UserResponse;
import com.vti.academy.companymanagement.model.resquest.user.NewUserRequest;

public class UserMapper {
	
	public static UserResponse map(User user) {
		UserResponse userResponse = new UserResponse();
		userResponse.setEmployeeCode(user.getEmployeeCode());
		userResponse.setEmployeeName(user.getEmployeeName());
		userResponse.setExperience(user.getExperience());
		userResponse.setJoinDate(user.getJoinDate());
		if(user.getTypeOfContract() != null) userResponse.setTypeOfContract(user.getTypeOfContract().trim());
		userResponse.setStatus(user.getStatus());
	
		if(user.getListUserRole() != null) {
			List<String> listRoleName = new ArrayList<>();
			List<UserRole> listUserRole = user.getListUserRole();
			for(UserRole ur : listUserRole) {
				listRoleName.add(ur.getRole().getRoleName().trim());
			}
			userResponse.setRoleName(listRoleName);
		}
		
		return userResponse;
	}
	
	public static User map(NewUserRequest newUserRequest) {
		User user = new User();
		user.setEmployeeCode(newUserRequest.getEmployeeCode());
		user.setEmployeeName(newUserRequest.getEmployeeName());
		user.setAddress(newUserRequest.getAddress());
		user.setBranchCode(newUserRequest.getBranchCode());
		user.setBranchName(newUserRequest.getBranchName());
		user.setDateOfBirth(newUserRequest.getDateOfBirth());
		user.setEmail(newUserRequest.getEmail());
		user.setWorkmail(newUserRequest.getWorkmail());
		user.setExperience(newUserRequest.getExperience());
		user.setPassportDate(newUserRequest.getPassportDate());
		user.setPassportNo(newUserRequest.getPassportNo());
		user.setPhoneNumber(newUserRequest.getPhoneNumber());
		
		return user;
	}
}
