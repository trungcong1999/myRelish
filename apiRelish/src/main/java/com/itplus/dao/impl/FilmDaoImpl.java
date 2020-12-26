package com.itplus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itplus.entity.Film;

@Repository
public class FilmDaoImpl implements FilmDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Film> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_film_info";
		List<Film> list = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<Film>() {

			@Override
			public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Film film = new Film();
				film.setId(rs.getInt("id"));
				film.setName(rs.getString("name"));
				
				return film;
			}

		});
		return list;
	}

	@Override
	public void addFilm(Film film) {
		// TODO Auto-generated method stub
		String sql = "insert into film(name,anh_dai_dien,noi_dung,gioi_thieu,dien_xuat,id_the_loai,id_nha_san_xuat,nam_phat_hanh,sao_danh_gia) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, film.getId());
	}

	@Override
	public void updateFilm(Film film) {
		// TODO Auto-generated method stub
		String sql = "update film set name = ?,anh_dai_dien= ?,noi_dung= ?,gioi_thieu= ?,dien_xuat= ?,id_the_loai= ?,id_nha_san_xuat= ?,nam_phat_hanh= ?,sao_danh_gia= ? where id= ? ";
		jdbcTemplate.update(sql, film.getId());
	}

	@Override
	public void deleteFilm(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from film where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public Film getFilmById(int id) {
		// TODO Auto-generated method stub
		String sql = "Select * from film where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Film>(Film.class));
	}

	@Override
	public List<Film> findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM film WHERE name like CONCAT('%',?,'%')";
		return jdbcTemplate.query(sql, new Object[] { name }, new BeanPropertyRowMapper<Film>(Film.class));

	}

	@Override
	public List<Film> findByCategoryFilmId(String categoryId) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM film,the_loai_film WHERE film.id_the_loai=the_loai_film.id and film.id_the_loai=? ";
		return jdbcTemplate.query(sql, new Object[] { categoryId }, new BeanPropertyRowMapper<Film>(Film.class));
	}

}
