package com.vmz.berlin.weather.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.vmz.berlin.weather.data.CurrentWeather;

/**
 * Interface, welches als Service dient, um die Daten der externen REST-Schnittstelle (Server) abzuholen
 * und der aufrufenden Klasse dieses Interfaces zur Verfügung gestellt wird. Das Interface wird von Quarkus im
 * Hintergund ausgearbeitet und definiert, was es können soll. Wir geben nur leichte Vorgaben durch die MEthoden.
 * @author basti
 * @Path Dieser Teil des Paths wird mit dem Teil aus der application.properties verkettet (für die externe URL des REST-Servers)
 * @RegisterRestClient Dieser configKey bildet die Verkettung
 */
@RegisterRestClient(configKey = "config.api.weather") 
@Path("data/2.5/onecall")  
public interface WeatherService {

	// Berlin, Germany. Latitude and longitude coordinates are: 52.520008, 13.404954
	// Das eingeben als URL eingeben (um zu prüfen, ob es überhaupt eine Verbindung gibt)
	// Nicht LOKAL:
	// https://api.openweathermap.org/data/2.5/onecall?lat=52.520008&lon=13.404954&exclude=minutely,hourly,daily,alerts&appid=f74caf076ddcb0ff249f5447457a68f7  
	// LOKAL:
	// http://localhost:8081/client/query?lat=52.520008&lon=13.404954&exclude=minutely,hourly,daily,alerts&appid=f74caf076ddcb0ff249f5447457a68f7   
	/**
	 * gibt derzeitiges Wetter zurück
	 * @param lat geografische Breite
	 * @param lon geografische Länge
	 * @param exclude gefiltert
	 * @param appid API-Key
	 * @return gibt CurrentWeather-Object zurück
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	CurrentWeather getCurrentWeatherQP(@QueryParam("lat") double lat, @QueryParam("lon") double lon, @QueryParam("exclude") String exclude,
			@QueryParam("appid") String appid);

	// Das eingeben als URL eingeben (um zu prüfen, ob es überhaupt eine Verbindung gibt)
	// Nicht LOKAL:
	// (https://api.openweathermap.org/data/2.5/onecall?lat=52.520008&lon=13.404954&appid=f74caf076ddcb0ff249f5447457a68f7)		
	// LOKAL:
	// http://localhost:8081/client/query/noExclude?id=2950159&appid=f74caf076ddcb0ff249f5447457a68f7                         	
	/**
	 * gibt derzeitiges Wetter + das der nächsten 7 Tage zurück
	 * @param lat geografische Breite
	 * @param lon geografische Länge
	 * @param appid API-Key
	 * @return gibt derzeitiges Wetter + das der nächsten 7 Tage zurück
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	CurrentWeather getCurrentWeatherQP_ALL(@QueryParam("lat") double lat, @QueryParam("lon") double lon,
			@QueryParam("appid") String appid);
}