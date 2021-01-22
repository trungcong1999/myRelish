package com.itplus.entity;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

public class GameCriteria {
	private int id;
	@NotNull
	private String name;
	@Nullable
	private String alt_name;
	@Nullable
	private String description;
	
	public GameCriteria() {
		super();
	}

	public GameCriteria(int id, String name, String alt_name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.alt_name = alt_name;
		this.description = description;
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

	public String getAlt_name() {
		return alt_name;
	}

	public void setAlt_name(String alt_name) {
		this.alt_name = alt_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
