package com.vti.academy.companymanagement.service.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.academy.companymanagement.common.EnumUtil;
import com.vti.academy.companymanagement.common.Validate;
import com.vti.academy.companymanagement.exception.CustomException;
import com.vti.academy.companymanagement.model.entity.Company;
import com.vti.academy.companymanagement.model.entity.Role;
import com.vti.academy.companymanagement.model.entity.User;
import com.vti.academy.companymanagement.model.entity.UserRole;
import com.vti.academy.companymanagement.model.mail.Mail;
import com.vti.academy.companymanagement.model.mapper.UserMapper;
import com.vti.academy.companymanagement.model.response.ResultResponse;
import com.vti.academy.companymanagement.model.response.user.ListUserResponse;
import com.vti.academy.companymanagement.model.response.user.UserResponse;
import com.vti.academy.companymanagement.model.resquest.user.NewUserRequest;
import com.vti.academy.companymanagement.model.resquest.user.SearchUserRequest;
import com.vti.academy.companymanagement.model.resquest.user.UpdateUserRequest;
import com.vti.academy.companymanagement.repository.CompanyReps;
import com.vti.academy.companymanagement.repository.RoleReps;
import com.vti.academy.companymanagement.repository.UserReps;
import com.vti.academy.companymanagement.repository.UserRoleReps;
import com.vti.academy.companymanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserReps userReps;
	@Autowired
	private CompanyReps companyReps;
	@Autowired
	private RoleReps roleReps;
	@Autowired
	private UserRoleReps userRoleReps;
	@Autowired
	private ExtractMailService extractMailService;
	@Autowired
	private MailService mailService;
	
	@Override
	public ResultResponse getUser(String employeeCode) throws Exception {
		User user = userReps.detailUser(employeeCode);
		UserResponse userResponse = null;
		if(user != null) {
			userResponse = UserMapper.map(user);			
		}
		return ResultResponse.builder()
				.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
				.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
				.message(ResultResponse.MESSEGE_SUCCESS)
				.result(userResponse)
				.build();
		
	}

	void validateNewUserRequest(NewUserRequest newUserRequest) {
		
	}
	
	@Override
	@Transactional
	public ResultResponse addUser(NewUserRequest newUserRequest) throws Exception {
		LOGGER.info("ADD NEW USER");
		
		if(userReps.existsById(newUserRequest.getEmployeeCode())) {
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_EXISTED_EMPLOYEECODE, ResultResponse.MESSEGE_EXISTED_EMPLOYEECODE);
		}
		Company company = companyReps.findById(newUserRequest.getCompanyCode()).orElse(null);
		if(company == null) {
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_COMPANY, ResultResponse.MESSEGE_NOT_EXIST_COMPANY);
		}
		User user = UserMapper.map(newUserRequest);
		user.setCompany(company);
		user.setJoinDate(Instant.now());
		userReps.save(user);
		
		List<UserRole> listUserRole = new ArrayList<>();
		List<String> listRoleName = newUserRequest.getListRoleName();
		for(String roleName : listRoleName) {
			Role role = roleReps.findByroleName(roleName);
			if(role!=null) {
				UserRole userRole = new UserRole();
				userRole.setId(userRoleReps.nextId()+1);
				userRole.setUser(user);
				userRole.setRole(role);
				userRoleReps.save(userRole);
				listUserRole.add(userRole);
			}else {
				throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_ROLE, ResultResponse.MESSEGE_NOT_EXIST_ROLE);
			}
		}
		user.setListUserRole(listUserRole);
		UserResponse userResponse = UserMapper.map(user);
		
		//send mail
		sendMail(user);
		
		
		return ResultResponse.builder()
				.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
				.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
				.message(ResultResponse.MESSEGE_SUCCESS)
				.result(userResponse)
				.build();
	}

	private void sendMail(User user) {
		//người nhận
		Map<RecipientType, InternetAddress[]> recipiencts = new HashMap<>();// người nhận
		
		//list RecipientType.TO
		List<InternetAddress> listRecipientTypeTO = new ArrayList<>();
		try {
			listRecipientTypeTO.add(new InternetAddress(user.getEmail()));
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recipiencts.put(RecipientType.TO, listRecipientTypeTO.toArray(new InternetAddress[1]));
		//list RecipientType.CC
		
		
		//nội dung mail
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		Mail mail = extractMailService.getMail("success_register", map);
		mail.setSubject("Success user register");
		
		mailService.sendMail(mail, recipiencts);
	}
	
	@Override
	@Transactional
	public ResultResponse updateUser(UpdateUserRequest updateUserRequest) throws Exception {
		User user = userReps.findById(updateUserRequest.getEmployeeCode()).orElse(null);
		if(user == null) {
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_EMPLOYEECODE, ResultResponse.MESSEGE_NOT_EXIST_EMPLOYEECODE);
		}
		Company company = companyReps.findById(updateUserRequest.getCompanyCode()).orElse(null);
		if(company == null) {
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_COMPANY, ResultResponse.MESSEGE_NOT_EXIST_COMPANY);
		}
		if(Validate.isNotEmptyString(updateUserRequest.getPhoneNumber())) {
			user.setEmployeeName(updateUserRequest.getPhoneNumber());
		}
		if(Validate.isNotEmptyString(updateUserRequest.getEmployeeName())) {
			user.setEmployeeName(updateUserRequest.getEmployeeName());
		}
		if(Validate.isNotEmptyString(updateUserRequest.getEmail()) && Validate.validateEmail(updateUserRequest.getEmail())) {
			user.setEmail(updateUserRequest.getEmail());
		}
		if(Validate.isNotEmptyString(updateUserRequest.getPassportNo())) {
			user.setPassportNo(updateUserRequest.getPassportNo());
		}
		if(updateUserRequest.getPassportDate() != null) {
			user.setPassportDate(updateUserRequest.getPassportDate());
		}
		if(Validate.isNotEmptyString(updateUserRequest.getAddress())) {
			user.setAddress(updateUserRequest.getAddress());
		}
		if(updateUserRequest.getExperience() != null) {
			user.setExperience(updateUserRequest.getExperience());
		}
		if(Validate.isNotEmptyString(updateUserRequest.getTypeOfContract())) {
			user.setTypeOfContract(updateUserRequest.getTypeOfContract());
		}
		if(EnumUtil.isNotEmptyEnum(updateUserRequest.getStatus())) {
			user.setStatus(updateUserRequest.getStatus());
		}
		user.setCompany(company);
		userReps.save(user);
		
		if(updateUserRequest.getListRoleName() != null) {
			userRoleReps.deleteByuser(user);
			List<UserRole> listUserRole = new ArrayList<>();
			List<String> listRoleName = updateUserRequest.getListRoleName();
			for(String roleName : listRoleName) {
				Role role = roleReps.findByroleName(roleName);
				if(role!=null) {
					UserRole userRole = new UserRole();
					userRole.setId(userRoleReps.nextId()+1);
					userRole.setUser(user);
					userRole.setRole(role);
					userRoleReps.save(userRole);
					listUserRole.add(userRole);
				}else {
					throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_ROLE, ResultResponse.MESSEGE_NOT_EXIST_ROLE);
				}
			}
			user.setListUserRole(listUserRole);
		}
		
		UserResponse userResponse = UserMapper.map(user);
		
		return ResultResponse.builder()
				.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
				.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
				.message(ResultResponse.MESSEGE_SUCCESS)
				.result(userResponse)
				.build();
	}

	@Override
	public ResultResponse deleteUser(String employeeCode) throws Exception {
		User user = userReps.findById(employeeCode).orElse(null);
		if(user == null) {
			throw new CustomException(ResultResponse.STATUS_CODE_BAD_REQUEST, ResultResponse.MESSEGE_CODE_NOT_EXIST_EMPLOYEECODE, ResultResponse.MESSEGE_NOT_EXIST_EMPLOYEECODE);
		}
		user.setActive(false);
		userReps.save(user);
		
		return ResultResponse.builder()
				.statusCode(ResultResponse.STATUS_CODE_SUCCESS)
				.messageCode(ResultResponse.MESSEGE_CODE_SUCCESS)
				.message(ResultResponse.MESSEGE_SUCCESS)
				.result(null)
				.build();
	}

	private void validateSearchUserRequest(SearchUserRequest searchUserRequest) {
		
	}
	
	@Override
	public ResultResponse searchUser(SearchUserRequest searchUserRequest) throws Exception {
		validateSearchUserRequest(searchUserRequest);
		
		Sort sort = Sort.by(searchUserRequest.getSortBy());
		if(searchUserRequest.getSortType().equalsIgnoreCase("DESC")) {
			sort = sort.descending();
		}else {
			sort = sort.ascending();
		}
		PageRequest pageReq = PageRequest.of(searchUserRequest.getPage()-1, searchUserRequest.getLimit(), sort);
		Page<User> pageUser = userReps.searchUser(searchUserRequest.getEmployeeName(), searchUserRequest.getEmail(), searchUserRequest.getPhoneNumber(),
								searchUserRequest.getCompanyName(), pageReq);
		Page<UserResponse> pageUserResponse = pageUser.map(UserMapper::map);									
		ListUserResponse listUserResponse = ListUserResponse.builder()
											.userResponseList(pageUserResponse.toList())
											.total(pageUserResponse.getTotalElements())
											.pageable(pageUserResponse.getPageable())
											.build();
		
		return ResultResponse.builder()
				.statusCode(200)
				.messageCode("api.success")
				.message("Success!")
				.result(listUserResponse)
				.build();
	}

	

}
