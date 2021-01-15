package com.itplus.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itplus.entity.Creator;

@Repository
public class CreatorDaoImpl implements CreatorDao {
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Creator getById(int id) {
		String sql ="SELECT * FROM tbl_creator WHERE id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {id}, new BeanPropertyRowMapper<Creator>(Creator.class));
	}
}
