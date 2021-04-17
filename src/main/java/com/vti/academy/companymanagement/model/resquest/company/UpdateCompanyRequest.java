package com.vti.academy.companymanagement.model.resquest.company;

import com.vti.academy.companymanagement.model.enums.StatusCompany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCompanyRequest {
	private String companyCode;
	private String companyName;
	private String address;
	private String email;
	private String phoneNumber;
	private String website;
	private StatusCompany status;
}
