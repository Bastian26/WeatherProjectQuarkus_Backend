package com.vmz.berlin.weather.converter;

import com.vmz.berlin.weather.data.reworked.CurrentWeatherReworked;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day1;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day2;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day3;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day4;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day5;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day6;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day7;
import com.vmz.berlin.weather.data.reworked.HourlyWeatherReworked_Hour1;
import com.vmz.berlin.weather.data.reworked.HourlyWeatherReworked_Hour2;

/**
 * Hilfsklasse für die Konvertierung. Bildet Container für andere Objekte, um auf diese zugreifne zu können
 * um Programm nicht zu verkomplizieren benötigt. Speichert derzeitiges Wetter + das der nächsten 7 Tage
 * @author basti
 */
public class ConverterHandingover {
	
	//Derzeitges Wetter
	private CurrentWeatherReworked cWReworked;
	//Tägliches Wetter
	private DailyWeatherReworked_Day1 dWReworked1;
	private DailyWeatherReworked_Day2 dWReworked2;
	private DailyWeatherReworked_Day3 dWReworked3;
	private DailyWeatherReworked_Day4 dWReworked4;
	private DailyWeatherReworked_Day5 dWReworked5;
	private DailyWeatherReworked_Day6 dWReworked6;
	private DailyWeatherReworked_Day7 dWReworked7;
	// Stündliche Objekte
	private HourlyWeatherReworked_Hour1 hWReworked1;
	private HourlyWeatherReworked_Hour2 hWReworked2;
	
	// Constructors
	public ConverterHandingover() {
		
	}

	public ConverterHandingover(CurrentWeatherReworked cWReworked, DailyWeatherReworked_Day1 dWReworked1,
			DailyWeatherReworked_Day2 dWReworked2, DailyWeatherReworked_Day3 dWReworked3,
			DailyWeatherReworked_Day4 dWReworked4, DailyWeatherReworked_Day5 dWReworked5,
			DailyWeatherReworked_Day6 dWReworked6, DailyWeatherReworked_Day7 dWReworked7,
			HourlyWeatherReworked_Hour1 hWReworked1, HourlyWeatherReworked_Hour2 hWReworked2) {
		
		super();
		this.cWReworked = cWReworked;
		this.dWReworked1 = dWReworked1;
		this.dWReworked2 = dWReworked2;
		this.dWReworked3 = dWReworked3;
		this.dWReworked4 = dWReworked4;
		this.dWReworked5 = dWReworked5;
		this.dWReworked6 = dWReworked6;
		this.dWReworked7 = dWReworked7;
		this.hWReworked1 = hWReworked1;
		this.hWReworked2 = hWReworked2;
	}

	// Getter & Setter
	public CurrentWeatherReworked getcWReworked() {
		return cWReworked;
	}

	public void setcWReworked(CurrentWeatherReworked cWReworked) {
		this.cWReworked = cWReworked;
	}

	public DailyWeatherReworked_Day1 getdWReworked1() {
		return dWReworked1;
	}

	public void setdWReworked1(DailyWeatherReworked_Day1 dWReworked1) {
		this.dWReworked1 = dWReworked1;
	}

	public DailyWeatherReworked_Day2 getdWReworked2() {
		return dWReworked2;
	}

	public void setdWReworked2(DailyWeatherReworked_Day2 dWReworked2) {
		this.dWReworked2 = dWReworked2;
	}

	public DailyWeatherReworked_Day3 getdWReworked3() {
		return dWReworked3;
	}

	public void setdWReworked3(DailyWeatherReworked_Day3 dWReworked3) {
		this.dWReworked3 = dWReworked3;
	}

	public DailyWeatherReworked_Day4 getdWReworked4() {
		return dWReworked4;
	}

	public void setdWReworked4(DailyWeatherReworked_Day4 dWReworked4) {
		this.dWReworked4 = dWReworked4;
	}

	public DailyWeatherReworked_Day5 getdWReworked5() {
		return dWReworked5;
	}

	public void setdWReworked5(DailyWeatherReworked_Day5 dWReworked5) {
		this.dWReworked5 = dWReworked5;
	}

	public DailyWeatherReworked_Day6 getdWReworked6() {
		return dWReworked6;
	}

	public void setdWReworked6(DailyWeatherReworked_Day6 dWReworked6) {
		this.dWReworked6 = dWReworked6;
	}

	public DailyWeatherReworked_Day7 getdWReworked7() {
		return dWReworked7;
	}

	public void setdWReworked7(DailyWeatherReworked_Day7 dWReworked7) {
		this.dWReworked7 = dWReworked7;
	}

	public HourlyWeatherReworked_Hour1 gethWReworked1() {
		return hWReworked1;
	}

	public void sethWReworked1(HourlyWeatherReworked_Hour1 hWReworked1) {
		this.hWReworked1 = hWReworked1;
	}

	public HourlyWeatherReworked_Hour2 gethWReworked2() {
		return hWReworked2;
	}

	public void sethWReworked2(HourlyWeatherReworked_Hour2 hWReworked2) {
		this.hWReworked2 = hWReworked2;
	}
	
}
