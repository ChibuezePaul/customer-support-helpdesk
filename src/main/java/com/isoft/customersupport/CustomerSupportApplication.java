package com.isoft.customersupport;

import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.location.CustomerLocationRepository;
import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.team.TeamRepository;
import com.isoft.customersupport.ticket.category.Category;
import com.isoft.customersupport.ticket.category.CategoryRepository;
import com.isoft.customersupport.usermngt.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
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
			User user3 = new User ();
			user3.setEmail ( "amos4christ@gmail.com" );
			user3.setPassword ( encoder.encode ( "admin" ) );
			user3.setRole ( Roles.ADMIN );
			user3.setIsFirstLogin ( false );
			User user4 = new User ();
			user4.setEmail ( "oyedemitaiwo@yahoo.co.uk" );
			user4.setPassword ( encoder.encode ( "admin" ) );
			user4.setRole ( Roles.ADMIN );
			user4.setIsFirstLogin ( false );
			User user5 = new User ();
			user5.setEmail ( "shosky76@gmail.com" );
			user5.setPassword ( encoder.encode ( "admin" ) );
			user5.setRole ( Roles.ADMIN );
			user5.setIsFirstLogin ( false );
			User user6 = new User ();
			user6.setEmail ( "sulapad2002@yahoo.com" );
			user6.setPassword ( encoder.encode ( "admin" ) );
			user6.setRole ( Roles.ADMIN );
			user6.setIsFirstLogin ( false );
			userRepository.saveAll ( Arrays.asList ( user1 , user2, user3, user4, user5, user6 ) );
			
			ActiveDirectory adUser1 = new ActiveDirectory ();
			adUser1.setEmail ( "kemmieolaps@yahoo.co.uk" );
			adUser1.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser2 = new ActiveDirectory ();
			adUser2.setEmail ( "deboer062000@yahoo.com" );
			adUser2.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser3 = new ActiveDirectory ();
			adUser3.setEmail ( "olapadeabiola@gmail.com" );
			adUser3.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser4 = new ActiveDirectory ();
			adUser4.setEmail ( "olapadeabiola@yahoo.com" );
			adUser4.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser5 = new ActiveDirectory ();
			adUser5.setEmail ( "busolamole@gmail.com" );
			adUser5.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser6 = new ActiveDirectory ();
			adUser6.setEmail ( "tousche77@gmail.com" );
			adUser6.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser7 = new ActiveDirectory ();
			adUser7.setEmail ( "justinboss360@gmail.com" );
			adUser7.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser8 = new ActiveDirectory ();
			adUser8.setEmail ( "ote.ogunniyi@gmail.com" );
			adUser8.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser9 = new ActiveDirectory ();
			adUser9.setEmail ( "kolapojulos@yahoo.co.uk" );
			adUser9.setPassword ( encoder.encode ( "aduser" ) );
			ActiveDirectory adUser10 = new ActiveDirectory ();
			adUser10.setEmail ( "slawuyi@gmail.com" );
			adUser10.setPassword ( encoder.encode ( "aduser" ) );
			activeDirectoryRepo.saveAll ( Arrays.asList (adUser1, adUser1, adUser2, adUser3, adUser4, adUser5, adUser6, adUser7, adUser8, adUser9, adUser10) );
			
			Team team1 = new Team ();
			team1.setName ( "IT Support" );
			team1.setSupervisor ( user2 );
			team1.setMembers ( new HashSet<> ( Arrays.asList ( adUser1, adUser2 ) ) );
			Team team2 = new Team ();
			team2.setName ( "Customer HelpDesk" );
			team2.setSupervisor ( user3 );
			team2.setMembers ( new HashSet<> ( Arrays.asList ( adUser3, adUser4 ) ) );
			Team team3 = new Team ();
			team3.setName ( "Finacle Support" );
			team3.setSupervisor ( user4 );
			team3.setMembers ( new HashSet<> ( Arrays.asList ( adUser5, adUser6 ) ) );
			Team team4 = new Team ();
			team4.setName ( "Hardware Support" );
			team4.setSupervisor ( user4 );
			team4.setMembers ( new HashSet<> ( Arrays.asList ( adUser7, adUser8 ) ) );
			Team team5 = new Team ();
			team5.setName ( "ATM Support" );
			team5.setSupervisor ( user6 );
			team5.setMembers ( new HashSet<> ( Arrays.asList ( adUser9, adUser10 ) ) );
			Team team6 = new Team ();
			team6.setName ( "Network Support" );
			team6.setSupervisor ( user2 );
			team6.setMembers ( new HashSet<> ( Arrays.asList ( adUser2, adUser3 ) ) );
			Team team7 = new Team ();
			team7.setName ( "Human Resource" );
			team7.setSupervisor ( user3 );
			team7.setMembers ( new HashSet<> ( Arrays.asList ( adUser4, adUser5 ) ) );
			Team team8 = new Team ();
			team8.setName ( "Field Agent" );
			team8.setSupervisor ( user4 );
			team8.setMembers ( new HashSet<> ( Arrays.asList ( adUser6, adUser7 ) ) );
			Team team9 = new Team ();
			team9.setName ( "Testers" );
			team9.setSupervisor ( user5 );
			team9.setMembers ( new HashSet<> ( Arrays.asList ( adUser8, adUser9 ) ) );
			Team team10 = new Team ();
			team10.setName ( "Developers" );
			team10.setSupervisor ( user6 );
			team10.setMembers ( new HashSet<> ( Arrays.asList ( adUser10, adUser1 ) ) );
			teamRepository.saveAll ( Arrays.asList ( team1 , team2, team3, team4, team5, team6, team7, team8, team9, team10 ) );
			
			Category category1 = new Category ();
			category1.setTicketClass ( "ATM Error" );
			category1.setAssignee ( team1 );
			category1.setTAT ( "48 Hours" );
			Category category2 = new Category ();
			category2.setTicketClass ( "Backup and Storage" );
			category2.setAssignee ( team2 );
			Category category3 = new Category ();
			category3.setTicketClass ( "Biometrics" );
			category3.setAssignee ( team3 );
			Category category4 = new Category ();
			category4.setTicketClass ( "Business Process Management Platform" );
			category4.setAssignee ( team4 );
			category4.setTAT ( "16 Hours" );
			Category category5 = new Category ();
			category5.setTicketClass ( "Data Centre" );
			category5.setAssignee ( team5 );
			Category category6 = new Category ();
			category6.setTicketClass ( "File Server" );
			category6.setAssignee ( team6 );
			Category category7 = new Category ();
			category7.setTicketClass ( "Finacle" );
			category7.setAssignee ( team7 );
			Category category8 = new Category ();
			category8.setTicketClass ( "Finacle Support" );
			category8.setAssignee ( team8 );
			category8.setTAT ( "5 Hours" );
			Category category9 = new Category ();
			category9.setTicketClass ( "Finone" );
			category9.setAssignee ( team9 );
			category9.setTAT ( "10 Hours" );
			Category category10 = new Category ();
			category10.setTicketClass ( "GSS Scanner" );
			category10.setAssignee ( team10 );
			category10.setTAT ( "4 Hours" );
			Category category11 = new Category ();
			category11.setTicketClass ( "Hardware Malfunction" );
			category11.setAssignee ( team2 );
			category11.setTAT ( "48 Hours" );
			Category category12 = new Category ();
			category12.setTicketClass ( "Network Issue" );
			category12.setAssignee ( team3 );
			category12.setTAT ( "5 Hours" );
			Category category13 = new Category ();
			category13.setTicketClass ( "MoneyGram/Western Union" );
			category13.setAssignee ( team4 );
			Category category14 = new Category ();
			category14.setTicketClass ( "Leave Portal" );
			category14.setAssignee ( team5 );
			category14.setTAT ( "5 Hours" );
			Category category15 = new Category ();
			category15.setTicketClass ( "Other" );
			category15.setAssignee ( createAdminTeam(user2, user3, user4, user5, user6) );
			category15.setTAT ( "3 Hours" );
			categoryRepository.saveAll ( Arrays.asList ( category1 , category2 , category3 , category4 , category5 , category6 , category7 , category8 , category9 , category10 , category11 , category12, category13, category14, category15 ) );
			
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
			CustomerLocation location6 = new CustomerLocation ();
			location6.setCustomerBranch ( "Ikot Branch" );
			location6.setCustomerCity ( "Calabar" );
			location6.setCustomerCountry ( "Nigeria" );
			CustomerLocation location7 = new CustomerLocation ();
			location7.setCustomerBranch ( "Mainland Branch" );
			location7.setCustomerCity ( "Lagos" );
			location7.setCustomerCountry ( "Nigeria" );
			CustomerLocation location8 = new CustomerLocation ();
			location8.setCustomerBranch ( "Off Shore Branch" );
			location8.setCustomerCity ( "Rivers" );
			location8.setCustomerCountry ( "Nigeria" );
			CustomerLocation location9 = new CustomerLocation ();
			location9.setCustomerBranch ( "East Branch" );
			location9.setCustomerCity ( "Imo" );
			location9.setCustomerCountry ( "Nigeria" );
			CustomerLocation location10 = new CustomerLocation ();
			location10.setCustomerBranch ( "Primerose" );
			location10.setCustomerCity ( "Lagos" );
			location10.setCustomerCountry ( "Nigeria" );
			locationRepository.saveAll ( Arrays.asList ( location1, location2, location3, location4, location5, location6, location7, location8, location9, location10) );
			
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
