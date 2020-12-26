package com.itplus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itplus.entity.Truyen;

@Repository
public class TruyenDaoImpl implements TruyenDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Truyen> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM truyen";
		List<Truyen> listTruyen = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<Truyen>() {

			@Override
			public Truyen mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Truyen truyen = new Truyen();
				truyen.setId(rs.getInt("id"));
				truyen.setName(rs.getString("name"));
		
				return truyen;
			}

		});
		return listTruyen;
	}

	@Override
	public void addGTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		String sql = "insert into truyen(name,tac_gia,noi_dung,anh_dai_dien,cot_truyen,sao_danh_gia,id_the_loai,nam_phat_hanh) values(?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, truyen.getName());
	}

	@Override
	public void updateTruyen(Truyen truyen) {
		// TODO Auto-generated method stub
		String sql = "update truyeb set name=?,tac_gia=?,noi_dung=?,anh_dai_dien=?,cot_truyen=?,sao_danh_gia=?,id_the_loai=?,nam_phat_hanh=? where id=?";
		jdbcTemplate.update(sql, truyen.getName());
	}

	@Override
	public void deleteTruyen(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from truyen where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public Truyen getTruyenById(int id) {
		// TODO Auto-generated method stub
		String sql = "Select * from truyen where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Truyen>(Truyen.class));
	}

	@Override
	public List<Truyen> findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM truyen WHERE name like CONCAT('%',?,'%')";
		return jdbcTemplate.query(sql, new Object[] { name }, new BeanPropertyRowMapper<Truyen>(Truyen.class));
	}

	@Override
	public List<Truyen> findByCategoryTruyenId(String categoryId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM truyen,the_loai_truyen WHERE truyen.id_the_loai=the_loai_truyen.id and truyen.id_the_loai=? ";
		return jdbcTemplate.query(sql, new Object[] { categoryId }, new BeanPropertyRowMapper<Truyen>(Truyen.class));
	}

}
