package com.vti.academy.companymanagement.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.vti.academy.companymanagement.model.mail.Mail;

@Service
public class ExtractMailService {
	@Autowired
	private TemplateService templateService;
	private Mail mail;
	
	public Mail getMail(String nameSource, Map<String, Object> params) {		
		Context context = new Context();
		context.setVariables(params);
		
		templateService.setNameSource(nameSource);
		templateService.setContext(context);
		
		if(mail == null) {
			mail = new Mail();
		}
		mail.setContentHTML(templateService.getContent());			
		
		return mail;
	}
	
}
