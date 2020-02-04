package com.isoft.customersupport.usermngt;

import com.isoft.customersupport.config.Messages;
import com.isoft.customersupport.location.CustomerLocation;
import com.isoft.customersupport.location.CustomerLocationService;
import com.isoft.customersupport.team.Team;
import com.isoft.customersupport.team.TeamService;
import com.isoft.customersupport.ticket.Ticket;
import com.isoft.customersupport.ticket.TicketService;
import com.isoft.customersupport.ticket.category.Category;
import com.isoft.customersupport.ticket.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller @Slf4j
public class UserController {
	
	@Value("${ticket.type}")
	public String [] ticketType;
	
	@Value("${ticket.status}")
	public String [] ticketStatus;
	
	private final CustomerLocationService customerLocationService;
	private final CategoryService categoryService;
	private final TeamService teamService;
	private final Messages messages;
	private final UserService userService;
	private final TicketService ticketService;
	private final ActiveDirectoryRepo activeDirectoryRepo;
	
	
	@Autowired
	public UserController ( CustomerLocationService customerLocationService , CategoryService categoryService , TeamService teamService , Messages messages , UserService userService , TicketService ticketService, ActiveDirectoryRepo activeDirectoryRepo ) {
		this.customerLocationService = customerLocationService;
		this.categoryService = categoryService;
		this.teamService = teamService;
		this.messages = messages;
		this.userService = userService;
		this.ticketService = ticketService;
		this.activeDirectoryRepo = activeDirectoryRepo;
	}
	
	@GetMapping("/")
	public String getIndexPage( Model model, RedirectAttributes redirectAttributes ){
//		if(Util.getCurrentUser ().getIsFirstLogin () != null) {
//			redirectAttributes.addFlashAttribute ( "password",new User ());
//			redirectAttributes.addFlashAttribute ( "isPasswordChange",true );
//			return "redirect:/login";
//		}
		loadTicketAttributes ( model );
		model.addAttribute ( "isIndex", true );
		loadAllModelAttributes ( model );
		return "index";
	}
	
