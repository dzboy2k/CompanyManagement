package com.vti.academy.companymanagement.model.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vti.academy.companymanagement.model.enums.StatusCompany;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
@Table(name = "tblcompany")
public class Company {
	@Id
	@Column(name = "companycode")
	private String companyCode;
	@Column(name = "companyname")
	private String companyName;
	@Column(name = "address")
	private String address;
	@Column(name = "email")
	private String email;
	@Column(name = "phonenumber")
	private String phoneNumber;
	@Column(name = "website")
	private String website;
	@Column(name = "logo")
	private String logo;
	@Column(name = "datecreate")
	private Timestamp dateCreate;
	private String creater;
	@Column(name = "datemodifier")
	private Timestamp dateModifier;
	@Column(name = "modifier")
	private String modifier;
	@Column(name = "status")
	private Byte status;
	@Column(name = "isactive")
	private boolean isActive;
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Quan hệ 1-n với đối tượng ở dưới (User)
	private List<User> listUser; 
	
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
