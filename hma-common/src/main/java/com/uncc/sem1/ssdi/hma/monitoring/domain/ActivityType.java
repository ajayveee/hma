package com.uncc.sem1.ssdi.hma.monitoring.domain;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityType {
	private int activityTypeId;
	private String activity;
	private double caloriesBurned;
	public int getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(int activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public double getCaloriesBurned() {
		return caloriesBurned;
	}
	public void setCaloriesBurned(double caloriesBurned) {
		this.caloriesBurned = caloriesBurned;
	}
	public ActivityType(int activityTypeId) {
		super();
		this.activityTypeId = activityTypeId;
	}
	public ActivityType() {
		super();
	}
	
}
