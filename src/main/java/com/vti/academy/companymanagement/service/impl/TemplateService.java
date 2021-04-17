package com.vti.academy.companymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import lombok.Getter;
import lombok.Setter;

@Service
public class TemplateService {
	@Value("${config.mail.template.basename}")
	private String basename;	
	@Value("${config.mail.template.prefix}")
	private String prefix;
	@Value("${config.mail.template.surfix}")
	private String surfix;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	@Getter @Setter private String nameSource;
	@Getter @Setter private Context context;
	
	private static ITemplateResolver htmlTemplateResolver(String prefix, String surfix) {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(prefix);
        templateResolver.setSuffix(surfix);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
	
	private static ResourceBundleMessageSource emailMessageSource(String basename) {
		ResourceBundleMessageSource bundleMessageSource = new ResourceBundleMessageSource();
		bundleMessageSource.setBasename(basename);
        return bundleMessageSource;
    }
	
	public void configResourceTemplate(String nameSource, String prefix, String surfix, String basename) {
		templateEngine.setTemplateResolver(htmlTemplateResolver(prefix, surfix));
		templateEngine.setTemplateEngineMessageSource(emailMessageSource(basename));
	}
	
	public String getContent() {
		configResourceTemplate(nameSource, prefix, surfix, basename);
        return templateEngine.process(nameSource, context);
    }
	
	
}
