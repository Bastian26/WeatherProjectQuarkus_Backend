package com.vmz.berlin.weather;

import java.util.ArrayList;
import java.util.List;
import io.quarkus.scheduler.Scheduled;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.jboss.logging.Logger;

import com.vmz.berlin.weather.converter.ConverterForCurrentAndForecasts;
import com.vmz.berlin.weather.converter.ConverterHandingover;
import com.vmz.berlin.weather.data.CurrentWeather;
import com.vmz.berlin.weather.data.Location;
import com.vmz.berlin.weather.data.reworked.CurrentWeatherReworked;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day1;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day2;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day3;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day4;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day5;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day6;
import com.vmz.berlin.weather.data.reworked.DailyWeatherReworked_Day7;
import com.vmz.berlin.weather.service.WeatherService;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
//Diese werden nur gebraucht, wenn die unteren Methoden im Code entkommentiert werden
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

/** 
 * Hauptklasse
 * @author Bastian Struggl
 * Diese Klasse wird sozusagen als Hauptmethode verwendet, ähnlich der main-Klasse. Diese existiert zwar auch in Quarkus, ist aber nicht direkt zu sehen
 * und wird auch nicht unbedingt benötigt. In dieser wird das WeatherService-Interface sowie der EntityManager von Hibernate (wichtig für die DB) injected
 * Es sind viele Methoden definiert wurden, um Daten von der externen Schnittstelle (REST-Server) zu holen und diese ggf. in einem anderen Format 
 * in der DB zu speichern (automatisch/manuell). Auf diese kann man in dem URL-Pfad localhost:8081/client zugreifen, solange diese lokal zu Verfügung gestellt wird.
 * 
 */
@Path("/client")
public class CourseWeatherResource {
	
	// wird für das Logging benötigt
	private static final Logger LOG = Logger.getLogger(CourseWeatherResource.class); //für Logging
	private static ArrayList<Location> locations = new ArrayList<Location>();
	private static Location location;
	// latitude & Longitude
	private static double lat;
	private static double lon;
	
	//Entity manager deklarieren, um mit diesem Hibernate für den Datenbankzugriff nutzen zu können
	//Wird injected
	@Inject
	EntityManager manager;
	
	//Die Weatherservice-Klasse wird injected und mit der Annotation "Rest-Client" versehen
	@Inject
	@RestClient
	private WeatherService weatherService;
	
	
   /**
	* Holt Daten aus der Location Datenbank
	* @return eine Liste mit Location-Objekten (Datensätzen)
	*/
	@GET
	@Path("/query/noExclude/showLocation")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Location> showLocation() {
		return manager.createQuery("Select b FROM Location b", Location.class).getResultList(); 
    }
	
   /**
	* Holt Daten aus der Location Datenbank und speichet diese in ein statisches Array zu Weiterverarbeitung.
	* Anschließend wird geprüft, welche Location auf der webseite ausgewählt wurde und in passende Lat und Lon Werte übersetzt.
	* @scheduled Wird jede Sek ausgeführt, dieser hohe Intervall wird benötigt, da Benutzereingaben schnell aktualisiert werden müssen
	*/
	@Scheduled(every="1s") 
    public void getAndSaveLocation() {
		locations = (ArrayList<Location>) manager.createQuery("Select b FROM Location b", Location.class).getResultList(); 
		location = locations.get(0);
		
		switch (location.getPlace()) {
		case "Berlin":
					lat = 52.520008;
					lon = 13.404954;
					break;
		case "Hamburg":
					lat = 53.551086;
					lon = 9.993682;
					break;
		case "Frankfurt":
					lat = 50.110924;
					lon = 8.682127;
					break;
		case "Hannover":
					lat = 52.37052;
					lon = 9.73322;
					break;
		case "München":
					lat = 48.137154;
					lon = 11.576124;
					break;
		case "Köln":
					lat = 50.935173;
					lon = 6.953101;
					break;
		}
    }
	
