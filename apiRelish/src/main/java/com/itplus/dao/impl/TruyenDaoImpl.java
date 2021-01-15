package com.itplus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itplus.entity.Game;
import com.itplus.entity.Truyen;

@Repository
public class TruyenDaoImpl implements TruyenDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Truyen> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_novel_info";
		List<Truyen> listTruyen = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<Truyen>() {

			@Override
			public Truyen mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Truyen truyen = new Truyen();
				truyen.setId(rs.getInt("id"));
				truyen.setName(rs.getString("name"));
				truyen.setAuthor_id(rs.getInt("author_id"));
				truyen.setDescription(rs.getString("description"));
				truyen.setCover_img(rs.getString("cover_img"));
				truyen.setRelease_date(rs.getString("release_date"));
		
				return truyen;
			}

		});
		return listTruyen;
	}

	@Override
	public void addGTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		String sql = "insert into tbl_novel_info(name,author_id ,description,cover_img,release_date) values(?,?,?,?,?)";
		jdbcTemplate.update(sql, truyen.getName(),truyen.getAuthor_id(),truyen.getDescription(),truyen.getCover_img(),truyen.getRelease_date());
	}

	@Override
	public void updateTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		String sql = "update tbl_novel_info set name=?,author_id=?,description=?,cover_img=?,release_date=? where id=?";
		jdbcTemplate.update(sql, truyen.getName(),truyen.getAuthor_id(),truyen.getDescription(),truyen.getCover_img(),truyen.getRelease_date(),truyen.getId());
	}

	@Override
	public void deleteTruyen(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from tbl_novel_info where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public Truyen getTruyenById(int id) {
		// TODO Auto-generated method stub
		String sql = "Select * from tbl_novel_info where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Truyen>(Truyen.class));
	}

	@Override
	public List<Truyen> findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_novel_info WHERE name like CONCAT('%',?,'%')";
		return jdbcTemplate.query(sql, new Object[] { name }, new BeanPropertyRowMapper<Truyen>(Truyen.class));
	}

	@Override
	public List<Truyen> getLatestProducts(int limit) {
		String sql = "SELECT * FROM tbl_novel_info ORDER BY release_date DESC LIMIT ?";
		return jdbcTemplate.query(sql,new Object[] {limit}, new BeanPropertyRowMapper<Truyen>(Truyen.class));
	}

	
}
