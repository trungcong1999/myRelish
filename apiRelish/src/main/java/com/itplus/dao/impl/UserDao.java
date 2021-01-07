package com.itplus.dao.impl;

import java.util.List;

import com.itplus.entity.Login;
import com.itplus.entity.User;

public interface UserDao {
	List<User> findUserById(int id);
	void updateUser(User user);
	boolean addUser(User user);
	void register(User user);
	User validateUser(Login login);
}
