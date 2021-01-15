package com.itplus.entity;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

public class Creator {
	private int id;
	@NotNull
	private String name;
	@Nullable
	private String bio;
	public Creator() {
		super();
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
