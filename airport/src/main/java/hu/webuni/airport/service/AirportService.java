package hu.webuni.airport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import hu.webuni.airport.model.Airport;

@Service
public class AirportService {

	private Map<Long, Airport> airports = new HashMap<>();
	
	{
		airports.put(1L, new Airport(1, "Ferenc Liszt Airport", "BUD"));
		airports.put(2L, new Airport(2, "Düsseldorf International Airport", "DUS"));
		airports.put(3L, new Airport(3, "Vienna International Airport", "VIE"));
	}
	
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata());
		airports.put(airport.getId(), airport);
		return airport;
	}
	
	public List<Airport> findAll() {
		return new ArrayList<>(airports.values());
	}
	
	public Airport findById(long id) {
		return airports.get(id);
	}
	
	public void delete(long id) {
		airports.remove(id);
	}

	public Airport modify(Airport airportToModify, Airport newAirport) {
		checkUniqueIata(newAirport.getIata());
		newAirport.setId(airportToModify.getId());
		airports.replace(newAirport.getId(), newAirport);
		return newAirport;
	}
	
	private void checkUniqueIata(String iata) { 
		  Optional<Airport> airportWithSameIata = airports.values().stream() 
				  											 .filter(a -> a.getIata().equals(iata)) 
				  											 .findAny(); 
		  if(airportWithSameIata.isPresent()) {
			  throw new NonUniqueIataException(iata);
		  }
	  }

}
