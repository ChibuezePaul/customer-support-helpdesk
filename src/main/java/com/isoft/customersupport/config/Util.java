package com.isoft.customersupport.config;

import com.isoft.customersupport.usermngt.ActiveDirectory;
import com.isoft.customersupport.usermngt.ActiveDirectoryRepo;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Util {
	
	@Autowired
	private static ActiveDirectoryRepo activeDirectoryRepo;
	
	public static ActiveDirectory getCurrentUser (){
		UserDetails ud = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ActiveDirectory adUser = new ActiveDirectory ();
		adUser.setEmail ( ud.getUsername() );
		return adUser;
//		Object [] authorities = ud.getAuthorities ().toArray ();
//		user.setIsFirstLogin ( authorities[0].toString () );
//		user.setRole ( Roles.valueOf ( authorities[1].toString ().replaceAll ( "ROLE_","" ) ) );
//		return activeDirectoryRepo.findByEmail ( ud.getUsername () );
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
}
