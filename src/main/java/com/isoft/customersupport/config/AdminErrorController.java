package com.isoft.customersupport.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AdminErrorController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler (value = {RuntimeException.class})
    @ResponseStatus ( HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final RuntimeException throwable, final Model model, WebRequest request) {
        logger.error("Exception during execution of SpringSecurity application", throwable);
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("error", errorMessage);

        if (request.getUserPrincipal () != null)
            return "error";
        else {
            SecurityContextHolder.getContext().setAuthentication(null);// logout
            return "login";
        }
    }

}