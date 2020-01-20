package com.isoft.customersupport.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

//@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private UserDetailsServiceImpl userPrincipalDetailsService;
    
    @Override
    protected void configure( AuthenticationManagerBuilder auth) throws Exception {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider ();
//        daoAuthenticationProvider.setPasswordEncoder( NoOpPasswordEncoder .getInstance ());
//        daoAuthenticationProvider.setUserDetailsService ( u -> new User("su",".", Collections.emptyList ()) );
//        auth.authenticationProvider(daoAuthenticationProvider);
        auth.userDetailsService (  u -> new User("su",".", Collections.emptyList () ) );
    }
    
    @Override
    protected void configure( HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers ( PathRequest.toStaticResources ().atCommonLocations () ).permitAll ()
                .antMatchers("/").authenticated ()
                .and()
                .formLogin ()
//                .loginPage("/login")
                .and()
                .logout()
                .invalidateHttpSession ( true )
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout").logoutSuccessUrl("/login");
    }
    
    @Bean
	PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder ();
        return NoOpPasswordEncoder.getInstance ();
    }
}