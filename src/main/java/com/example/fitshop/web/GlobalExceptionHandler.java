package com.example.fitshop.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalExceptionHandler implements ErrorController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleNotFoundException(ObjectNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", e.getObjectId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    @GetMapping("/403")
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView("403");
        modelAndView.addObject("message", "Access is restricted only to ADMIN users!");
        return modelAndView;
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = Integer.parseInt(status.toString());
        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("message", "The requested page does not exist!");
            return "404";
        } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
            model.addAttribute("message", "You are not authorized to enter here!");
            return "403";
        } else {
            return "error-page";
        }
    }

}
