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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice@Slf4j
public class AdminErrorController {
	
	@Value ("${ticket.type}")
	public String [] ticketType;
	@Autowired
    private CategoryService categoryService;
	@Autowired
	private CustomerLocationService customerLocationService;
	
	@ExceptionHandler ( ApplicationException.class)
    @ResponseStatus ( HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView exception(final ApplicationException throwable, Model model, WebRequest request) {
        log.error("Exception during execution of HelpDesk Application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");

        if (request != null && request.getUserPrincipal () != null) {
            log.error("Displaying Error Page");
//            model.addAttribute("error", errorMessage);
			ModelAndView modelAndView = new ModelAndView ("error");
			modelAndView.getModel ().put ( "error", errorMessage );
            return modelAndView;
        }
        else {
            SecurityContextHolder.getContext().setAuthentication(null);// logout
			log.error("Displaying Login Page");
            return new ModelAndView ("login");
        }
    }
    
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView handleMaxSizeException(
		  MaxUploadSizeExceededException exc,
		  HttpServletRequest request,
		  HttpServletResponse response) {
		
		ModelAndView modelAndView = new ModelAndView ("index");
		modelAndView.getModel().put("fileMessage", "File too large!");
		modelAndView.getModel().put("isFileLarge", true);
		modelAndView.getModel().put("isIndex", true);
		modelAndView.getModel().put( "types", ticketType );
		modelAndView.getModel().put( "categories", categoryService.findAllCategory () );
		modelAndView.getModel().put( "locations", customerLocationService.findAllCustomerLocation () );
		modelAndView.getModel().put( "ticket", new Ticket () );
		log.error ( "Maximum file limit reached" );
		return modelAndView;
	}

}