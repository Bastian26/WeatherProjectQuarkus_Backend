package com.vmz.berlin.weather.data.reworked;


import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.codec.binary.Base32;

import com.vmz.berlin.weather.data.CurrentObject;
import com.vmz.berlin.weather.data.CurrentWeatherObject;

/**
 * CurrentWeather (extern) wurde in ein eigenes Klassenformat (intern) umgewandelt. Hier kann man nun die Annotations für Hibernate
 * nutzen, damit aus diesen Klassen automatisch Tabellen gebildet werden (ORM)
 * @author basti
 * @Entity Gibt an, dass es sich um eine Entität (Tabelle) handelt
 */
@Entity
public class CurrentWeatherReworked {
	
	// NEW ------------------
	@Id // Markiert die ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Wird automatisch eine ID generieren
	private Integer id;
	// ------------------------
	
	private double lat;
	private double lon;
    private String timezone;
    private int timezoneOffset;
    @Embedded //Versuch nestes Objects with hibernate
    private CurrentObjectReworked current;

	// Constructors
    public CurrentWeatherReworked() {
    	
    }

    public CurrentWeatherReworked(Integer id, double lat, double lon, String timezone, int timezoneOffset, CurrentObjectReworked current) {
		super();
		this.id = id;
		this.lat = lat;
		this.lon = lon;
		this.timezone = timezone;
		this.timezoneOffset = timezoneOffset;
		this.current = current;
	}
	
	// ###   Getter & Setter   ###
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	// ALT---------------------------
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public int getTimezoneOffset() {
		return timezoneOffset;
	}

	public void setTimezoneOffset(int timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	public CurrentObjectReworked getCurrent() {
		return current;
	}

	public void setCurrent(CurrentObjectReworked current) {
		this.current = current;
	}

}
