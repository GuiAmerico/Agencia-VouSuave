package com.agencia.vousuave.controller;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agencia.vousuave.dto.EmailDTO;
import com.agencia.vousuave.entity.Email;
import com.agencia.vousuave.service.EmailService;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@GetMapping("/sending-email")
	public String hello() {
		System.out.println("hello");

		return "Hello";
	}
	
	@PostMapping("/sending-email")
	public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDTO){
		Email email = new Email();
		BeanUtils.copyProperties(emailDTO, email);
		emailService.sendEmail(email);
		return new ResponseEntity<>(email,HttpStatus.CREATED);
	} 
	
}
