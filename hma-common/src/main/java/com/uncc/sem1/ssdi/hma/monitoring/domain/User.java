package com.uncc.sem1.ssdi.hma.monitoring.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
	private String username;
	private int age;
	private String email;
	private String password;
	private String phoneNumber;
	private String address;
	private String firstName;
	private String lastName;
	private int userid;
	private List<Activity> activities;
	private Date dob;
	
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	{
		this.activities = new ArrayList<Activity>();
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

	public User(int userid) {
		super();
		this.userid = userid;
	}

}
