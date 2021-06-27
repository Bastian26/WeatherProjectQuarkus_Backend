package com.vmz.berlin.weather.data.reworked;

import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DailyWeatherReworked_Day6 {
	
	@Id // Markiert die ID für Hibernate
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Wird automatisch eine ID generieren
	private Integer idDaily;
	// ------------------------
	
	private int dt;
	private int sunrise;
	private int sunset;
	@Embedded
	private TempObject_NestedInDailyReworked temp;
	@Embedded
	private FeelsLikeObject_NestedInDailyReworked feelsLike;
	private int pressure;
	private int humidity;
	private double dewPoint;
	private double windSpeed;
	private int windDeg;
	@Embedded
	private WeatherObject_NestedInDailyReworked weather;
	private int clouds;
	private double pop;
	private double uvi;
	
	// Constructors
	public DailyWeatherReworked_Day6() {
		
	}

	public DailyWeatherReworked_Day6(int dt, int sunrise, int sunset, TempObject_NestedInDailyReworked temp,
			FeelsLikeObject_NestedInDailyReworked feelsLike, int pressure, int humidity, double dewPoint, double windSpeed,
			int windDeg, WeatherObject_NestedInDailyReworked weather, int clouds, double pop, double uvi) {
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
	
	public Integer getIdDaily() {
		return idDaily;
	}

	public void setId(Integer idDaily) {
		this.idDaily = idDaily;
	}
	
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

	public TempObject_NestedInDailyReworked getTemp() {
		return temp;
	}

	public void setTemp(TempObject_NestedInDailyReworked temp) {
		this.temp = temp;
	}

	public FeelsLikeObject_NestedInDailyReworked getFeelsLike() {
		return feelsLike;
	}

	public void setFeelsLike(FeelsLikeObject_NestedInDailyReworked feelsLike) {
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

	public WeatherObject_NestedInDailyReworked getWeather() {
		return weather;
	}

	public void setWeather(WeatherObject_NestedInDailyReworked weather) {
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
