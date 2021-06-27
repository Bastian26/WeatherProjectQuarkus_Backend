package com.vmz.berlin.weather.data;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Unterklasse von CurrentObject (das letzte Glied dieser Verschachtelung)
 * @author basti
 *
 */
public class CurrentWeatherObject {
	
//	@JsonbProperty("id")
//	private int cWOid;
	private String main;
	private String description;
	
	// Default Constructor
	public CurrentWeatherObject() {
		
	}
	
	// Constructor
	public CurrentWeatherObject(/*int cWOid,*/ String main, String description) {
		super();
		//this.cWOid = cWOid;
		this.main = main;
		this.description = description;
	}
	
	// ###   Getter & Setter   ###
//	public int getcWOId() {
//		return cWOid;
//	}
//
//	public void setcWOId(int id) {
//		this.cWOid = cWOid;
//	}

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
