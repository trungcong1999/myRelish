package com.itplus.entity;

public class Film {
	private int id;
	private String name;
	private String poster_img;
	private String description;
	private int writer_id;
	private int director_id;
	private String release_date;
	public Film(int id, String name, String poster_img, String description, int writer_id, int director_id,
			String release_date) {
		super();
		this.id = id;
		this.name = name;
		this.poster_img = poster_img;
		this.description = description;
		this.writer_id = writer_id;
		this.director_id = director_id;
		this.release_date = release_date;
	}
	public Film() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPoster_img() {
		return poster_img;
	}
	public void setPoster_img(String poster_img) {
		this.poster_img = poster_img;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getWriter_id() {
		return writer_id;
	}
	public void setWriter_id(int writer_id) {
		this.writer_id = writer_id;
	}
	public int getDirector_id() {
		return director_id;
	}
	public void setDirector_id(int director_id) {
		this.director_id = director_id;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
}
