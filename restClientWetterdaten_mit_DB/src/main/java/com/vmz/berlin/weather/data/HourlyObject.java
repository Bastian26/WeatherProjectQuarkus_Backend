package com.vmz.berlin.weather.data;

import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Unterklasse von CurrentWeather für das stündliche Wetter
 * @author basti
 *
 */
public class HourlyObject {
	
	private int dt;
	private double temp;
	@JsonbProperty("feels_like")
	private double feelsLike;
	private int pressure;
	private int humidity;
	@JsonbProperty("dew_point")
	private double dewPoint;
	private int clouds;
	private int visibility;
	@JsonbProperty("wind_speed")
	private double windSpeed;
	@JsonbProperty("wind_deg")
	private int windDeg;
	private ArrayList<HourlyNestedObject> weather;
	private double pop;
	
	//Constructors
	public HourlyObject() {
		
	}

	public HourlyObject(int dt, double temp, double feelsLike, int pressure, int humidity, double dewPoint, int clouds,
			int visibility, double windSpeed, int windDeg, ArrayList<HourlyNestedObject> weather, int pop) {
		super();
		this.dt = dt;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.pressure = pressure;
		this.humidity = humidity;
		this.dewPoint = dewPoint;
		this.clouds = clouds;
		this.visibility = visibility;
		this.windSpeed = windSpeed;
		this.windDeg = windDeg;
		this.weather = weather;
		this.pop = pop;
	}
	
	// Getter & Setter
	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
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

	public ArrayList<HourlyNestedObject> getWeather() {
		return weather;
	}

	public void setWeather(ArrayList<HourlyNestedObject> weather) {
		this.weather = weather;
	}

	public double getPop() {
		return pop;
	}

	public void setPop(double pop) {
		this.pop = pop;
	}
	
	
}
