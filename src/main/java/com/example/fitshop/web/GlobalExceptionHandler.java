package com.example.fitshop.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleNotFoundException(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", e.getObjectId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ModelAndView handleServerError() {
//        ModelAndView modelAndView = new ModelAndView("500");
//        modelAndView.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
//        return modelAndView;
//    }


}
