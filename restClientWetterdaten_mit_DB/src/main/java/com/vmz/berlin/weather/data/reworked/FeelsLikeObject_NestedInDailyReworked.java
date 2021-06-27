package com.vmz.berlin.weather.data.reworked;

import javax.persistence.Embeddable;

@Embeddable
public class FeelsLikeObject_NestedInDailyReworked {
	
	private double dayFL;
	private double nightFL;
	private double eveFL;
	private double mornFL;
	
	// Constructors
	public FeelsLikeObject_NestedInDailyReworked() {
		
	}

	public FeelsLikeObject_NestedInDailyReworked(double dayFL, double nightFL, double eveFL, double mornFL) {
		super();
		this.dayFL = dayFL;
		this.nightFL = nightFL;
		this.eveFL = eveFL;
		this.mornFL = mornFL;
	}
	
	// Getter & Setter
	public double getDayFL() {
		return dayFL;
	}

	public void setDayFL(double dayFL) {
		this.dayFL = dayFL;
	}

	public double getNightFL() {
		return nightFL;
	}

	public void setNightFL(double nightFL) {
		this.nightFL = nightFL;
	}

	public double getEveFL() {
		return eveFL;
	}

	public void setEveFL(double eveFL) {
		this.eveFL = eveFL;
	}

	public double getMornFL() {
		return mornFL;
	}

	public void setMornFL(double mornFL) {
		this.mornFL = mornFL;
	}
	
	
	
}
