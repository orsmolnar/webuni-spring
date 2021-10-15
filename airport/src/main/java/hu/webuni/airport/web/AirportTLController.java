package hu.webuni.airport.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hu.webuni.airport.dto.AirportDto;

@Controller
public class AirportTLController {
	
	private List<AirportDto> allAirports = new ArrayList<>();
	
	{
		allAirports.add(new AirportDto(1, "Ferenc Liszt Airport", "BUD"));
		allAirports.add(new AirportDto(2, "Düsseldorf International Airport", "DUS"));
		allAirports.add(new AirportDto(3, "Vienna International Airport", "VIE"));
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	@GetMapping("/airports")
	public String listAirports(Map<String, Object> model) {
		model.put("airports", allAirports);
		model.put("newAirport", new AirportDto());
		return "airports";
	}
	
	@PostMapping("/airports")
	public String addAirport(AirportDto airport) {
		allAirports.add(airport);
		//átirányítás - a válasz kigenerálása előtt hívódjon meg a GetMapping-es listAirports metódus, hogy a newAirport bele kerüljön a model-be
		return "redirect:airports";
	}
}
