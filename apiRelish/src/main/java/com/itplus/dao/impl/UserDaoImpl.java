package com.itplus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itplus.entity.Login;
import com.itplus.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<User> findUserById(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_user WHERE id =?";
		return jdbcTemplate.query(sql,new Object[] {id} ,new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE tbl_user SET name=?,email=?,password=?,birthday=?,gender=?,bio=?,avatar_img=? WHERE id=?";
		jdbcTemplate.update(sql, user.getName(),user.getEmail(),user.getPassword(),user.getBirthday(),user.getGender(),user.getBio(),user.getAvatar_img(),user.getId());
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		String sql ="INSERT INTO tbl_user(name,email,password,birthday,gender,bio,avatar_img) values(?,?,?,?,?,?,?)";
		List<User> list = jdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<User>());
		for(User users:list) {
			if(user.getId()==users.getId()|| StringUtils.pathEquals(user.getEmail(),users.getEmail())) {
				return false;
			}
		}
		list.add(user);
		return true;
		
	}

	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO tbl_user(name,email,password,birthday,gender,bio,avatar_img) values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {user.getName(),user.getEmail(),user.getPassword(),user.getBirthday(),user.getGender(),user.getBio(),user.getAvatar_img()});
	}

	@Override
	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM tbl_user; WHERE email='"+login.getEmail()+"' and password='"+login.getPassword()+"'";
		List<User> list = jdbcTemplate.query(sql, new UserMapper());
		return list.size() > 0 ? list.get(0) : null;
	}
	
}

class UserMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		  User user = new User();
			 
		    user.setName(rs.getString("name"));
		    user.setEmail(rs.getString("email"));
		    user.setPassword(rs.getString("password"));
		    user.setBirthday(rs.getString("birthday"));
		    user.setGender(rs.getInt("gender"));
		    user.setBio(rs.getString("bio"));
		    user.setAvatar_img(rs.getString("avatar_img"));
		   
		 
		    return user;
	}

	
	}
