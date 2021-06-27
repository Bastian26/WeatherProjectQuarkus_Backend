package com.vmz.berlin.weather.data;

/**
 * Unterklasse von CurrentObject (keine weitere Verschachtelung)
 * @author basti
 *
 */
public class FeelsLikeObject_NestedInDaily {
	
	private double day;
	private double night;
	private double eve;
	private double morn;
	
	// Constructors
	public FeelsLikeObject_NestedInDaily() {
		
	}

	public FeelsLikeObject_NestedInDaily(double day, double night, double eve, double morn) {
		super();
		this.day = day;
		this.night = night;
		this.eve = eve;
		this.morn = morn;
	}
	
	// Getter & Setter
	public double getDay() {
		return day;
	}

	public void setDay(double day) {
		this.day = day;
	}

	public double getNight() {
		return night;
	}

	public void setNight(double night) {
		this.night = night;
	}

	public double getEve() {
		return eve;
	}

	public void setEve(double eve) {
		this.eve = eve;
	}

	public double getMorn() {
		return morn;
	}

	public void setMorn(double morn) {
		this.morn = morn;
	}
	
	
	
}
