package com.isoft.customersupport.config;

import com.isoft.customersupport.exception.ApplicationException;
import com.isoft.customersupport.location.CustomerLocationService;
import com.isoft.customersupport.ticket.Ticket;
import com.isoft.customersupport.ticket.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice @Slf4j
public class AdminErrorController {
	
	@Value ("${ticket.type}")
	public String [] ticketTypes;
	@Value("${ticket.priority}")
	public String [] ticketPriorities;
	private final CategoryService categoryService;
	private final CustomerLocationService customerLocationService;
	
	@Autowired
	public AdminErrorController ( CategoryService categoryService , CustomerLocationService customerLocationService ) {
		this.categoryService = categoryService;
		this.customerLocationService = customerLocationService;
	}
	
	@ExceptionHandler (ApplicationException.class)
    @ResponseStatus (HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(final ApplicationException throwable, WebRequest request) {
        log.error("Exception during execution of HelpDesk Application", throwable);

        if (request != null && request.getUserPrincipal () != null) {
            log.error("Displaying Error Page");
			ModelAndView modelAndView = new ModelAndView ("error");
			modelAndView.getModel ().put ( "error", throwable != null ? throwable.getMessage() : "Unknown error" );
            return modelAndView;
        }
        else {
            SecurityContextHolder.getContext().setAuthentication(null);// logout
			log.error("Displaying Login Page");
            return new ModelAndView ("login");
        }
    }
    
//    @ExceptionHandler (MailSendException.class)
//    @ResponseStatus (HttpStatus.GATEWAY_TIMEOUT)
//    public ModelAndView handleMailException(final MailSendException throwable) {
//        log.error("Exception during sending of mail", throwable);
//		ModelAndView modelAndView = new ModelAndView ("error");
//		modelAndView.getModel ().put ( "error", "Internet Connection Timeout. Error Sending Ticket Mail." );
//		return modelAndView;
//    }
    
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxSizeException( MaxUploadSizeExceededException exc) {
		
		ModelAndView modelAndView = new ModelAndView ("index");
		modelAndView.getModel().put("fileMessage", "File too large! Max size is 1MB!");
		modelAndView.getModel().put("isFileLarge", true);
		modelAndView.getModel().put("isIndex", true);
		modelAndView.getModel().put( "ticketTypes", ticketTypes );
		modelAndView.getModel().put( "ticketPriorities", ticketPriorities );
		modelAndView.getModel().put( "categories", categoryService.findAllCategory () );
		modelAndView.getModel().put( "locations", customerLocationService.findAllCustomerLocation () );
		modelAndView.getModel().put( "ticket", new Ticket () );
		log.error ( "Maximum file limit reached" );
		return modelAndView;
	}
}