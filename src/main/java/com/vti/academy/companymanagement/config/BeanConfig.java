package com.vti.academy.companymanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
public class BeanConfig {
	@Bean
    public SpringTemplateEngine creatInstantTemplateEngine() {
    	SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    	return templateEngine;
    }
}
