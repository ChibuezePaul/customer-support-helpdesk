package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ActiveDirectoryRepo activeDirectoryRepo;
	
	@Override
	public UserDetails loadUserByUsername ( String s ) throws UsernameNotFoundException {
		ActiveDirectory user = activeDirectoryRepo.findByEmail ( s ).orElseThrow ( UserNotFoundException ::new );
		return new User (user.getEmail (),user.getPassword (), Collections.emptyList ());
	}
}