package com.laps.app.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendEmail(String fromEmail, String toEmail, String subject, String body) {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setSubject(subject);
			helper.setFrom(fromEmail);
			helper.setTo(toEmail);

			boolean html = true;

			helper.setText(body + "<br><br>Go to the portal -> <a href='http://localhost:8081/login'>login here</a>",
					html);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		mailSender.send(message);
	}

}
