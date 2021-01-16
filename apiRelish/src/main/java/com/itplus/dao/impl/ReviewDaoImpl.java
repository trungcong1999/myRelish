package com.itplus.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.itplus.entity.Review;
@Repository
public class ReviewDaoImpl implements ReviewDao{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Review> getReviewById(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT tbl_game_review.id,tbl_game_review.id_game,tbl_game_review.review FROM tbl_game_review WHERE tbl_game_review.id=? ";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	public List<Review> getNumberReview(int id) {
		// TODO Auto-generated method stub
		String sql="SELECT  COUNT(*) FROM tbl_game_review WHERE id_game=?";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	public List<Review> getNumberUserReview(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT COUNT(tbl_game_review.id_user) FROM tbl_game_review WHERE id_game=?";
		return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Review>(Review.class));
	}

	@Override
	public List<Review> getLatestReview(int limit) {
		String sql = "SELECT * FROM tbl_game_review ORDER BY created_time DESC LIMIT ?";
		return jdbcTemplate.query(sql,new Object[] {limit}, new BeanPropertyRowMapper<Review>(Review.class));
	}

}
