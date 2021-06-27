package com.vmz.berlin.weather.data.reworked;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable  //Versuch nestes Objects with hibernate
public class CurrentObjectReworked {
	
	// Attributes
	//private int dt;
	private int sunrise;
	private int sunset;
	private double temp;
	private double feelsLike;
	private int pressure;
	private int humidity;
	private double dewPoint;
	private double uvi;
	private int clouds;
	private int visibility;
	private double windSpeed;
	private int windDeg;
	@Embedded
	private CurrentWeatherObjectReworked weather;
	
	// Default Constructor
	public CurrentObjectReworked() {
		
	}
	
	// Constructor
	public CurrentObjectReworked(/*int dt,*/ int sunrise, int sunset, double temp, double feelsLike, int pressure, int humidity,
			double dewPoint, double uvi, int clouds, int visibility, double windSpeed, int windDeg,
			CurrentWeatherObjectReworked weather) {
		super();
		//this.dt = dt;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.pressure = pressure;
		this.humidity = humidity;
		this.dewPoint = dewPoint;
		this.uvi = uvi;
		this.clouds = clouds;
		this.visibility = visibility;
		this.windSpeed = windSpeed;
		this.windDeg = windDeg;
//		this.weather = weather;
	}

	// ###   Getter & Setter   ###
//	public int getDt() {
//		return dt;
//	}
//
//	public void setDt(int dt) {
//		this.dt = dt;
//	}

	public int getSunrise() {
		return sunrise;
	}

	public void setSunrise(int sunrise) {
		this.sunrise = sunrise;
	}

	public int getSunset() {
		return sunset;
	}

	public void setSunset(int sunset) {
		this.sunset = sunset;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public double getDewPoint() {
		return dewPoint;
	}

	public void setDewPoint(double dewPoint) {
		this.dewPoint = dewPoint;
	}

	public double getUvi() {
		return uvi;
	}

	public void setUvi(double uvi) {
		this.uvi = uvi;
	}

	public int getClouds() {
		return clouds;
	}

	public void setClouds(int clouds) {
		this.clouds = clouds;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public int getWindDeg() {
		return windDeg;
	}

	public void setWindDeg(int windDeg) {
		this.windDeg = windDeg;
	}

	public CurrentWeatherObjectReworked getWeather() {
		return weather;
	}

	public void setWeather(CurrentWeatherObjectReworked weather) {
		this.weather = weather;
	}


	
	
}
