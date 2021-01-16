package com.itplus.entity;

public class Creator {
	private int id;
	private String name;
	private String bio;
	public Creator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Creator(int id, String name, String bio) {
		super();
		this.id = id;
		this.name = name;
		this.bio = bio;
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
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
}
