package com.itplus.entity;

public class Truyen {
	private int id;
	private String name;
	private int author_id;
	private String description;
	private String cover_img;
	private String 	release_date;
	public Truyen() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Truyen(int id, String name, int author_id, String description, String cover_img, String release_date) {
		super();
		this.id = id;
		this.name = name;
		this.author_id = author_id;
		this.description = description;
		this.cover_img = cover_img;
		this.release_date = release_date;
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
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCover_img() {
		return cover_img;
	}
	public void setCover_img(String cover_img) {
		this.cover_img = cover_img;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	
	
	
}
