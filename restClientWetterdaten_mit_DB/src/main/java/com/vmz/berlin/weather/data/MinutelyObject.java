package com.vmz.berlin.weather.data;

/**
 * Unterklasse von CurrentWeather für minütliches Wetter (keine weitere Verschachtelung)
 * @author basti
 *
 */
public class MinutelyObject {
	
	private int dt;
	private int precipitation;
	
	// Constructors
	public MinutelyObject() {
		
	}

	public MinutelyObject(int dt, int precipitation) {
		super();
		this.dt = dt;
		this.precipitation = precipitation;
	}
	
	// Getter & Setter
	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

	public int getPrecipitation() {
		return precipitation;
	}

	public void setPrecipitation(int precipitation) {
		this.precipitation = precipitation;
	}
	
	
}
