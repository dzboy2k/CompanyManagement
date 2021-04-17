package com.vti.academy.companymanagement.model.mail;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
public class Recipient {
	private InternetAddress recipientAddress;
	private RecipientType recipientType;
}
