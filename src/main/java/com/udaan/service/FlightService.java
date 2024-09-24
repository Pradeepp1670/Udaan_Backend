package com.udaan.service;

import java.util.List;
import java.util.UUID;

import com.udaan.dto.FlightDto;
import com.udaan.entity.Flight;

public interface FlightService {

	public List<Flight> getAllFlights();
	
	public Flight addFlight(FlightDto flight);
	
	public Flight updateFlight(FlightDto flight, String uuid);	
	
	public String deleteFlight(String uuid);
	
	public List<Flight> findBySourceAndDestination(String Source,String destination, String date);
	
	
}
