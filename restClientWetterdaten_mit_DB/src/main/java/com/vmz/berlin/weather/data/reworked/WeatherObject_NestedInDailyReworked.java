package com.vmz.berlin.weather.data.reworked;

import javax.persistence.Embeddable;

@Embeddable
public class WeatherObject_NestedInDailyReworked {
	
	private int id;
	private String main;
	private String description;
	
	// Constructors
	public WeatherObject_NestedInDailyReworked() {
		
	}

	public WeatherObject_NestedInDailyReworked(int id, String main, String description) {
		super();
		this.id = id;
		this.main = main;
		this.description = description;
	}

	// Getter & Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMain() {
		return main;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
