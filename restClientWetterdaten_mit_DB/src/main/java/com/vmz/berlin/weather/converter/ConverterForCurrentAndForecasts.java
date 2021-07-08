package com.vmz.berlin.weather.converter;

import javax.json.bind.annotation.JsonbProperty;

import com.vmz.berlin.weather.data.CurrentWeather;
import com.vmz.berlin.weather.data.CurrentWeatherObject;
import com.vmz.berlin.weather.data.DailyWeather;
import com.vmz.berlin.weather.data.WeatherObject_NestedInDaily;
import com.vmz.berlin.weather.data.reworked.CurrentObjectReworked;
import com.vmz.berlin.weather.data.reworked.CurrentWeatherObjectReworked;
import com.vmz.berlin.weather.data.reworked.CurrentWeatherReworked;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day1;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day2;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day3;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day4;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day5;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day6;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day7;
import com.vmz.berlin.weather.data.reworked.FeelsLikeObject_NestedInDailyReworked;
import com.vmz.berlin.weather.data.reworked.HourlyNestedObjectReworked;
import com.vmz.berlin.weather.data.reworked.HourlyWeatherReworked_Hour1;
import com.vmz.berlin.weather.data.reworked.HourlyWeatherReworked_Hour2;
import com.vmz.berlin.weather.data.reworked.TempObject_NestedInDailyReworked;
import com.vmz.berlin.weather.data.reworked.WeatherObject_NestedInDailyReworked;

/**
 * The class, which is used for the main conversion, converts external CurrentWeather formats into internal formats, which are saved and returned in a container 
 * object, from which the desired objects can then be fetched
 * @author Bastian Struggl
 *
 */
public class ConverterForCurrentAndForecasts {
	
        /**
	 * Main method that is responsible for converting the external objects / classes
	 * 
	 * @param cW Es wird der Methode ein externes Objekt des Typs CurrentWeather übergebn (im Format der REST-API)
	 * @param gewünschteAusgabe Hier wird ein String übergebe, nach diesem wird das CurrentWeather-Object (welches ALLE Informationen enthält, also
	 * Daten für das momentane Wetter + der nächsten 7 Tage) in ein gewünschtes internes Objekt umgewandelt. 
	 * Warum hat man 6 klassen mit einem gleichen Typ erstellt und nicht eine Klasse für "DailyWeatherReworked". Das würde im momentanen Zustand Konflikte mit 
	 * dem ORM (Object Relational Mappign) von Hibernate geben. Dieses sieht Klassen als Entitäten. Würde es nur 1 Klasse für die 6 Tagesobjekte geben, würde es nur 
	 * noch eine Tabelle erstellen und wir hätten nicht die Daten der nächsten 6 Tage in der Db, sondern nur für einen Tag. Mit mehr Zeit könnte man sicher 
	 * diesne Konflikt lösen
	 * @return gibt ein ConvertHandingover-Objekt zurück (welches alle 8 komplexen hier verwendeten internen Datenformate speichern kann) zurück. Aus diesem kann man 
	 * dann später das gewünschte Objekt herasuholen, so spart man sich unnötig komplexe Methoden
	 */
	public static ConverterHandingover convert(CurrentWeather cW, String gewünschteAusgabe) {
		ConverterHandingover convertHandingover = new ConverterHandingover();
		
		// Passt den String einer Zahl an (Day1 - Day7 = 0 - 6), welcher für die folgenden Methoden-Aufrüfe der Folge-Methoden
		// benötigt wird, oder nimmt nur das CurrentWeather 
		switch (gewünschteAusgabe) {
				//Derzeitiges Wetter
		case   "current":
					convertHandingover.setcWReworked(convertIntoCurrent(cW));
					;
				// Zukünftiges Wetter
		case 	  "day1":
					convertHandingover.setdWReworked1(convertIntoDay1(cW, 0));
					;
		case 	  "day2":
					convertHandingover.setdWReworked2(convertIntoDay2(cW, 1));
					;
		case 	  "day3":
					convertHandingover.setdWReworked3(convertIntoDay3(cW, 2));
					;
		case 	  "day4":
					convertHandingover.setdWReworked4(convertIntoDay4(cW, 3));
					;
		case 	  "day5":
					convertHandingover.setdWReworked5(convertIntoDay5(cW, 4));
					;
		case 	  "day6":
					convertHandingover.setdWReworked6(convertIntoDay6(cW, 5));
					;
		case 	  "day7":
					convertHandingover.setdWReworked7(convertIntoDay7(cW, 6));
					;
				// Nächste Stunde
		case 	  "hour1":
					convertHandingover.sethWReworked1(convertIntoHour1(cW, 0));
					;
				// Darauffolgende Stunde
		case 	  "hour2":
					convertHandingover.sethWReworked2(convertIntoHour2(cW, 1));
			;		
		}
		return convertHandingover;
	}
	
