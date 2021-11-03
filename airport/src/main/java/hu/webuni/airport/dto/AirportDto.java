package hu.webuni.airport.dto;

import java.util.Objects;

import javax.validation.constraints.Size;

public class AirportDto {

	@Override
	public int hashCode() {
		return Objects.hash(iata, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AirportDto other = (AirportDto) obj;
		return Objects.equals(iata, other.iata) && id == other.id && Objects.equals(name, other.name);
	}
	private long id;
	
	@Size(min = 3, max = 20)
	private String name;
	private String iata;
	
	public AirportDto(){	
	}
	
	public AirportDto(long id, String name, String iata) {
		super();
		this.id = id;
		this.name = name;
		this.iata = iata;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	
}
