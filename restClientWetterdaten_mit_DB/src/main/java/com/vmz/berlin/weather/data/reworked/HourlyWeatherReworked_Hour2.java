package com.vmz.berlin.weather.data.reworked;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Klasse für das Wetter der nächsten Stunde, wurde stark gekürzt, da die Information nicht mehr bneötigt wurden
 * @author Bastian Struggl
 * @Entity Gibt an, dass es sich um eine Entität (Tabelle) handelt
 */
@Entity
public class HourlyWeatherReworked_Hour2 {
	
	@Id // Markiert die ID für Hibernate
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Wird automatisch eine ID generieren
	private Integer idHourly;
	// ------------------------
	
	private double temp;
	private int clouds;
	@Embedded
	private HourlyNestedObjectReworked weather;
	
	// Constructors
	public HourlyWeatherReworked_Hour2() {
		super();
	}

	public HourlyWeatherReworked_Hour2(Integer idHourly, double temp, int clouds, HourlyNestedObjectReworked weather) {
		super();
		this.idHourly = idHourly;
		this.temp = temp;
		this.clouds = clouds;
		this.weather = weather;
	}

	// Getter & Setter
	public Integer getIdHourly() {
		return idHourly;
	}

	public void setIdHourly(Integer idHourly) {
		this.idHourly = idHourly;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public int getClouds() {
		return clouds;
	}

	public void setClouds(int clouds) {
		this.clouds = clouds;
	}

	public HourlyNestedObjectReworked getWeather() {
		return weather;
	}

	public void setWeather(HourlyNestedObjectReworked weather) {
		this.weather = weather;
	}
	
	
}
