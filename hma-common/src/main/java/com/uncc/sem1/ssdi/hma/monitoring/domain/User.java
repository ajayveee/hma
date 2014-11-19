package com.uncc.sem1.ssdi.hma.monitoring.domain;

public class User {
	private String username;
	private int age;
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String username, int age, String email) {
		super();
		this.username = username;
		this.age = age;
		this.email = email;
	}

	public User() {
		super();
	}

}
