package com.itplus.entity;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

public class GameCriteriaReview {
	private int user_id;
	private int game_id;
	private int criteria_id;
	private int score; 
	@Nullable
	private String review;
	@NotNull
	private String criteria_name;
	
	public GameCriteriaReview() {
		super();
	}

	public GameCriteriaReview(int user_id, int game_id, int criteria_id, int score, String review,
			String criteria_name) {
		super();
		this.user_id = user_id;
		this.game_id = game_id;
		this.criteria_id = criteria_id;
		this.score = score;
		this.review = review;
		this.criteria_name = criteria_name;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getGame_id() {
		return game_id;
	}

	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}

	public int getCriteria_id() {
		return criteria_id;
	}

	public void setCriteria_id(int criteria_id) {
		this.criteria_id = criteria_id;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getCriteria_name() {
		return criteria_name;
	}

	public void setCriteria_name(String criteria_name) {
		this.criteria_name = criteria_name;
	}

	
}
