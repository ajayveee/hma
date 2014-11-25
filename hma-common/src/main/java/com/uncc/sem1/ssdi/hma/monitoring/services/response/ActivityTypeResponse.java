package com.uncc.sem1.ssdi.hma.monitoring.services.response;

import java.util.List;

import com.uncc.sem1.ssdi.hma.monitoring.domain.ActivityType;

public class ActivityTypeResponse extends HMAResponse {
	private List<ActivityType> activityTypes;

	public List<ActivityType> getActivityTypes() {
		return activityTypes;
	}

	public void setActivityTypes(List<ActivityType> activityTypes) {
		this.activityTypes = activityTypes;
	}

	public ActivityTypeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ActivityTypeResponse(String responseMsg, Status status) {
		super(responseMsg, status);
		// TODO Auto-generated constructor stub
	}
}
