package com.vti.academy.companymanagement.common;

public class Validate {
	public static boolean isEmptyString(String s) {
		return (s == null || s.length() == 0 || s.trim().equals(" ")) ? true : false; 
	}
	public static boolean isNotEmptyString(String s) {
		return !isEmptyString(s);
	}
	
	public static boolean validatePhoneNumber(String phoneNumber) {
		return phoneNumber.matches("(84|0)+([2|3|5|7|8|9][0-9]{8})\\b");
	}
	
	public static boolean validateEmail(String email) {
		return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
	}

}
