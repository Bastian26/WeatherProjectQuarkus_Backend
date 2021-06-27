package com.vmz.berlin.weather.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {
	
	@Id // Markiert die ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)  // Wird automatisch eine ID generieren
	private Integer id;
	
	String place;
	
	// Constructors
	public Location() {
		super();
	}
	
	public Location(Integer id, String place) {
		super();
		this.id = id;
		this.place = place;
	}
	
	// Getter & Setter
	public int getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
}
