package com.kh.portfolio.common.mail;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

		@Inject
		private JavaMailSender mailSender;
		
		@Inject
		private SimpleMailMessage preConfiguredMessage;
		
		private final String From = "sang105kr@gmail.com";
		
		@Async
		public void sendMail(String to, String subject, String body) {
			MimeMessage message = mailSender.createMimeMessage();
			
			try {
				
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				messageHelper.setSubject(subject);
				messageHelper.setTo(to);
				messageHelper.setFrom(From);
				messageHelper.setText(body,true);
				mailSender.send(message);
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		@Async
		public void sendSimpleMail(String message) {
			preConfiguredMessage.setText(message);
			mailSender.send(preConfiguredMessage);
		}
}




