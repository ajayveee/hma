package com.uncc.sem1.ssdi.hma.monitoring.services.response;

import com.uncc.sem1.ssdi.hma.monitoring.domain.User;

public class UserResponse extends Response {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserResponse(User user) {
		super();
		this.user = user;
	}

	public UserResponse() {
		super();
		this.user = new User();
	}

	public UserResponse(String responseMsg, Status status) {
		super(responseMsg, status);
		// TODO Auto-generated constructor stub
	}

}