	@PostMapping("/save-ticket")
	public String createTicket(@ModelAttribute ("ticket") @Valid Ticket cmd, BindingResult result, RedirectAttributes redirectAttributes, Model model){
		if (result.hasErrors()) {
			log.warn("Error occurred creating Ticket {}", result);
			loadTicketAttributes ( model );
			model.addAttribute("isIndex",true);
			return "index";
		}
		ticketService.createTicket ( cmd );
		redirectAttributes.addFlashAttribute("message", messages.get("ticket.added.ok"));
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String getLoginPage(Model model){
		model.addAttribute ( "password",new User() );
		return "login";
	}
	
//	@GetMapping("/error")
//	public String getErrorPage(){
//		return "error";
//	}
	
	@GetMapping("/open-tickets")
	public String viewOpenTickets(){
		return "open-tickets";
	}
	
	@GetMapping("/unseen-tickets")
	public String viewUnseenTickets(){
		return "unseen-tickets";
	}
	
	@GetMapping("/all-tickets")
	public String viewAllTickets(){
		return "ticket-history";
	}
	
	@GetMapping("/single-ticket")
	public String viewSingleTicket(){
		return "single-ticket";
	}
	
	@GetMapping("/new-ticket-category")
	public String addNewTicketClass(Model model){
		model.addAttribute ( "isNewTicketCategory",true );
		loadAllModelAttributes ( model );
		return "index";
	}
	
	@PostMapping ("/save-ticket-category")
	public String saveNewTicketClass(@ModelAttribute ("ticketCategory") @Valid Category cmd, BindingResult result, RedirectAttributes redirectAttributes, Model model){
		if (result.hasErrors()) {
			log.warn("Error occurred creating Category {}", result);
			model.addAttribute("isNewTicketCategory",true);
			return "index";
		}
		categoryService.createCategory ( cmd );
		redirectAttributes.addFlashAttribute("message", messages.get("category.added.ok"));
		return "redirect:/";
	}
	
	@GetMapping("/new-team")
	public String addNewSupportTeam(Model model){
		model.addAttribute ( "isNewSupportTeam",true );
		loadAllModelAttributes ( model );
		return "index";
	}
	
	@PostMapping ("/save-team")
	public String saveNewTeam(@ModelAttribute ("team") @Valid Team cmd, BindingResult result, RedirectAttributes redirectAttributes, Model model){
		if (result.hasErrors()) {
			log.warn("Error occurred creating Team {}", result);
			model.addAttribute("isNewSupportTeam",true);
			return "index";
		}
		teamService.createTeam ( cmd );
		redirectAttributes.addFlashAttribute("message", messages.get("team.added.ok"));
		return "redirect:/";
	}
	
	@GetMapping("/new-location")
	public String addNewLocation(Model model){
		model.addAttribute ( "isNewLocation",true );
		loadAllModelAttributes ( model );
		return "index";
	}
	
	@PostMapping ("/save-location")
	public String saveNewLocation(@ModelAttribute ("location") @Valid CustomerLocation cmd, BindingResult result, RedirectAttributes redirectAttributes, Model model){
		if (result.hasErrors()) {
			log.warn("Error occurred creating Team {}", result);
			model.addAttribute("isNewLocation",true);
			return "index";
		}
		customerLocationService.createCustomerLocation ( cmd );
		redirectAttributes.addFlashAttribute("message", messages.get("location.added.ok"));
		return "redirect:/";
	}
	
	@GetMapping("/new-admin")
	public String addNewAdminUser(Model model){
		model.addAttribute ( "isNewAdminUser",true );
		loadAllModelAttributes ( model );
		return "index";
	}
	
	@PostMapping ("/save-admin")
	public String saveNewAdmin(@ModelAttribute ("admin") @Valid User cmd, BindingResult result, RedirectAttributes redirectAttributes, Model model){
		if(userService.findAllUsers().stream().anyMatch ( u -> u.getEmail ().equalsIgnoreCase ( cmd.getEmail () ) ) )
			result.addError ( new ObjectError ( "email","Admin with email " +cmd.getEmail () +" already exists" ) );
		
		if (result.hasGlobalErrors () || result.hasFieldErrors ( "email" )) {
			log.warn("Error occurred creating Admin {}", result);
			model.addAttribute("isNewAdminUser",true);
			return "index";
		}
		userService.createUser ( cmd );
		redirectAttributes.addFlashAttribute("message", messages.get("admin.added.ok"));
		return "redirect:/";
	}
	
	
//	@GetMapping("/change-password")
//	public String changePassword(Model model){
//		model.addAttribute ( "isChangePassword",true );
//		loadAllModelAttributes ( model );
//		return "index";
//	}
	@PostMapping ("/save-password")
	public String savePassword(@ModelAttribute ("password") @Valid User cmd, BindingResult result, RedirectAttributes redirectAttributes, Model model){
		User user = userService.findUserByEmail (cmd.getEmail ());
		if(user != null && !user.getPassword ().equalsIgnoreCase ( cmd.getOldPassword () )){
			result.addError ( new ObjectError ( "oldPassword" , "Invalid Old Password supplied" ) );
		}
		
		if (result.hasErrors()) {
			log.warn("Error occurred changing password {}", result);
			model.addAttribute("isPasswordChange",true);
			return "login";
		}
		userService.updateUser ( cmd );
		redirectAttributes.addFlashAttribute("message", messages.get("password.changed.ok"));
		return "redirect:/";
	}
	
	private void loadAllModelAttributes ( Model model ) {
//		model.addAttribute ( "unseenTicketCount", 0 );
//		model.addAttribute ( "openTicketCount", 0 );
//		model.addAttribute ( "allTicketCount", 0 );
		model.addAttribute ( "ticket", new Ticket () );
		model.addAttribute ( "ticketCategory",new Category () );
		model.addAttribute ( "team",new Team () );
		model.addAttribute ( "location",new CustomerLocation () );
		model.addAttribute ( "admin",new User () );
		model.addAttribute ( "teams",teamService.findAllTeam () );
		model.addAttribute ( "supervisors",userService.findAllUsers () );
		model.addAttribute ( "members",activeDirectoryRepo.findAll () );
	}
	
	public void loadTicketAttributes ( Model model ) {
		model.addAttribute ( "types", ticketType );
//		model.addAttribute ( "classes", categoryService.findAllCategory () );
//		model.addAttribute ( "statuses", ticketStatus );
		model.addAttribute ( "categories", categoryService.findAllCategory () );
		model.addAttribute ( "locations", customerLocationService.findAllCustomerLocation () );
	}
	
	@ModelAttribute("openTicketCount")
	public int getOpenTicketCount(){
		return ticketService.findAllOpenTicket ().size ();
	}
	
	@ModelAttribute("unseenTicketCount")
	public int getUnseenTicketCount(){
		return ticketService.findAllUnseenTicket ().size ();
	}
	
	@ModelAttribute("allTicketCount")
	public int getAllTicketCount(){
		return ticketService.findAllTicket ().size ();
	}
}