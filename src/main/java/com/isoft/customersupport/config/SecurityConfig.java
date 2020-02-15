package com.isoft.customersupport.config;

import com.isoft.customersupport.usermngt.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    private final Environment env;
    private final UserDetailsServiceImpl customerUserDetailsService;
    
    @Autowired
    public SecurityConfig ( Environment env , UserDetailsServiceImpl customerUserDetailsService ) {
        this.env = env;
        this.customerUserDetailsService = customerUserDetailsService;
    }
    
    @Override
    protected void configure( AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider ();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customerUserDetailsService);
        auth.authenticationProvider(daoAuthenticationProvider);
    }
    
    @Override
    protected void configure( HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers ( PathRequest.toStaticResources ().atCommonLocations () ).permitAll ()
                .antMatchers("/h2console/**").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/").fullyAuthenticated ()
                .antMatchers("/***-tickets").authenticated ()
                .antMatchers("/single-***").authenticated ()
                .antMatchers("/new-ticket-category","new-team","/change-password","/save-ticket-category","save-team","/save-password").hasAnyRole ( "SU", "ADMIN" )
                .antMatchers("/new-location","/new-admin","/save-location","/save-admin").hasRole ( "SU" )
                .antMatchers("/save-comment***").authenticated ()
                .antMatchers("/save-ticket").authenticated ()
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
        http
              .headers().frameOptions().disable();
    }
    
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }
    
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(env.getProperty ("spring.mail.username"));
        mailSender.setPassword(env.getProperty ("spring.mail.password"));

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        Session session = Session.getInstance ( props , new Authenticator () {
            @Override
            protected PasswordAuthentication getPasswordAuthentication () {
                return new PasswordAuthentication (env.getProperty ("spring.mail.username"),env.getProperty ("spring.mail.password") );
            }
        } );
        mailSender.setSession ( session );
        return mailSender;
    }
   
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage ();
        message.setText(
              "<h2>A new ticket has been assigned to your team with the following details.</h3>" +
					"<p>Ticket Type : %s</p>" +
					"<p>Ticket Class : %s</p>" +
					"<p>Ticket Title : %s</p>" +
					"<p>Issue : %s</p>" +
					"<p>Created By : %s</p>" +
					"<p>Created On : %s</p>" +
					"<p>Contact Number : %s</p>" +
					"<p>Ticket Priority : %s</p>" +
					"<p>Ticket TAT : %s</p>");
        return message;
    }
}