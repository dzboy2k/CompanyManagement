package com.vti.academy.companymanagement.model.response.user;

import java.time.Instant;
import java.util.List;

import com.vti.academy.companymanagement.model.enums.StatusUser;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserResponse {
	private String employeeCode;
	private String employeeName;
	private List<String> roleName;
	private byte experience;
	private Instant joinDate;
	private String typeOfContract;
	private StatusUser status;
}
