package com.itplus.entity;

import javax.validation.constraints.NotNull;

public class GameInfo {
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
	
	private String publisher_name;
	private String developer_name;
	private int count_in_collection;
	private int count_review_article;
	private float score;
	
	@NotNull
	public GameInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GameInfo(int id, String name, String release_date, int metacritic_score, String description,
			String header_img, String publisher_name, String developer_name, int count_in_collection,
			int count_review_article, float score) {
		super();
		this.id = id;
		this.name = name;
		this.release_date = release_date;
		this.metacritic_score = metacritic_score;
		this.description = description;
		this.header_img = header_img;
		this.publisher_name = publisher_name;
		this.developer_name = developer_name;
		this.count_in_collection = count_in_collection;
		this.count_review_article = count_review_article;
		this.score = score;
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

	public String getPublisher_name() {
		return publisher_name;
	}

	public void setPublisher_name(String publisher_name) {
		this.publisher_name = publisher_name;
	}

	public String getDeveloper_name() {
		return developer_name;
	}

	public void setDeveloper_name(String developer_name) {
		this.developer_name = developer_name;
	}

	public int getCount_in_collection() {
		return count_in_collection;
	}

	public void setCount_in_collection(int count_in_collection) {
		this.count_in_collection = count_in_collection;
	}

	public int getCount_review_article() {
		return count_review_article;
	}

	public void setCount_review_article(int count_review_article) {
		this.count_review_article = count_review_article;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
}
