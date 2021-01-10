package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.itplus.entity.User;
import com.itplus.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	UserService userService;

	// Lấy thông tin 1 người dùng dựa vào id
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, HttpServletRequest request) {
		List<User> listUserById = userService.findUserById(id);
		Gson gson = new Gson();
		return gson.toJson(listUserById);
	}	

	@RequestMapping(value = "user/save", method = RequestMethod.POST)
	public String save(@RequestBody User user) {
		try {
			userService.updateUser(user);
			return "update success";
		} catch (Exception e) {
			// TODO: handle exception
			return "update error";
		}
	}
	
	@RequestMapping(value = "user/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("login");
		User user = new User();
		request.setAttribute("loginBean", user);
		return view;
	}
	@ResponseBody
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	public ModelAndView processLogin(@ModelAttribute("loginBean") User user, HttpServletRequest request) {
		ModelAndView view =null;
		if(userService.checklogin(user.getEmail(), user.getPassword())) {
			request.setAttribute("loggedInUser", user.getEmail());
			view = new ModelAndView("welcome");
		}else {
			request.setAttribute("message", "Invalid ussename or password!");
            view = new ModelAndView("login");
		}
		return view;
		
	}

}
