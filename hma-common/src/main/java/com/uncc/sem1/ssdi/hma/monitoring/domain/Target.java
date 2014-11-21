package com.uncc.sem1.ssdi.hma.monitoring.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Target {
	private int targetId;
	private Date startDate;
	private Date endDate;
	private ActivityType activityType;
	private int completedPercentage;
	private double calories;
	private User user;

	public void setUser(User user) {
		this.user = user;
	}

	public double getCalories() {
		return calories;
	}

	public void setCalories(double calories) {
		this.calories = calories;
	}

	public Date getStartDate() {
		return startDate;
	}

	public int getTargetId() {
		return targetId;
	}

	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType targetType) {
		this.activityType = targetType;
	}

	public int getCompletedPercentage() {
		return completedPercentage;
	}

	public void setCompletedPercentage(int completedPercentage) {
		this.completedPercentage = completedPercentage;
	}

	public User getUser() {
		return user;
	}

}
