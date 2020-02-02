package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ActiveDirectoryRepo activeDirectoryRepo;
	@Autowired
	private UserRepository userRepository;
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername ( String s ) throws UsernameNotFoundException {
		User appUser = userRepository.findByEmail ( s );
		ActiveDirectory adUser = activeDirectoryRepo.findByEmail ( s );
		
		if(appUser == null && adUser == null) throw new UserNotFoundException ();
		
		if(appUser == null) {
			return new org.springframework.security.core.userdetails.User ( adUser.getEmail () , adUser.getPassword () , Collections.emptyList () );
		}
		else {
//			Collection<SimpleGrantedAuthority> authorities = new ArrayList<> (  );
//			authorities.add ( new SimpleGrantedAuthority ( "ROLE_" + appUser.getRole () ) );
//			authorities.add ( new SimpleGrantedAuthority ( appUser.getIsFirstLogin () ) );
//			return new org.springframework.security.core.userdetails.User ( appUser.getEmail () , appUser.getPassword () , authorities );
			return new org.springframework.security.core.userdetails.User ( appUser.getEmail () , appUser.getPassword () , Collections.singleton ( new SimpleGrantedAuthority ( "ROLE_" + appUser.getRole () ) ) );
		}
	}
}