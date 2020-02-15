package com.isoft.customersupport.config;

import com.isoft.customersupport.exception.ApplicationException;
import com.isoft.customersupport.usermngt.Roles;
import com.isoft.customersupport.usermngt.User;
import com.isoft.customersupport.usermngt.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component @Slf4j
public class Util {
	
	private static final String USER_DOES_NOT_HAVE_PERMISSION_TO_COMPLETE_REQUEST = "User Does Not Have Permission To Complete Request";
	
	public static String getCurrentUser (){
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ud.getUsername();
	}
	
	public static String generatePassword() {
		try {
			return String.format ( "%s%d" , RandomStringUtils.randomAlphabetic ( 5 ).toUpperCase () ,
				  SecureRandom.getInstanceStrong ().nextInt ( 999 ) );
		}
		catch ( NoSuchAlgorithmException e ) {
			e.printStackTrace ();
		}
		return RandomStringUtils.randomAlphabetic ( 8 );
	}
	
	public static void validateUserRoleAction (UserRepository userRepository) {
		User creator = userRepository.findByEmail( Util.getCurrentUser ());
		if(!(creator.getRole ().equals ( Roles.SU ) || creator.getRole ().equals ( Roles.ADMIN ))) throw new ApplicationException ( USER_DOES_NOT_HAVE_PERMISSION_TO_COMPLETE_REQUEST );
	}
	
	public static void validateIsSuperUser (UserRepository userRepository) {
		User creator = userRepository.findByEmail( Util.getCurrentUser ());
		if(!creator.getRole ().equals ( Roles.SU )) throw new ApplicationException ( USER_DOES_NOT_HAVE_PERMISSION_TO_COMPLETE_REQUEST );
	}
}
