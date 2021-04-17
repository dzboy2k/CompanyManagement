package com.vti.academy.companymanagement.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.academy.companymanagement.model.response.ResultResponse;
import com.vti.academy.companymanagement.model.resquest.user.NewUserRequest;
import com.vti.academy.companymanagement.model.resquest.user.SearchUserRequest;
import com.vti.academy.companymanagement.model.resquest.user.UpdateUserRequest;
import com.vti.academy.companymanagement.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/search")
	public ResultResponse search(@RequestBody SearchUserRequest searchUserRequest) throws Exception {
		
		return userService.searchUser(searchUserRequest);
	}
	
	@GetMapping("/{employeeCode}")
	public ResultResponse getUserByEmployeeCode(@PathVariable("employeeCode") String employeeCode) throws Exception {
		return userService.getUser(employeeCode);
	}
	
	@PostMapping("")
	public ResultResponse addNewUser(@Valid @RequestBody NewUserRequest newUserRequest) throws Exception{
		return userService.addUser(newUserRequest);
	}
	
	@PutMapping("")
	public ResultResponse updateUser(@Valid @RequestBody UpdateUserRequest updateUserRequest) throws Exception{
		return userService.updateUser(updateUserRequest);
	}
	
	@DeleteMapping("/{employeeCode}")
	public ResultResponse deleteUser(@PathVariable("employeeCode") String employeeCode) throws Exception{
		return userService.deleteUser(employeeCode);
	}
}
