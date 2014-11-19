package com.uncc.sem1.ssdi.hma.monitoring.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Target {
	private int targetId;
	private Date startDate;
	private Date endDate;
	private TargetType targetType;
	private int completedPercentage;
	private int userId;
	public Date getStartDate() {
		return startDate;
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
	public TargetType getTargetType() {
		return targetType;
	}
	public void setTargetType(TargetType targetType) {
		this.targetType = targetType;
	}
	public int getCompletedPercentage() {
		return completedPercentage;
	}
	public void setCompletedPercentage(int completedPercentage) {
		this.completedPercentage = completedPercentage;
	}
	public int getUser() {
		return userId;
	}
	public void setUser(int user) {
		this.userId = user;
	}

}
