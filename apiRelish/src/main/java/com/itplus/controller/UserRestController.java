package com.itplus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.itplus.entity.User;
import com.itplus.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	UserService userService;

	// Lấy thông tin 1 người dùng dựa vào id
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable int id, HttpServletRequest request) {
		List<User> listUserById = userService.findUserById(id);
		Gson gson = new Gson();
		return gson.toJson(listUserById);
	}	

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String save(@RequestBody User user) {
		try {
			userService.updateUser(user);
			return "update success";
		} catch (Exception e) {
			// TODO: handle exception
			return "update error";
		}
	}

}
