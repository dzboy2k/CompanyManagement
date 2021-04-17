package com.vti.academy.companymanagement.common;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ValidateTestCase {

	@Test
	void test() {
		assertEquals(true, Validate.validatePhoneNumber("0356928512"));
	}

}
