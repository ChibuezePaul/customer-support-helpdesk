package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.config.TicketMailService;
import com.isoft.customersupport.config.Util;
import com.isoft.customersupport.exception.UserNotFoundException;
import com.isoft.customersupport.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	public final SimpleMailMessage template;
	private final TicketMailService ticketMailService;
	private final PasswordEncoder encoder;
	
	@Autowired
	public UserServiceImpl ( UserRepository userRepository , SimpleMailMessage template , TicketMailService ticketMailService , PasswordEncoder encoder ) {
		this.userRepository = userRepository;
		this.template = template;
		this.ticketMailService = ticketMailService;
		this.encoder = encoder;
	}
	
	@Override
	public User createUser ( User userCmd ) {
		if(userCmd==null) throw new ValidationException ();
		User newUser = new User ();
		newUser.setEmail ( userCmd.getEmail () );
		String tempPassword = Util.generatePassword ();
		newUser.setPassword ( encoder.encode ( tempPassword ) );
		newUser.setRole ( Roles.ADMIN );
		ticketMailService.sendNewUserCreatedMessage ( "Supervisor Account Created", "Username : "+ newUser.getEmail () + "<br />Password : "+tempPassword , newUser.getEmail () );
		return userRepository.save ( newUser );
	}
	
	
	
	@Override
	public User updateUser ( User userCmd ) {
		User user = userRepository.findByEmail ( userCmd.getEmail () );
		if(user == null) throw new UserNotFoundException ();
		if( ! userCmd.getPassword ().equals ( user.getPassword () ) ) throw new ValidationException ();
		user.setIsFirstLogin (false);
		user.setPassword ( encoder.encode ( userCmd.getPassword () ) );
		return userRepository.save ( user );
	}
	
	@Override
	public User findUserByEmail ( String email ) {
		if(email == null) throw new ValidationException ();
		User user = userRepository.findByEmail ( email );
		if(user == null) throw new UserNotFoundException ();
		return userRepository.findByEmail ( email );
	}
	
	@Override
	public User findUserById ( Integer id ) {
		return userRepository.findById ( id ).orElseThrow ( UserNotFoundException ::new );
	}
	
	@Override
	public void deleteUser ( String email ) {
		User user = userRepository.findByEmail ( email );
		if(user == null) throw new UserNotFoundException ();
		userRepository.delete ( user);
	}
	
	@Override
	public List< User > findAllUsers () {
		return userRepository.findAll ();
	}
	
	@Override
	public boolean isFirstLogin ( String email ) {
		 User adminUser = userRepository.findByEmail ( email );
		 return adminUser == null ? false : adminUser.getIsFirstLogin ();
	}
}
