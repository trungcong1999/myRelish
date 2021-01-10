package com.itplus.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itplus.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> findUserById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_user WHERE id =?";
		return jdbcTemplate.query(sql, new Object[] { id }, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbl_user SET name=?,email=?,password=?,birthday=?,gender=?,bio=?,avatar_img=? WHERE id=?";
		jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getBirthday(),
				user.getGender(), user.getBio(), user.getAvatar_img(), user.getId());
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbl_user(name,email,password,birthday,gender,bio,avatar_img) values(?,?,?,?,?,?,?)";
		List<User> list = jdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<User>());
		for (User users : list) {
			if (user.getId() == users.getId() || StringUtils.pathEquals(user.getEmail(), users.getEmail())) {
				return false;
			}
		}
		list.add(user);
		return true;

	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_user WHERE email=?";
		List<User> list = jdbcTemplate.query(sql, new Object[] { email }, new BeanPropertyRowMapper<User>(User.class));
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}



}
