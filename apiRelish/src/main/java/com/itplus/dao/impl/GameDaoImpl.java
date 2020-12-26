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

@Repository
public class GameDaoImpl implements GameDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Game> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_game_info";
		List<Game> listGame = jdbcTemplate.query(sql, new Object[] {}, new RowMapper<Game>() {

			@Override
			public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				Game game = new Game();
				game.setId(rs.getInt("id"));
				game.setName(rs.getString("name"));
				game.setRelease_date(rs.getString("release_date"));
				game.setMetacritic_score(rs.getInt("metacritic_score"));
				game.setDescription(rs.getString("description"));
				game.setHeader_img(rs.getString("header_img"));
				game.setPublisher_id(rs.getInt("publisher_id"));
				game.setDeveloper_id(rs.getInt("developer_id"));
				return game;
			}

		});
		return listGame;
	}

	@Override
	public void addGame(Game game) {
		// TODO Auto-generated method stub
		String sql = "insert into tbl_game_info(name,release_date,metacritic_score,description,header_img,publisher_id,developer_id) values(?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, game.getName(),game.getRelease_date(),game.getMetacritic_score(),game.getDescription(),game.getHeader_img(),game.getPublisher_id(),game.getDeveloper_id());
	}

	@Override
	public void updateGame(Game game) {
		// TODO Auto-generated method stub
		String sql = "update tbl_game_info set name=?,release_date=?,metacritic_score=?,description=?,header_img=?,publisher_id=?,developer_id=? where id=?";
		jdbcTemplate.update(sql, game.getName(),game.getRelease_date(),game.getMetacritic_score(),game.getDescription(),game.getHeader_img(),game.getPublisher_id(),game.getDeveloper_id(),game.getId());
	}

	@Override
	public void deleteGame(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from tbl_game_info where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public Game getGameById(int id) {
		// TODO Auto-generated method stub
		String sql = "Select * from tbl_game_info WHERE id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<Game>(Game.class));
	}

	@Override
	public List<Game> findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_game_info WHERE name LIKE CONCAT('%',?,'%')";
		return jdbcTemplate.query(sql, new Object[] { name }, new BeanPropertyRowMapper<Game>(Game.class));
	}

	@Override
	public List<Game> getLatestProducts(int limit) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM tbl_game_info ORDER BY release_date DESC LIMIT ?";
		return jdbcTemplate.query(sql,new Object[] {limit}, new BeanPropertyRowMapper<Game>(Game.class));
	}

	@Override
	public List<Game> getTagId(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM tbl_game_info,tbl_game_tag WHERE tbl_game_info.id=tbl_game_tag.game_id  and tbl_game_tag.tag_id =?";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Game>(Game.class));
	}

	@Override
	public List<Game> getReviewByIdUser(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM tbl_game_review,tbl_game_info WHERE tbl_game_review.id_game=tbl_game_info.id and tbl_game_review.id_user=?";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Game>(Game.class));
	}

	@Override
	public List<Game> getRadomReviewById(int id) {
		// TODO Auto-generated method stub
		String sql ="SELECT * FROM tbl_game_info,tbl_game_review WHERE tbl_game_info.id=tbl_game_review.id_game and tbl_game_review.id=?  ";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Game>(Game.class));
	}





	

}
