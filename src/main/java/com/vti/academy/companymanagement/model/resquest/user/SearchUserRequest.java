package com.vti.academy.companymanagement.model.resquest.user;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class SearchUserRequest {
	private String companyName;
	private String employeeName;
	private String email;
	private String phoneNumber;
	private String sortBy;
	private String sortType;
	private int page;
	private int limit;
}
