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
}
