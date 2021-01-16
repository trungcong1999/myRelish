package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.User;

@Service
public interface UserService {
	List<User> getAllUser();
	User findUserById(int id);
	void updateUser(User user);
	void addUser(User user);
	User findByEmail(String email);
	boolean checklogin(String email, String password);
	int deleteUser(int id);
}
