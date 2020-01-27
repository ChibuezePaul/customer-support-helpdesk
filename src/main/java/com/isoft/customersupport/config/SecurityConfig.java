package com.isoft.customersupport.config;

import com.isoft.customersupport.usermngt.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsServiceImpl customerUserDetailsService;
    
    @Override
    protected void configure( AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService (  customerUserDetailsService );
    }
    
    @Override
    protected void configure( HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers ( PathRequest.toStaticResources ().atCommonLocations () ).permitAll ()
                .antMatchers("/").authenticated ()
                .antMatchers("/login").permitAll ()
                .and()
                .formLogin ()
                .loginPage("/login")
                .and()
                .logout()
                .invalidateHttpSession ( true )
			  	.clearAuthentication ( true )
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout").logoutSuccessUrl("/login");
    }
    
    @Bean
	PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance ();
    }
}