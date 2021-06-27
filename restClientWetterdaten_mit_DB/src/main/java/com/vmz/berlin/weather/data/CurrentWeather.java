package com.vmz.berlin.weather.data;


import java.util.ArrayList;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.codec.binary.Base32;

/**
 * Hautklasse, die dann die anderen Klassen verschachtelt beherbergt. Z.B. Hourly & Daily, Menutely ist deaktiviert (auskommentiert), da
 * man diese Daten im Moment nicht benötigt von dem REST-Server (kann erweitert werden bei Bedarf)
 * @author basti
 */
public class CurrentWeather {
	
	private double lat;
	private double lon;
    private String timezone;
    @JsonbProperty("timezone_offset")
    private int timezoneOffset;
    private CurrentObject current;
//    ArrayList<MinutelyObject> minutely;
    ArrayList<HourlyObject> hourly;
    private ArrayList<DailyWeather> daily;
    
    // Constructors
    public CurrentWeather() {
    	
    }

    public CurrentWeather(double lat, double lon, String timezone, int timezoneOffset, CurrentObject current,
			/*ArrayList<MinutelyObject> minutely*/ ArrayList<HourlyObject> hourly ,ArrayList<DailyWeather> daily) {
		super();
		this.lat = lat;
		this.lon = lon;
		this.timezone = timezone;
		this.timezoneOffset = timezoneOffset;
		this.current = current;
//		this.minutely = minutely;
		this.hourly = hourly;
		this.daily = daily;
	}
	
	// Getter & Setter  
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

	public CurrentObject getCurrent() {
		return current;
	}

	public void setCurrent(CurrentObject current) {
		this.current = current;
	}
//
//	public ArrayList<MinutelyObject> getMinutely() {
//		return minutely;
//	}
//
//	public void setMinutely(ArrayList<MinutelyObject> minutely) {
//		this.minutely = minutely;
//	}

	public ArrayList<HourlyObject> getHourly() {
		return hourly;
	}

	public void setHourly(ArrayList<HourlyObject> hourly) {
		this.hourly = hourly;
	}

	public ArrayList<DailyWeather> getDaily() {
		return daily;
	}

	public void setDaily(ArrayList<DailyWeather> daily) {
		this.daily = daily;
	}

	
	
    
    
	
}
