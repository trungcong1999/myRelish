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
				film.setPoster_img(rs.getString("poster_img"));
				film.setDescription(rs.getString("description"));
				film.setWriter_id(rs.getInt("writer_id"));
				film.setDirector_id(rs.getInt("director_id"));
				film.setRelease_date(rs.getString("release_date"));
				return film;
			}

		});
		return list;
	}

	@Override
	public void addFilm(Film film) {
		// TODO Auto-generated method stub
		String sql = "insert into tbl_film_info(name,poster_img,description,writer_id,director_id,release_date) values(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, film.getName(),film.getPoster_img(),film.getDescription(),film.getWriter_id(),film.getDirector_id(),film.getRelease_date());
	}

	@Override
	public void updateFilm(Film film) {
		// TODO Auto-generated method stub
		String sql = "update tbl_film_info set name = ?,poster_img= ?,description= ?,writer_id= ?,director_id= ?,release_date= ? where id= ? ";
		jdbcTemplate.update(sql, film.getName(),film.getPoster_img(),film.getDescription(),film.getWriter_id(),film.getDirector_id(),film.getRelease_date(),film.getId());
	}

	@Override
	public void deleteFilm(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from tbl_film_info where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public Film getFilmById(int id) {
		// TODO Auto-generated method stub
		String sql = "Select * from tbl_film_info where id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Film>(Film.class));
	}

	@Override
	public List<Film> findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_film_info WHERE name like CONCAT('%',?,'%')";
		return jdbcTemplate.query(sql, new Object[] { name }, new BeanPropertyRowMapper<Film>(Film.class));

	}

	

}