	// Sekundärmethoden. Müssen so viele sein, da sie immer einen anderen Datentyp zurückgeben, sonst bräuchte man wieder Sammelklasse
   /**
	* Wandelt externes Klassenformat in internes für derzeitges Wetter (CurrentWeatherReworked)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static CurrentWeatherReworked convertIntoCurrent(CurrentWeather cW) {
		
		CurrentWeatherReworked currentWR = new CurrentWeatherReworked();
		
		currentWR.setLat(cW.getLat()); //Konvertiert Lat
		currentWR.setLon(cW.getLon());
		currentWR.setTimezone(cW.getTimezone());
		currentWR.setTimezoneOffset(cW.getTimezoneOffset());
		
		// CurrentObject
		currentWR.setCurrent(new CurrentObjectReworked());
		currentWR.getCurrent().setSunrise(cW.getCurrent().getSunrise());         
		currentWR.getCurrent().setSunset(cW.getCurrent().getSunset());  	
		currentWR.getCurrent().setTemp(kelvinInCelsius(cW.getCurrent().getTemp())); 
		currentWR.getCurrent().setFeelsLike(kelvinInCelsius(cW.getCurrent().getFeelsLike()));  
		currentWR.getCurrent().setPressure(cW.getCurrent().getPressure());
		currentWR.getCurrent().setHumidity(cW.getCurrent().getHumidity());
		currentWR.getCurrent().setDewPoint(kelvinInCelsius(cW.getCurrent().getDewPoint()));
		currentWR.getCurrent().setUvi(cW.getCurrent().getUvi());
		currentWR.getCurrent().setClouds(cW.getCurrent().getClouds());
		currentWR.getCurrent().setVisibility(cW.getCurrent().getVisibility());
		currentWR.getCurrent().setWindSpeed(cW.getCurrent().getWindSpeed());
		currentWR.getCurrent().setWindDeg(cW.getCurrent().getWindDeg());
		
		// WeatherObject
		currentWR.getCurrent().setWeather(new CurrentWeatherObjectReworked());
		currentWR.getCurrent().getWeather().setMain(cW.getCurrent().getWeather()[0].getMain());
		currentWR.getCurrent().getWeather().setDescription(cW.getCurrent().getWeather()[0].getDescription());
		
		return currentWR;
	}
	
	/**
	* Wandelt externes Klassenformat in internes für 1.Tag Wetter (DailyWeatherReworked_Day1)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergebn
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day1 convertIntoDay1(CurrentWeather cW, int day) {
		
		DailyWeatherReworked_Day1 dailyRW = new DailyWeatherReworked_Day1();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
		
		return dailyRW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 2.Tag Wetter (DailyWeatherReworked_Day2)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day2 convertIntoDay2(CurrentWeather cW, int day) {
		
		DailyWeatherReworked_Day2 dailyRW = new DailyWeatherReworked_Day2();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
		
		return dailyRW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 3.Tag Wetter (DailyWeatherReworked_Day3)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day3 convertIntoDay3(CurrentWeather cW, int day) {
	
		DailyWeatherReworked_Day3 dailyRW = new DailyWeatherReworked_Day3();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
	
		return dailyRW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 4.Tag Wetter (DailyWeatherReworked_Day4)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day4 convertIntoDay4(CurrentWeather cW, int day) {
	
		DailyWeatherReworked_Day4 dailyRW = new DailyWeatherReworked_Day4();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
	
		return dailyRW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 5.Tag Wetter (DailyWeatherReworked_Day5)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day5 convertIntoDay5(CurrentWeather cW, int day) {
	
		DailyWeatherReworked_Day5 dailyRW = new DailyWeatherReworked_Day5();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
	
		return dailyRW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 6.Tag Wetter (DailyWeatherReworked_Day6)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day6 convertIntoDay6(CurrentWeather cW, int day) {
	
		DailyWeatherReworked_Day6 dailyRW = new DailyWeatherReworked_Day6();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
		
		return dailyRW;
	}

   /**
	* Wandelt externes Klassenformat in internes für 7.Tag Wetter (DailyWeatherReworked_Day7)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
    * @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static DailyWeatherReworked_Day7 convertIntoDay7(CurrentWeather cW, int day) {
	
		DailyWeatherReworked_Day7 dailyRW = new DailyWeatherReworked_Day7();
		
		dailyRW.setDt(cW.getDaily().get(day).getDt()); 
		dailyRW.setSunrise(cW.getDaily().get(day).getSunrise());
		dailyRW.setSunset(cW.getDaily().get(day).getSunset());
		
		// TempObject
		dailyRW.setTemp(new TempObject_NestedInDailyReworked());
		dailyRW.getTemp().setDay(kelvinInCelsius(cW.getDaily().get(day).getTemp().getDay()));
		dailyRW.getTemp().setMin(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMin()));
		dailyRW.getTemp().setMax(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMax()));
		dailyRW.getTemp().setNight(kelvinInCelsius(cW.getDaily().get(day).getTemp().getNight()));
		dailyRW.getTemp().setEve(kelvinInCelsius(cW.getDaily().get(day).getTemp().getEve()));
		dailyRW.getTemp().setMorn(kelvinInCelsius(cW.getDaily().get(day).getTemp().getMorn()));
		
		// FeelsLikeObject
		dailyRW.setFeelsLike(new FeelsLikeObject_NestedInDailyReworked());
		dailyRW.getFeelsLike().setDayFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		dailyRW.getFeelsLike().setEveFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getEve()));
		dailyRW.getFeelsLike().setMornFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getMorn()));
		dailyRW.getFeelsLike().setNightFL(kelvinInCelsius(cW.getDaily().get(day).getFeelsLike().getNight()));
		
		dailyRW.setPressure(cW.getDaily().get(day).getPressure());   
		dailyRW.setHumidity(cW.getDaily().get(day).getHumidity());
		
		dailyRW.setDewPoint(kelvinInCelsius(cW.getDaily().get(day).getDewPoint()));
		dailyRW.setWindSpeed(cW.getDaily().get(day).getWindSpeed());
		dailyRW.setWindDeg(cW.getDaily().get(day).getWindDeg());
		
		// WeatherObject
		dailyRW.setWeather(new WeatherObject_NestedInDailyReworked());
		dailyRW.getWeather().setId(cW.getDaily().get(day).getWeather().get(0).getId());
		dailyRW.getWeather().setMain(cW.getDaily().get(day).getWeather().get(0).getMain());
		dailyRW.getWeather().setDescription(cW.getDaily().get(day).getWeather().get(0).getDescription());
		
		return dailyRW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 1.Stunde, also das Wetter für die nächste Stunde (HourlyWeatherReworked_Hour1)
	* Es werden in diesem Format aber viele unwichtige Daten abgeschnitten
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
	* @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static HourlyWeatherReworked_Hour1 convertIntoHour1(CurrentWeather cW, int hour) {
		
		HourlyWeatherReworked_Hour1 hour1RW = new HourlyWeatherReworked_Hour1 ();
		
		hour1RW.setTemp(cW.getHourly().get(hour).getTemp());
		hour1RW.setClouds(cW.getHourly().get(hour).getClouds());
		
		HourlyNestedObjectReworked hNestedObjReworked = new HourlyNestedObjectReworked();
		
		hour1RW.setWeather(hNestedObjReworked);	
		hour1RW.getWeather().setMain(cW.getHourly().get(0).getWeather().get(0).getMain());
		hour1RW.getWeather().setDescription(cW.getHourly().get(0).getWeather().get(0).getDescription());
		
		return hour1RW;
	}
	
   /**
	* Wandelt externes Klassenformat in internes für 2.Stunde, also das Wetter der übernächsten Stunde (HourlyWeatherReworked_Hour2)
	* @param cW CurrentWeather-Objekt (Externes Format) wird übergebn
	* @param day Hier wird Tag übergeben
	* @return in interne Format umgewandeltes Objekt wird zurückgegeben
	*/
	public static HourlyWeatherReworked_Hour2 convertIntoHour2(CurrentWeather cW, int hour) {
			
		HourlyWeatherReworked_Hour2 hour2RW = new HourlyWeatherReworked_Hour2 ();
		
		hour2RW.setTemp(cW.getHourly().get(hour).getTemp());
		hour2RW.setClouds(cW.getHourly().get(hour).getClouds());
		
		HourlyNestedObjectReworked hNestedObjReworked = new HourlyNestedObjectReworked();
		
		hour2RW.setWeather(hNestedObjReworked);	
		hour2RW.getWeather().setMain(cW.getHourly().get(1).getWeather().get(0).getMain());
		hour2RW.getWeather().setDescription(cW.getHourly().get(1).getWeather().get(0).getDescription());
		
		return hour2RW;
	}
	
	/**
	 * Zur Umrechnung von Kelvin (extern) in Celsius (intern)
	 * @param kelvin Die Kelvinzahl, die der Methode übergeben wird
	 * @return gibt die Grad Celisus als Double-Datentyp zurück
	 */
	public static double kelvinInCelsius(double kelvin) {
		double celsius = (kelvin-273.15);
		return celsius;
	}
}