	//Speichert internes Format (hier das derzeite Wetter in die Tabelle "CurrentWeather")
	/**
	 * Holt externes Format (hier das derzeite Wetter in die Tabelle "CurrentWeather"), konvertiert dieses in eigene Klassen und speichert diese dann in die DB 
	 * in die Tablle "CurrentWeatherReworked" per ORM (Object Relational Mapping)
	 * @Scheduled Methode wird alle 5 Min. ausgeführt
	 * @Transactional Gibt an, dass die Methode Transaktionen nutzen kann
	 * Holt die Daten für den Bereich "Berlin" mithife des Lat, Long und dem gültigen API-Key
	 */
	@Scheduled(every="150s") //300
	@Transactional 	
	public void saveCurrentWeather() {
		LOG.info("5 Minuten um"); 
		CurrentWeather cWy;
		if (lat == 0.0 & lon == 0.0) {
			cWy = weatherService.getCurrentWeatherQP_ALL(52.520008, 13.404954, "f74caf076ddcb0ff249f5447457a68f7");
		}else {
			cWy = weatherService.getCurrentWeatherQP_ALL(lat, lon, "f74caf076ddcb0ff249f5447457a68f7");
		}
		ConverterHandingover converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "current");
		manager.persist(converterHandingover.getcWReworked());   
	}
	
	/**
	 * Holt externes Format und konvertiert dieses in ein internes (interne Klassen) und speichert die Daten für die nächsten 7 Tage in 7 Tabellen in der DB
	 * per ORM
	 * @Scheduled Methode wird alle 5 Min. ausgeführt
	 * @Transactional Gibt an, dass die Methode Transaktionen nutzen kann
	 * Holt die Daten für den Bereich "Berlin" mithife des Lat, Long und dem gültigen API-Key
	 */
	@Scheduled(every="150s") //10Min 600
	@Transactional 	
	public void saveForecastWeatherForAllDays() {
		LOG.info("10 Minuten um"); //Logging
		//Holt ein CurrentWeather-Objekt im externen Format (für Berlin)
		CurrentWeather cWy;
		if (lat == 0.0 & lon == 0.0) {
			cWy = weatherService.getCurrentWeatherQP_ALL(52.520008, 13.404954, "f74caf076ddcb0ff249f5447457a68f7");
		}else {
			cWy = weatherService.getCurrentWeatherQP_ALL(lat, lon, "f74caf076ddcb0ff249f5447457a68f7");
		}
		// Erzeuge ein convert-Objekt, welches zum Konvertieren benötigt wird
		ConverterForCurrentAndForecasts convert = new ConverterForCurrentAndForecasts();
		
		//Konvertiert dieses in 6 Objekte (für die Wettervorhersage der nächsten 6 Tage) mit der dafür erzeugten Methode
		// Speichert das momentane Wetter von heute in die Datenbank
		ConverterHandingover converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day1");
		manager.persist(converterHandingover.getdWReworked1());   
		// Speichert Tag 1 (heute)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day2");
		manager.persist(converterHandingover.getdWReworked2()); 
		// Speichert Tag 2 (morgen)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day3");
		manager.persist(converterHandingover.getdWReworked3()); 
		// Speichert Tag 3 (übermorgen)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day4");
		manager.persist(converterHandingover.getdWReworked4()); 
		// Speichert Tag 4 (...)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day5");
		manager.persist(converterHandingover.getdWReworked5()); 
		// Speichert Tag 5 (...)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day6");
		manager.persist(converterHandingover.getdWReworked6()); 
		// Speichert Tag 6 (...)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "day7");
		manager.persist(converterHandingover.getdWReworked7()); 
		// Speichert Wetter der nächsten Stunde (1. Stunde) (Wird zwar nicht genutzt auf der Webseite, dient aber der Erweiterung)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "hour1");
		manager.persist(converterHandingover.gethWReworked1()); 
		// Speichert Wetter der übernächsten Stunde (2. Stunde) (Wird zwar nicht genutzt auf der Webseite, dient aber der Erweiterung)
		converterHandingover = ConverterForCurrentAndForecasts.convert(cWy, "hour2");
		manager.persist(converterHandingover.gethWReworked2()); 
	}
		
	 // Daten aus der Datenbank holen (entweder für derzeitiges Wetter, oder für die nächsten 7 Tage) und zeigt diese an per GET an. Nur für manuelle Prüfung gedacht
	/**
	 * @GET Holt Daten für das derzeitige Wetter im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der Datenbank 
	 * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
	 * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
	 * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
	 */
	 @GET
	 @Path("/query/noExclude/showCurrentWeather")
     @Produces(MediaType.APPLICATION_JSON)
     public List<CurrentWeatherReworked> getCurrentWeatherFromDB() {
		return manager.createQuery("Select b FROM CurrentWeatherReworked b", CurrentWeatherReworked.class).getResultList(); 
     }
	/**
	 * @GET Holt Daten für das zukünftige Wetter (1. Tag, Morgen) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
	 * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
	 * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
	 */
	 @GET
	 @Path("/query/noExclude/showSaveDay1")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day1> getWeatherDay1FromDB() {  
		return manager.createQuery("Select b FROM DailyWeatherReworked_Day1 b", DailyWeatherReworked_Day1.class).getResultList();                                                                                                                 
     }
    
    /**
 	 * @GET Holt Daten für das zukünftige Wetter (2. Tag, Übermorgen) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
 	 * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
 	 * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
 	 */
 	 @GET
 	 @Path("/query/noExclude/showSaveDay2")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day2> getWeatherDay2FromDB() {  
 		return manager.createQuery("Select b FROM DailyWeatherReworked_Day2 b", DailyWeatherReworked_Day2.class).getResultList(); 
     }
     
    /**
  	 * @GET Holt Daten für das zukünftige Wetter (3. Tag) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
  	 * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
  	 * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
  	 */
 	 @GET
 	 @Path("/query/noExclude/showSaveDay3")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day3> getWeatherDay3FromDB() { 
 		return manager.createQuery("Select b FROM DailyWeatherReworked_Day3 b", DailyWeatherReworked_Day3.class).getResultList(); 
     }
    /**
   	 * @GET Holt Daten für das zukünftige Wetter (4. Tag) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
   	 * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
   	 * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
   	 */
 	 @GET
 	 @Path("/query/noExclude/showSaveDay4")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day4> getWeatherDay4FromDB() { 
 		return manager.createQuery("Select b FROM DailyWeatherReworked_Day4 b", DailyWeatherReworked_Day4.class).getResultList(); 
     }
    /**
     * @GET Holt Daten für das zukünftige Wetter (5. Tag) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
     * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
     * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
     */
 	 @GET
 	 @Path("/query/noExclude/showSaveDay5")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day5> getWeatherDay5FromDB() {  
 		return manager.createQuery("Select b FROM DailyWeatherReworked_Day5 b", DailyWeatherReworked_Day5.class).getResultList(); 
     }
     
    /**
     * @GET Holt Daten für das zukünftige Wetter (6. Tag) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
     * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
     * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
     */
 	 @GET
 	 @Path("/query/noExclude/showSaveDay6")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day6> getWeatherDay6FromDB() { 
 		return manager.createQuery("Select b FROM DailyWeatherReworked_Day6 b", DailyWeatherReworked_Day6.class).getResultList();                                                                                                                      //Um mehr als sql nutzen zu können
     }
    /**
     * @GET Holt Daten für das zukünftige Wetter (7. Tag) im internen Format (von eigen erstellten/internen Klassen, welche oft mit Reworked im Namen enden) aus der DB 
     * im Docker-Container, nutzt Hibernate ORM (Object-Relational-Mapping)
     * @Path gibt den Pfad an, den man nach "/client" setzen muss, um auf die Methode zuzugreifen
     * @return gibt ein internes Objekt zurück (Welches mit einer SQL-Query geholt wird) und zeigt dieses an
     */
 	 @GET
 	 @Path("/query/noExclude/showSaveDay7")
     @Produces(MediaType.APPLICATION_JSON)
     public List<DailyWeatherReworked_Day7> getWeatherDay7FromDB() {  
 		return manager.createQuery("Select b FROM DailyWeatherReworked_Day7 b", DailyWeatherReworked_Day7.class).getResultList();                                                                                                                 //Um mehr als sql nutzen zu können
     }
		
	 // Code ab hier ist nur optional, wenn der Wunsch nach Erweiterung besteht, kann man diesen entkommentieren (PUT = Verändern, DELET = Löschen)
 	 //Fügt Daten mit POST hinzu (es wird ein JSON-Objekt entgegengenommen und eingefügt)
     
 	/**
 	 * Methode holt Daten gefiltet, ohne diese in der Datenbank zu speichern
 	 * @param lat geografische Breite
 	 * @param lon geografische Länge
 	 * Beide zusammen ergeben GPS-Koordinaten
 	 * @param exclude damit schließt man 
 	 * @param appid hier wird der API-Schlüssel eingegeben
 	 * @return gibt das momentane Wetter als Objekt zurück, wird mit GET ausgegeben
 	 */
 	@GET
 	@Path("/query")
 	@Produces(MediaType.APPLICATION_JSON)
 	public CurrentWeather getAttributeOfObject(@QueryParam("lat") double lat, @QueryParam("lon") double lon, @QueryParam("exclude") String exclude,
 			@QueryParam("appid") String appid) {  
 	return weatherService.getCurrentWeatherQP(lat, lon, exclude, appid);
 	}
 	
 	/**
 	 * Methode holt die Daten ungefiltert (noExclude), ohne diese dabei in einer Datenbank zu speichern
 	 * @param lat geografische Breite
 	 * @param lon geografische Länge
 	 * Beide zusammen ergeben GPS-Koordinaten
 	 * @param appid appid hier wird der API-Schlüssel eingegeben
 	 * @return gibt das momentane Wetter als Objekt zurück, wird mit GET ausgegeben
 	 */
 	// Hole alle Daten ungefiltert 
 	@GET
 	@Path("/query/noExclude")
 	@Produces(MediaType.APPLICATION_JSON)
 	public CurrentWeather getAttributeOfObject2(@QueryParam("lat") double lat, @QueryParam("lon") double lon,
 			@QueryParam("appid") String appid) {  
 		CurrentWeather cW;
 		cW = weatherService.getCurrentWeatherQP_ALL(lat, lon, appid);  // TEST neu optional  
 		return cW;
 	}
 	 @POST
 	 @Path("/query/noExclude")
     @Consumes(MediaType.APPLICATION_JSON)
     @Produces(MediaType.APPLICATION_JSON)
     @Transactional 								//Spezifiziert, dass die Methode Transaktionen nutzen kann
     public CurrentWeather saveWeather(CurrentWeather book) {  
 		manager.persist(book);  				//Speicher ein Buch durhc den Persist-manager
     	return book;
     }
 	
	// PUT verändert bestehende Daten aus der Datenbank, es muss die id als Path-Parameter übergeben werden
	@PUT
	@Path("/query/noExclude/put{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional	//Spezifiziert, dass die Methode Transaktionen nutzen kann
    public CurrentWeatherReworked changeCurrentWeather(CurrentWeatherReworked currentWeatherReworked, @PathParam("id") Integer index) {  
			CurrentWeatherReworked cWR = manager.find(CurrentWeatherReworked.class, 1);
			manager.getTransaction().begin();
			manager.remove(cWR);
			manager.getTransaction().commit();
		manager.persist(currentWeatherReworked);	//Speichert den CurrentWeatherReworked-Datensatz
    	// Gibt das veränderte Objekt zurück (so kann man ggf. überprüfen, ob man auch die richtigen Daten verändert hat)
		return currentWeatherReworked;
    }
	
	// Löscht einen Datensatz für CurrentWeatherReworked nach der id
	@DELETE
	@Path("/query/noExclude/delete{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional	//Spezifiziert, dass die Methode Transaktionen nutzen kann
    public CurrentWeatherReworked deleteCurrentWeather(@PathParam("id") Integer index) {  
			CurrentWeatherReworked cWR = manager.find(CurrentWeatherReworked.class, 1);
			manager.getTransaction().begin();
			manager.remove(cWR);
			manager.getTransaction().commit();
    	return cWR;
    }
	
}
