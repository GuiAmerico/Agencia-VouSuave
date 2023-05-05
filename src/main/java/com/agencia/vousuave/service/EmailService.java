package com.agencia.vousuave.service;

import java.time.LocalDateTime;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.agencia.vousuave.entity.Email;
import com.agencia.vousuave.enums.StatusEmail;
import com.agencia.vousuave.repository.EmailRepository;

@SuppressWarnings("finally")
@Service
public class EmailService {

	@Autowired
	private EmailRepository repository;
	
	@Autowired
	private JavaMailSender mailSender;

	public Email sendEmail(Email email) {

		email.setSendDateEmail(LocalDateTime.now());
		
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(email.getEmailFrom());
			message.setTo(email.getEmailTo());
			message.setSubject(email.getSubject());
			message.setText(email.getText());
			System.out.println(email.toString());
			InternetAddress internetAddress = new InternetAddress(email.getEmailTo());
			internetAddress.validate();
			mailSender.send(message);
			
			email.setStatusEmail(StatusEmail.SENT);
		} catch (Exception e) {
			System.out.println(e);
			
			email.setStatusEmail(StatusEmail.ERROR);
		}finally {
			return repository.save(email);
		}
		
	}
}
