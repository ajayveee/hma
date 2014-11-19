package com.uncc.sem1.ssdi.hma.monitoring.domain;

import java.util.Date;

public class BiologicalProfile {
	private User user;
	private Date activeFrom;
	private Date activeTill;
	private Gender gender;
	private double systolicBloodPressure;
	private double diastolicBloodPressure;
	private double hips;
	private double neck;
	private double restingHeartRate;
	private double waist;
	private double wrist;
	private double basalMetabolicRate;
	private BloodPressure bloodPressureCategory;
	private double bodyFatPercentage;
	private double bodyMassIndex;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(Date activeFrom) {
		this.activeFrom = activeFrom;
	}
	public Date getActiveTill() {
		return activeTill;
	}
	public void setActiveTill(Date activeTill) {
		this.activeTill = activeTill;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public double getSystolicBloodPressure() {
		return systolicBloodPressure;
	}
	public void setSystolicBloodPressure(double systolicBloodPressure) {
		this.systolicBloodPressure = systolicBloodPressure;
	}
	public double getDiastolicBloodPressure() {
		return diastolicBloodPressure;
	}
	public void setDiastolicBloodPressure(double diastolicBloodPressure) {
		this.diastolicBloodPressure = diastolicBloodPressure;
	}
	public double getHips() {
		return hips;
	}
	public void setHips(double hips) {
		this.hips = hips;
	}
	public double getNeck() {
		return neck;
	}
	public void setNeck(double neck) {
		this.neck = neck;
	}
	public double getRestingHeartRate() {
		return restingHeartRate;
	}
	public void setRestingHeartRate(double restingHeartRate) {
		this.restingHeartRate = restingHeartRate;
	}
	public double getWaist() {
		return waist;
	}
	public void setWaist(double waist) {
		this.waist = waist;
	}
	public double getWrist() {
		return wrist;
	}
	public void setWrist(double wrist) {
		this.wrist = wrist;
	}
	public double getBasalMetabolicRate() {
		return basalMetabolicRate;
	}
	public void setBasalMetabolicRate(double basalMetabolicRate) {
		this.basalMetabolicRate = basalMetabolicRate;
	}
	public BloodPressure getBloodPressureCategory() {
		return bloodPressureCategory;
	}
	public void setBloodPressureCategory(BloodPressure bloodPressureCategory) {
		this.bloodPressureCategory = bloodPressureCategory;
	}
	public double getBodyFatPercentage() {
		return bodyFatPercentage;
	}
	public void setBodyFatPercentage(double bodyFatPercentage) {
		this.bodyFatPercentage = bodyFatPercentage;
	}
	public double getBodyMassIndex() {
		return bodyMassIndex;
	}
	public void setBodyMassIndex(double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}
	
}
