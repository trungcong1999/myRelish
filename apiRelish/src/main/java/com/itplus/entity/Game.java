package com.itplus.entity;

import javax.validation.constraints.NotNull;

public class Game {
	private int id;
	@NotNull
	private String name;
	@NotNull
	private String release_date;
	@NotNull
	private int metacritic_score;
	@NotNull
	private String description;
	@NotNull
	private String header_img;
	
	private int publisher_id;
	private int developer_id;
	private String user_name;
	@NotNull
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Game(int id, String name, String release_date, int metacritic_score, String description, String header_img,
			int publisher_id, int developer_id, String user_name) {
		super();
		this.id = id;
		this.name = name;
		this.release_date = release_date;
		this.metacritic_score = metacritic_score;
		this.description = description;
		this.header_img = header_img;
		this.publisher_id = publisher_id;
		this.developer_id = developer_id;
		this.user_name = user_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public int getMetacritic_score() {
		return metacritic_score;
	}
	public void setMetacritic_score(int metacritic_score) {
		this.metacritic_score = metacritic_score;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHeader_img() {
		return header_img;
	}
	public void setHeader_img(String header_img) {
		this.header_img = header_img;
	}
	public int getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	}
	public int getDeveloper_id() {
		return developer_id;
	}
	public void setDeveloper_id(int developer_id) {
		this.developer_id = developer_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	

	
}
