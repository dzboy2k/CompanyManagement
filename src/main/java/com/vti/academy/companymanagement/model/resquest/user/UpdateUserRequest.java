package com.vti.academy.companymanagement.model.resquest.user;

import java.sql.Timestamp;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import com.vti.academy.companymanagement.model.enums.StatusUser;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserRequest {
	@NotBlank(message = "employeeCode is not null")
	private String employeeCode;
	@NotBlank(message = "employeeName is not null")
	private String employeeName;
	@NotBlank(message = "email is not null")
	@Email(message = "email is invalid")
	private String email;
	@NotBlank(message = "workmail is not null")
	@Email(message = "workmail is invalid")
	private String workmail;
	@NotBlank(message = "address not null")
	private String address;
	@Pattern(regexp = "(84|0)+([2|3|5|7|8|9][0-9]{8})", message = "Phonenumber is invalid")
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Timestamp dateOfBirth;
	@NotBlank(message = "passportNo is not null")
	private String passportNo;
	@NotBlank(message = "passportDate is not null")
	private Timestamp passportDate;
	private Byte experience;
	@NotBlank(message = "Company code is not null")
	private String companyCode;
	private String branchCode;
	private String branchName;
	private String typeOfContract;
	private List<String> listRoleName;
	private StatusUser status;
}
