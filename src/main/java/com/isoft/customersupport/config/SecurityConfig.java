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
    
    @Autowired
    private Environment env;
    
    @Autowired
    private UserDetailsServiceImpl customerUserDetailsService;
    
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
                .antMatchers("/").authenticated ()
                .antMatchers("/open-tickets").authenticated ()
                .antMatchers("/all-tickets").authenticated ()
                .antMatchers("/new***").authenticated ()
                .antMatchers("/save***").authenticated ()
                .antMatchers("/change-password").authenticated ()
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
        return new BCryptPasswordEncoder ();
    }
    
    @Bean
    public JavaMailSender getMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("isoft.code.io@gmail.com");
//        mailSender.setUsername(env.getProperty ("spring.mail.username"));
//        mailSender.setPassword(env.getProperty ("spring.mail.password"));
        mailSender.setPassword("iamisoftcode.io");

        Properties props = mailSender.getJavaMailProperties();
//        Properties props = new Properties (  );
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
//
        Session session = Session.getInstance ( props , new Authenticator () {
            @Override
            protected PasswordAuthentication getPasswordAuthentication () {
                return new PasswordAuthentication ("isoft.code.io@gmail.com","iamisoftcode.io" );
            }
        } );
        mailSender.setSession ( session );//setJavaMailProperties ( props );
        return mailSender;
    }
   
    @Bean
    public SimpleMailMessage templateSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage ();
        message.setText(
              "A new ticket has been assigned to your team with the following details.\n%s\n");
        return message;
    }
}