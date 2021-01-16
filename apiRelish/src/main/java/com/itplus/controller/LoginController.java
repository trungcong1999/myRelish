package com.itplus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.itplus.entity.User;
import com.itplus.service.UserService;

@Controller
public class LoginController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("login");
		User user = new User();
		request.setAttribute("loginBean", user);
		return view;
	}
	
	@RequestMapping(value = "/admin/index", method = RequestMethod.POST)
	public ModelAndView processLogin(@ModelAttribute("loginBean") User user, HttpServletRequest request,@RequestParam("email") String email,@RequestParam("password") String password) {
		ModelAndView view =null;
		if(userService.checklogin(user.getEmail(), user.getPassword())) {
			request.setAttribute("loggedInUser", user.getEmail());
			view = new ModelAndView("index");
		}else {
			request.setAttribute("message", "Invalid ussename or password!");
            view = new ModelAndView("login");
		}
		return view;
		
	}
}
