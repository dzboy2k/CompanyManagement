package com.vti.academy.companymanagement.common;

public class EnumUtil {
	public static boolean isEmptyEnum(Enum<?> e) {
		return e == null ? true : false;
	}
	
	public static boolean isNotEmptyEnum(Enum<?> e) {
		return !isEmptyEnum(e);
	}
}
