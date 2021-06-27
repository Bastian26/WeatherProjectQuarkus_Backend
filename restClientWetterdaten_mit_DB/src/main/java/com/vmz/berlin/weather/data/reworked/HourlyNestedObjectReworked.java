package com.vmz.berlin.weather.data.reworked;

import javax.persistence.Embeddable;

@Embeddable
public class HourlyNestedObjectReworked {
	
	private String main;
	private String description;
	
	// Constructors
	public HourlyNestedObjectReworked() {
		super();
	}
	public HourlyNestedObjectReworked(String main, String description) {
		super();
		this.main = main;
		this.description = description;
	}
	
	// Getter & Setter
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
