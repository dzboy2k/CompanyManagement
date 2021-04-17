package com.vti.academy.companymanagement.model.resquest.company;

import com.vti.academy.companymanagement.model.enums.StatusCompany;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCompanyRequest {
	private String companyCode;
	private String companyName;
	private String email;
	private String phoneNumber;
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
