package com.uncc.sem1.ssdi.hma.monitoring.domain;

public enum ActivityType {
	CYCLING(0), RUNNING(1), SWIMMING(2);
	private int value;

	ActivityType(int value) {
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public static ActivityType getType(int value){
		for(ActivityType targetType : values()){
			if(value == targetType.value){
				return targetType;
			}
		}
		return null;
	}
}
