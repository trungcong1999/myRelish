package com.itplus.dao.impl;

import java.util.List;

import com.itplus.entity.User;

public interface UserDao {
	List<User> getAllUser();
	User findUserById(int id);
	void updateUser(User user);
	void addUser(User user);
	User findByEmail(String email);
	int deleteUser(int id);

}
