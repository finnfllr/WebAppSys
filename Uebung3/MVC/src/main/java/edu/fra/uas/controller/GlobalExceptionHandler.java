package edu.fra.uas.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.MissingServletRequestParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ModelAndView handleMissingParams(HttpServletRequest req, MissingServletRequestParameterException exception) {
        log.debug("handleMissingParams() is called");
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURL());
		mav.addObject("timestamp", new Date().toString());
		mav.addObject("status", 500);

		mav.setViewName("support");
		return mav;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ModelAndView handleTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException exception) {
        log.debug("handleTypeMismatch() is called");
        ModelAndView mav = new ModelAndView();
        String fehlerhafterWert = exception.getValue().toString();
        String errorMessage = "Der übergebene Wert '" + fehlerhafterWert + "' ist ungültig für den Parameter '" + exception.getName() + "'.";

        mav.addObject("exception", exception);
        mav.addObject("errorMessage", errorMessage);
        mav.addObject("url", req.getRequestURL());
        mav.addObject("timestamp", new Date().toString());
        mav.addObject("status", 400);
        mav.setViewName("support");
        return mav;
    }

}
