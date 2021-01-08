package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.User;

@Service
public interface UserService {
	List<User> findUserById(int id);
	void updateUser(User user);
	User findByEmail(String email);
	boolean checklogin(String email, String password);
}
