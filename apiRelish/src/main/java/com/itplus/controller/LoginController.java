package com.itplus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itplus.entity.Login;
import com.itplus.entity.User;
import com.itplus.service.UserService;

@RestController
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request) {
	    ModelAndView mav = new ModelAndView("login");
	    mav.addObject("login", new Login());
	    return mav;
	  }
	 
	  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public String loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") Login login) {
	    ModelAndView mav = null;
	 
	    User user = userService.validateUser(login);
	 
	    if (null != user) {
	    mav = new ModelAndView("welcome");
	    mav.addObject("firstname", user.getName());
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	 
	    Gson gson = new Gson();
	    return gson.toJson(mav);
	  }
}
