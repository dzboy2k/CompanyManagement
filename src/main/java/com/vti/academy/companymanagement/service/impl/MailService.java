package com.vti.academy.companymanagement.service.impl;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.vti.academy.companymanagement.model.mail.Mail;
import com.vti.academy.companymanagement.service.UserService;

import lombok.Getter;
import lombok.Setter;

@Service
public class MailService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	
	private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

    @Value("${config.mail.host}")
    private String host;
    @Value("${config.mail.port}")
    private String port;
    @Value("${config.mail.username}")
    private String email;//mail server
    @Value("${config.mail.password}")
    private String password;

    @Getter @Setter Map<RecipientType, InternetAddress[]> recipiencts;// người nhận
    @Getter @Setter private Mail mail;

    public void sendMail(Mail mail, Map<RecipientType, InternetAddress[]> recipiencts) {
    	this.mail = mail;
    	this.recipiencts = recipiencts;
    	sendMail();
    }
    
    public void sendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
        	message.setFrom(new InternetAddress(email));

        	Set<RecipientType> recipientTypes = recipiencts.keySet();
        	for(RecipientType recipientType : recipientTypes) {
        		message.setRecipients(recipientType, recipiencts.get(recipientType));	
        	}

            message.setSubject(mail.getSubject());
            message.setContent(mail.getContentHTML(), CONTENT_TYPE_TEXT_HTML);
            
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            LOGGER.error("SEND MAIL FAIL");
        }
    }
}
