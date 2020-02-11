package com.isoft.customersupport;

import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.location.CustomerLocationRepository;
import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.team.TeamRepository;
import com.isoft.customersupport.ticket.category.Category;
import com.isoft.customersupport.ticket.category.CategoryRepository;
import com.isoft.customersupport.usermngt.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Time;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication @Slf4j
public class CustomerSupportApplication implements InitializingBean {

	private final UserRepository userRepository;
	private final ActiveDirectoryRepo activeDirectoryRepo;
	private final CustomerLocationRepository locationRepository;
	private final TeamRepository teamRepository;
	private final CategoryRepository categoryRepository;
	private final PasswordEncoder encoder;
	
	@Value ( "${init.test.data}" )
	public boolean shouldInitializeTestData;
	
	@Autowired
	public CustomerSupportApplication ( UserRepository userRepository , ActiveDirectoryRepo activeDirectoryRepo , CustomerLocationRepository locationRepository , TeamRepository teamRepository , CategoryRepository categoryRepository , PasswordEncoder encoder ) {
		this.userRepository = userRepository;
		this.activeDirectoryRepo = activeDirectoryRepo;
		this.locationRepository = locationRepository;
		this.teamRepository = teamRepository;
		this.categoryRepository = categoryRepository;
		this.encoder = encoder;
	}
	
	public static void main ( String[] args ) {
		SpringApplication.run ( CustomerSupportApplication.class , args );
	}
	
