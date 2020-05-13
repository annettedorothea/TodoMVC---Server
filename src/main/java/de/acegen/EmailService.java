package de.acegen;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailService {

	private EmailConfiguration emailConfiguration;

	public EmailService(EmailConfiguration emailConfiguration) {
		this.emailConfiguration = emailConfiguration;
	}

	public void sendEmail(String subject, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName(emailConfiguration.getHost());
			email.setSmtpPort(emailConfiguration.getPort());
			email.setAuthenticator(
					new DefaultAuthenticator(emailConfiguration.getUser(), emailConfiguration.getPassword()));
			email.setStartTLSEnabled(true);
			email.setFrom(emailConfiguration.getUser());
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(emailConfiguration.getUser());
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			throw new WebApplicationException("failedToSendEmail", Response.Status.BAD_REQUEST);
		}
	}
	
}
