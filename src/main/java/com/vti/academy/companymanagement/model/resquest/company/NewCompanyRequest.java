package com.vti.academy.companymanagement.model.resquest.company;

import lombok.Data;

@Data
public class NewCompanyRequest {
	private String companyCode;
	private String companyName;
	private String address;
	private String email;
	private String phoneNumber;
	private String website;
}
