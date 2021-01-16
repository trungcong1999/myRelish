package com.itplus.dao.impl;

import java.io.Console;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.itplus.entity.CountObject;
import com.itplus.entity.FloatValueObject;
import com.itplus.entity.Creator;
import com.itplus.entity.Game;
import com.itplus.entity.GameCriteriaReview;
import com.itplus.entity.GameReviewArticle;

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
		String sql = "delete from tbl_game_tag where game_id = ?";
		jdbcTemplate.update(sql, id);
		sql = "delete from tbl_game_review where id_game = ?";
		jdbcTemplate.update(sql, id);
		sql = "delete from tbl_game_rate_criteria where game_id = ?";
		jdbcTemplate.update(sql, id);
		sql = "delete from tbl_game_info where id = ?";
		jdbcTemplate.update(sql, id);
	}

	@Override
	public List<Game> getGameById(int id) {
		String sql = "Select * from tbl_game_info WHERE id = ?";
		return jdbcTemplate.query(sql, new Object[] { id }, new BeanPropertyRowMapper<Game>(Game.class));
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

	@Override
	public List<GameCriteriaReview> getAllCriteria(int gameId, int userId) {
		String sql = "SELECT tbl_game_rate_criteria.*, tbl_game_criteria.name AS criteria_name"
				+ " FROM tbl_game_rate_criteria INNER JOIN tbl_game_criteria "
				+ "ON tbl_game_rate_criteria.criteria_id=tbl_game_criteria.id "
				+ "WHERE tbl_game_rate_criteria.game_id=? AND tbl_game_rate_criteria.user_id=?";
		return jdbcTemplate.query(sql,new Object[] {gameId, userId}, new BeanPropertyRowMapper<GameCriteriaReview>(GameCriteriaReview.class));
	}
	public List<Game> getAllGameName() {
		// TODO Auto-generated method stub
		String sql="SELECT tbl_game_info.*,tbl_creator.name as username FROM tbl_game_info,tbl_creator WHERE tbl_game_info.publisher_id=tbl_creator.id AND tbl_game_info.developer_id=tbl_creator.id";
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
				game.setUser_name(rs.getString("username"));
				return game;
			}

		});
		return listGame;
	}

	@Override
	public List<Creator> getAllCreator() {
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


	@Override
	public List<GameReviewArticle> getAllReviewArticles(int userId, int gameId) {
		String sql = "SELECT * FROM tbl_game_review WHERE id_game=? AND id_user=?";
		return jdbcTemplate.query(sql,new Object[] {gameId, userId}, new BeanPropertyRowMapper<GameReviewArticle>(GameReviewArticle.class));
	}

	@Override
	public CountObject countReviewArticlesByGameId(int gameId) {
		String sql = "SELECT COUNT(id) AS count_value FROM tbl_game_review WHERE id_game=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {gameId}, new BeanPropertyRowMapper<CountObject>(CountObject.class));
	}

	@Override
	public CountObject countInPeopleCollectionByGameId(int gameId) {
		String sql = "SELECT COUNT(DISTINCT tbl_union.user_id) AS count_value FROM\r\n" + 
				"((SELECT id_user as user_id, id_game as game_id FROM tbl_game_review WHERE id_game=?)\r\n" + 
				"UNION ALL\r\n" + 
				"(SELECT user_id, game_id FROM tbl_game_rate_criteria WHERE game_id=?)) AS tbl_union";
		return jdbcTemplate.queryForObject(sql,new Object[] {gameId, gameId}, new BeanPropertyRowMapper<CountObject>(CountObject.class));
	}

	@Override
	public FloatValueObject getAverageScoreByGameId(int gameId) {
		String sql = "SELECT AVG(score) AS value FROM tbl_game_rate_criteria WHERE game_id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {gameId}, new BeanPropertyRowMapper<FloatValueObject>(FloatValueObject.class));
	}

	@Override
	public CountObject checkIsGameInCollection(int gameId, int userId) {
		String sql = "SELECT (EXISTS(SELECT * FROM tbl_game_review WHERE id_game=? AND id_user=?)\r\n" + 
				"OR EXISTS(SELECT * FROM tbl_game_rate_criteria WHERE game_id=? AND user_id=?)) AS count_value";
		return jdbcTemplate.queryForObject(sql,new Object[] {gameId,userId,gameId,userId}, new BeanPropertyRowMapper<CountObject>(CountObject.class));
	}
}
