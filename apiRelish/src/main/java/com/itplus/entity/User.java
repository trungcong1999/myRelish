package com.itplus.entity;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String 	birthday;
	private int gender;
	private String bio;
	private String avatar_img;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String email, String password, String birthday, int gender, String bio,
			String avatar_img) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.gender = gender;
		this.bio = bio;
		this.avatar_img = avatar_img;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatar_img() {
		return avatar_img;
	}

	public void setAvatar_img(String avatar_img) {
		this.avatar_img = avatar_img;
	}

	

}
