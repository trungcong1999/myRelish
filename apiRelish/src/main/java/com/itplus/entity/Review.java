package com.itplus.entity;

public class Review {
	private int id;
	private int id_game;
	private int id_user ;
	private String title;
	private String summary;
	private String review;
	private String created_time;
	private String 	last_edit_time;
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Review(int id, int id_game, int id_user, String title, String summary, String review, String created_time,
			String last_edit_time) {
		super();
		this.id = id;
		this.id_game = id_game;
		this.id_user = id_user;
		this.title = title;
		this.summary = summary;
		this.review = review;
		this.created_time = created_time;
		this.last_edit_time = last_edit_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_game() {
		return id_game;
	}
	public void setId_game(int id_game) {
		this.id_game = id_game;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getLast_edit_time() {
		return last_edit_time;
	}
	public void setLast_edit_time(String last_edit_time) {
		this.last_edit_time = last_edit_time;
	}
	
	
}
