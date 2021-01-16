package com.itplus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itplus.entity.Creator;

@Repository
public class CreatorDaoImpl implements CreatorDao{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Creator> getAll() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM tbl_creator";
		List<Creator> listCretor=jdbcTemplate.query(sql,new Object[] {},new  RowMapper<Creator>() {

			@Override
			public Creator mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Creator creator = new Creator();
				creator.setId(rs.getInt("id"));
				creator.setName(rs.getString("name"));
				creator.setBio(rs.getString("bio"));
				return creator;
			}
			
		});
		return listCretor;
	}
}
