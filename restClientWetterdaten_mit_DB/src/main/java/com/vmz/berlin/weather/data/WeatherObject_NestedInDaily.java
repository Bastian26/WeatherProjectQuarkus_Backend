package com.vmz.berlin.weather.data;

/**
 * Unterklasse von DailyWeather (keine weitere Verschachtelung)
 * @author basti
 *
 */
public class WeatherObject_NestedInDaily {
	
	private int id;
	private String main;
	private String description;
	private String icon;
	
	// Constructors
	public WeatherObject_NestedInDaily() {
		
	}

	public WeatherObject_NestedInDaily(int id, String main, String description, String icon) {
		super();
		this.id = id;
		this.main = main;
		this.description = description;
		this.icon = icon;
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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
