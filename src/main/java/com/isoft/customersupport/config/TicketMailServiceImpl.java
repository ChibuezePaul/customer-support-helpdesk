package com.isoft.customersupport.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Component @Slf4j
public class TicketMailServiceImpl implements TicketMailService {
	
	public final JavaMailSender emailSender;
	@Value ( "${mail.from}" )
	String emailFrom;
	@Value ( "${spring.servlet.multipart.location}" )
	String attachmentPath;
	
	@Autowired
	public TicketMailServiceImpl ( @Qualifier ( "getMailSender" ) JavaMailSender emailSender ) {this.emailSender =
		  emailSender;}
	
	@Override
	public void sendNewUserCreatedMessage ( String subject , String text , String to ) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom ( emailFrom );
			helper.setTo( to );
			helper.setSubject ( subject );
			helper.setText ( text, true );
			emailSender.send ( message );
		}catch ( Exception e ){
			e.printStackTrace ();
			log.error ( "Error Occured Sending Mail To Newly Created Supervisor {}",e.getMessage () );
		}
	}
	
	@Override
	public void sendTicketMessage(String subject, String text, String cc, String bc, List<String> to, String filename) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = prepareMessage ( message, subject , text , bc, to );
			if (!(cc == null || "".equals ( cc ))) helper.setCc ( cc );
			if (filename != null) {
				FileSystemResource file = new FileSystemResource(new File (filename));
				helper.addAttachment ( "ticket attachment - ".concat ( filename ), file );
			}
			emailSender.send ( message );
		}catch ( Exception e ){
			e.printStackTrace ();
			log.error ( "Error Occured Sending Mail For New Ticket {}",e.getMessage () );
		}
	}
	
	@Override
	public void sendTicketCommentMessage(String subject, String text, String bc, List<String> to){
		try{
			MimeMessage message = emailSender.createMimeMessage();
			prepareMessage ( message, subject , text , bc, to );
			emailSender.send(message);
		
		}catch ( Exception e ){
			e.printStackTrace ();
			log.error ( "Error Occured Sending Mail For New Ticket Comment {}",e.getMessage () );
		}
	}
	
	private MimeMessageHelper prepareMessage ( MimeMessage message , String subject , String text , String bc , List< String > to ) throws MessagingException {
		message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom ( emailFrom );
		helper.setValidateAddresses ( true );
		helper.setReplyTo ( "noreply@kemmtech.com" );
		helper.setSubject(subject);
		helper.setBcc ( bc );
		helper.setText(text,true);
		for ( String t : to ){
			helper.setTo ( t );
		}
		return helper;
	}
}
