package hu.webuni.airport.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.webuni.airport.model.Airport;
import hu.webuni.airport.model.Flight;
import hu.webuni.airport.repository.AirportRepository;
import hu.webuni.airport.repository.FlightRepository;

@Service
public class AirportService {

//	@PersistenceContext
//	EntityManager em;
	
	AirportRepository airportRepository;
	FlightRepository flightRepository;

	public AirportService(AirportRepository airportRepository, FlightRepository flightRepository) {
		super();
		this.airportRepository = airportRepository;
		this.flightRepository = flightRepository;
	}

	@Transactional
	public Airport save(Airport airport) {
		checkUniqueIata(airport.getIata(), null);
//		em.persist(airport);
		return airportRepository.save(airport);
	}
	
	public List<Airport> findAll() {
//		return em.createQuery("SELECT a FROM Airport a", Airport.class).getResultList();
		return airportRepository.findAll();
	}
	
	public Optional<Airport> findById(long id) {
//		return em.find(Airport.class, id);
		return airportRepository.findById(id);
	}
	
	@Transactional
	public void delete(long id) {
//		em.remove(findById(id));
		airportRepository.deleteById(id);
	}

	@Transactional
	public Airport update(Airport airport) {
		checkUniqueIata(airport.getIata(), airport.getId());
//		return em.merge(airport);
		if(airportRepository.existsById(null)) {
			return airportRepository.save(airport);
		} else {
			throw new NoSuchElementException();
		}

		
	}
	
	private void checkUniqueIata(String iata, Long id) { 
		
		boolean forUpdate = id != null;
		
		//mivel a getSingleResult Object-tel tér vissza, a createNamedQuery-nek megmondom második paraméterben, h mi lesz az eredmény
//		TypedQuery<Long> query = em.createNamedQuery(forUpdate ? "Airport.countByIataAndIdNotIn" : "Airport.countByIata", Long.class)
//					   .setParameter("iata", iata);
//		if (forUpdate) {
//			query.setParameter("id", id);
//		}
//		Long count = query.getSingleResult();
		
		Long count = forUpdate ? airportRepository.countByIataAndIdNot(iata, id) : airportRepository.countByIata(iata);
		
		if(count > 0) {
			throw new NonUniqueIataException(iata);
		}
	  }
	
	@Transactional
	public void createFlight() {
		Flight flight = new Flight();
		flight.setFlightNumber("asdfasf");
		flight.setTakeoff(airportRepository.findById(1L).get());
		flight.setLanding(airportRepository.findById(3L).get());
		flight.setTakeOffTime(LocalDateTime.of(2021, 4, 10, 10, 0, 0));
		flightRepository.save(flight);
	}

}
