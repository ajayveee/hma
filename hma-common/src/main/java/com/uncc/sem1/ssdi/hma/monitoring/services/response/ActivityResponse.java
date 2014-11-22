package com.uncc.sem1.ssdi.hma.monitoring.services.response;

import java.util.ArrayList;
import java.util.List;

import com.uncc.sem1.ssdi.hma.monitoring.domain.Activity;

public class ActivityResponse extends Response {
	private List<Activity> activities;

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	{
		activities = new ArrayList<Activity>();
	}

	public ActivityResponse() {
		super();
	}

	public ActivityResponse(String responseMsg, Status status) {
		super(responseMsg, status);
	}

}
