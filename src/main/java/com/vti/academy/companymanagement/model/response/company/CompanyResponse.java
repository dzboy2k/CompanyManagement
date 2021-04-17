package com.vti.academy.companymanagement.model.response.company;

import com.vti.academy.companymanagement.model.enums.StatusCompany;

import lombok.Data;

@Data
public class CompanyResponse {
	private String companyCode;
	private String companyName;
	private String address;
	private String email;
	private String phoneNumber;
	private String website;
	private Byte status;
	
	public StatusCompany getStatus() {
		return StatusCompany.parse(this.status);
	}
	public void setStatus(StatusCompany status) {
		this.status = status.getValue();
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
}
