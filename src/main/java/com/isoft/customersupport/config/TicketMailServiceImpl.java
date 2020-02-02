package com.isoft.customersupport.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Component
public class TicketMailServiceImpl implements TicketMailService {
	
	@Qualifier ( "getMailSender" )
	@Autowired
	public JavaMailSender emailSender;
	@Value ( "${mail.from}" )
	String emailFrom;
	
	@Override
	public void sendNewUserCreatedMessage ( String subject , String text , String to ) {
		try {
			SimpleMailMessage message = new SimpleMailMessage ();
			message.setFrom ( emailFrom );
			message.setTo( to );
			message.setSubject ( subject );
			message.setText ( text );
			emailSender.send ( message );
		}catch ( Exception e ){
			e.printStackTrace ();
		}
	}
	
	@Override
	public void sendTicketMessage(String subject, String text, String cc, String bc, List<String> to) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom ( emailFrom );
			for ( String s : to ) {
				helper.setTo ( s );
			}
			helper.setBcc ( bc );
			helper.setCc ( cc );
			helper.setSubject ( subject );
			helper.setText ( text );
			emailSender.send ( message );
		}catch ( Exception e ){
			e.printStackTrace ();
		}
	}
	
	@Override
	public void sendTicketMessageWithAttachment(String pathToAttachment, String subject, String text, String cc, String bc, String... to){
		
		try{
		
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom ( emailFrom );
		helper.setBcc ( bc );
		helper.setCc ( cc );
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text,true);
		
		if(pathToAttachment!=null){
			FileSystemResource file = new FileSystemResource(new File (pathToAttachment));
			helper.addAttachment("TicketFile", file);
		}
		
		emailSender.send(message);
		
		}catch ( Exception e ){
			e.printStackTrace ();
		}
	}
}