	@Override
	public void afterPropertiesSet () throws Exception {
		
		if(shouldInitializeTestData && userRepository.findAll ().isEmpty ()
	  	&& activeDirectoryRepo.findAll ().isEmpty () && teamRepository.findAll ().isEmpty ()
	  	&& categoryRepository.findAll ().isEmpty () && locationRepository.findAll ().isEmpty ()) {
			
			User user1 = new User ();
			user1.setEmail ( "su@kemmtech.com" );
			user1.setPassword ( encoder.encode ( "." ) );
			user1.setRole ( Roles.SU );
			user1.setIsFirstLogin ( false );
			User user2 = new User ();
			user2.setEmail ( "kemiolaps@gmail.com" );
			user2.setPassword ( encoder.encode ( "admin" ) );
			user2.setRole ( Roles.ADMIN );
			user2.setIsFirstLogin ( false );
			userRepository.saveAll ( Arrays.asList ( user1 , user2 ) );
			
			ActiveDirectory adUser1 = new ActiveDirectory ();
			adUser1.setEmail ( "kemmieolaps@yahoo.co.uk" );
			adUser1.setPassword ( encoder.encode ( "kemi" ) );
			activeDirectoryRepo.saveAll ( Arrays.asList (adUser1) );
			
			Team team1 = new Team ();
			team1.setName ( "IT Support" );
			team1.setSupervisor ( user2 );
			team1.setMembers ( new HashSet<> ( Arrays.asList ( adUser1 ) ) );
			Team team2 = new Team ();
			team2.setName ( "Customer HelpDesk" );
			team2.setSupervisor ( user2 );
			team2.setMembers ( new HashSet<> ( Arrays.asList ( adUser1 ) ) );
			Team team3 = new Team ();
			team3.setName ( "Developers" );
			team3.setSupervisor ( user2 );
			team3.setMembers ( new HashSet<> ( Arrays.asList ( adUser1 ) ) );
			teamRepository.saveAll ( Arrays.asList ( team1 , team2, team3 ) );
			
			Category category1 = new Category ();
			category1.setTicketClass ( "ATM Dispense Error" );
			category1.setAssignee ( team2 );
			category1.setTAT ( "48 Hours" );
			Category category2 = new Category ();
			category2.setTicketClass ( "Backup and Storage" );
			category2.setAssignee ( team2 );
			Category category3 = new Category ();
			category3.setTicketClass ( "Biometrics" );
			category3.setAssignee ( team2 );
			Category category4 = new Category ();
			category4.setTicketClass ( "Business Process Management Platform" );
			category4.setAssignee ( team2 );
			category1.setTAT ( "16 Hours" );
			Category category5 = new Category ();
			category5.setTicketClass ( "Data Centre" );
			category5.setAssignee ( team2 );
			Category category6 = new Category ();
			category6.setTicketClass ( "File Server" );
			category6.setAssignee ( team2 );
			Category category7 = new Category ();
			category7.setTicketClass ( "Finacle" );
			category7.setAssignee ( team2 );
			Category category8 = new Category ();
			category8.setTicketClass ( "Finacle Support" );
			category8.setAssignee ( team2 );
			category1.setTAT ( "5 Hours" );
			Category category9 = new Category ();
			category9.setTicketClass ( "Finone" );
			category9.setAssignee ( team2 );
			category1.setTAT ( "10 Hours" );
			Category category10 = new Category ();
			category10.setTicketClass ( "GSS Scanner" );
			category10.setAssignee ( team2 );
			category1.setTAT ( "4 Hours" );
			Category category11 = new Category ();
			category11.setTicketClass ( "Hardware" );
			category11.setAssignee ( team2 );
			category1.setTAT ( "48 Hours" );
			Category category12 = new Category ();
			category12.setTicketClass ( "Network" );
			category12.setAssignee ( team3 );
			category1.setTAT ( "5 Hours" );
			Category category13 = new Category ();
			category13.setTicketClass ( "Other" );
			category13.setAssignee ( createAdminTeam(user2) );
			category1.setTAT ( "3 Hours" );
			categoryRepository.saveAll ( Arrays.asList ( category1 , category2 , category3 , category4 , category5 , category6 , category7 , category8 , category9 , category10 , category11 , category12, category13 ) );
			
			CustomerLocation location1 = new CustomerLocation ();
			location1.setCustomerBranch ( "Head Office" );
			location1.setCustomerCity ( "Lagos" );
			location1.setCustomerCountry ( "Nigeria" );
			CustomerLocation location2 = new CustomerLocation ();
			location2.setCustomerBranch ( "Island Branch" );
			location2.setCustomerCity ( "Abuja" );
			location2.setCustomerCountry ( "Nigeria" );
			CustomerLocation location3 = new CustomerLocation ();
			location3.setCustomerBranch ( "PH Branch" );
			location3.setCustomerCity ( "Port Harcourt" );
			location3.setCustomerCountry ( "Nigeria" );
			CustomerLocation location4 = new CustomerLocation ();
			location4.setCustomerBranch ( "Data Centre" );
			location4.setCustomerCity ( "Enugu" );
			location4.setCustomerCountry ( "Nigeria" );
			CustomerLocation location5 = new CustomerLocation ();
			location5.setCustomerBranch ( "IT Support" );
			location5.setCustomerCity ( "Ogun" );
			location5.setCustomerCountry ( "Nigeria" );
			locationRepository.saveAll ( Arrays.asList ( location1 , location2, location3 , location4 , location5 ) );
			
			log.info("Init Data Created Successfully!");
		}
	}
	
	private Team createAdminTeam(User... admins){
		Team adminTeam = new Team (  );
		adminTeam.setName ( "Admin Team" );
		User u = new User ();
		u.setEmail ( "noemail@kemmtech.com" );
		userRepository.save ( u );
		adminTeam.setSupervisor ( u );
		Set<ActiveDirectory> adminMembers = new HashSet<> ( );
		for (User user : admins){
			ActiveDirectory adminDTO = new ActiveDirectory ();
			adminDTO.setEmail ( user.getEmail () );
			adminDTO.setPassword ( user.getPassword () );
			activeDirectoryRepo.save ( adminDTO );
			adminMembers.add ( adminDTO );
		}
		adminTeam.setMembers ( adminMembers );
		teamRepository.save ( adminTeam );
		log.info ( "admin team {}",adminTeam );
		return adminTeam;
	}
}
