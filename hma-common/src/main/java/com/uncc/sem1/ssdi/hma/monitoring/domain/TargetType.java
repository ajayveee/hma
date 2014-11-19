package com.uncc.sem1.ssdi.hma.monitoring.domain;

public enum TargetType {
	CYCLING(0), RUNNING(1), SWIMMING(2);
	private int value;

	TargetType(int value) {
		this.value = value;
	}
	public int getValue(){
		return this.value;
	}
	public static TargetType getType(int value){
		for(TargetType targetType : values()){
			if(value == targetType.value){
				return targetType;
			}
		}
		return null;
	}
}
