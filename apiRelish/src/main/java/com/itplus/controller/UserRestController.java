package com.itplus.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
		User listUserById = userService.findUserById(id);
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
	
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	public ResponseEntity<User> register(UriComponentsBuilder uriComponentsBuilder,@RequestBody User user,@RequestParam("name") String name,
			  @RequestParam("email") String email,@RequestParam("password") String password,
			  @RequestParam("birthday") String birthday,@RequestParam("gender") String gender,
			  @RequestParam("bio") String bio,@RequestParam("image") String image) {
			userService.addUser(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path("user/register").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<User>(user,headers,HttpStatus.CREATED);
	}
	
	

}
