package com.itplus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itplus.entity.Login;
import com.itplus.entity.User;

@Service
public interface UserService {
	List<User> findUserById(int id);
	void updateUser(User user);
	void register(User user);
	User validateUser(Login login);
}
