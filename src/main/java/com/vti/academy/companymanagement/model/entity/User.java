package com.vti.academy.companymanagement.model.entity;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vti.academy.companymanagement.model.enums.StatusUser;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "tbluser")
public class User {
	@Id
	@Column(name = "employeecode")
	private String employeeCode;
	@Column(name = "employeename")
	private String employeeName;
	private String email;
	private String workmail;
	private String address;
	@Column(name = "phonenumber")
	private String phoneNumber;
	@Column(name = "dateofbirth")
	private Timestamp dateOfBirth;
	@Column(name="passportno")
	private String passportNo;
	@Column(name = "passportdate")
	private Timestamp passportDate;
	private byte experience;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "companycode")
	private Company company;
	@Column(name = "branchcode")
	private String branchCode;
	@Column(name = "branchname")
	private String branchName;
	@Column(name = "joindate")
	private Instant joinDate;
	@Column(name = "typeofcontract")	
	private String typeOfContract;
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<UserRole> listUserRole;
	private Byte status;
	@Column(name = "isactive")
	private boolean isActive;
	
	public StatusUser getStatus() {
		return StatusUser.parse(this.status);
	}
	public void setStatus(StatusUser status) {
		this.status = status.getValue();
	}
	public void setStatus(Byte status) {
		this.status = status;
	}
}
