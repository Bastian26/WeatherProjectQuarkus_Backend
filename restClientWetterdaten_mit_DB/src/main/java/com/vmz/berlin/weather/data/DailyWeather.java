package com.vmz.berlin.weather.data;

import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;

/**
 * Klasse für das tägliche Wetter 
 * @author basti
 *
 */
public class DailyWeather {
	
	private int dt;
	private int sunrise;
	private int sunset;
	private TempObject_NestedInDaily temp;
	@JsonbProperty("feels_like")
	private FeelsLikeObject_NestedInDaily feelsLike;
	private int pressure;
	private int humidity;
	@JsonbProperty("dew_point")
	private double dewPoint;
	@JsonbProperty("wind_speed")
	private double windSpeed;
	@JsonbProperty("wind_deg")
	private int windDeg;
	private ArrayList<WeatherObject_NestedInDaily> weather;
	private int clouds;
	private double pop;
	private double uvi;
	
	// Constructors
	public DailyWeather() {
		
	}

	public DailyWeather(int dt, int sunrise, int sunset, TempObject_NestedInDaily temp,
			FeelsLikeObject_NestedInDaily feelsLike, int pressure, int humidity, double dewPoint, double windSpeed,
			int windDeg, ArrayList<WeatherObject_NestedInDaily> weather, int clouds, double pop, double uvi) {
		super();
		this.dt = dt;
		this.sunrise = sunrise;
		this.sunset = sunset;
		this.temp = temp;
		this.feelsLike = feelsLike;
		this.pressure = pressure;
		this.humidity = humidity;
		this.dewPoint = dewPoint;
		this.windSpeed = windSpeed;
		this.windDeg = windDeg;
		this.weather = weather;
		this.clouds = clouds;
		this.pop = pop;
		this.uvi = uvi;
	}

	// Getter & Setter
	public int getDt() {
		return dt;
	}

	public void setDt(int dt) {
		this.dt = dt;
	}

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

	public TempObject_NestedInDaily getTemp() {
		return temp;
	}

	public void setTemp(TempObject_NestedInDaily temp) {
		this.temp = temp;
	}

	public FeelsLikeObject_NestedInDaily getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(FeelsLikeObject_NestedInDaily feelsLike) {
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

	public ArrayList<WeatherObject_NestedInDaily> getWeather() {
		return weather;
	}

	public void setWeather(ArrayList<WeatherObject_NestedInDaily> weather) {
		this.weather = weather;
	}

	public int getClouds() {
		return clouds;
	}

	public void setClouds(int clouds) {
		this.clouds = clouds;
	}

	public double getPop() {
		return pop;
	}

	public void setPop(double pop) {
		this.pop = pop;
	}

	public double getUvi() {
		return uvi;
	}

	public void setUvi(double uvi) {
		this.uvi = uvi;
	}
	
	
}
