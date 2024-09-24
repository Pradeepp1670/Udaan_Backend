package com.udaan.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.dto.FlightDto;
import com.udaan.entity.Cities;
import com.udaan.entity.Flight;
import com.udaan.entity.FlightCompany;
import com.udaan.repository.CitiesRepository;
import com.udaan.repository.FlightCompanyRepository;
import com.udaan.repository.FlightRepository;
import com.udaan.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository frepo;
	
	@Autowired
	CitiesRepository cRepo;
	
	@Autowired
	FlightCompanyRepository fcRepo;

	@Override
	public List<Flight> getAllFlights() {
		return frepo.findAll();
	}

	@Override
	public Flight addFlight(FlightDto flight) {
		Flight flt = new Flight();
		flt.setFlightName(flight.getCompany().substring(0,2).toUpperCase());
		Random random = new Random();
		String value =Integer.toString(random.nextInt((1000 - 100) + 1) + 100);
		flt.setFlightNumber(value);
		FlightCompany company = fcRepo.findByCompanyName(flight.getCompany());
		flt.setCompany(company);
		
		Cities source = cRepo.findByCity(flight.getSource());
		flt.setSource(source);
		Cities destination = cRepo.findByCity(flight.getDestination());
		flt.setDestination(destination);
		
		flt.setDeppTime(LocalTime.parse(flight.getDeppTime()));
		flt.setArrTime(LocalTime.parse(flight.getArrTime()));
		flight.getDays().replaceAll(String::toUpperCase);
		flt.setDays(flight.getDays());
		flt.setPrice(flight.getPrice());
		flt.setSeats(flight.getSeats());
		flt.setUuid(UUID.randomUUID().toString());
		Date date = new Date();
		flt.setCreatedOn(date);
		flt.setModifiedOn(date);
		
		return frepo.save(flt);
		
	}

	@Override
	public Flight updateFlight(FlightDto flight, String uuid) {
		Flight flt = frepo.findByUuid(uuid);
		if(flt != null) {
			flt.setFlightName(flight.getCompany().substring(0,2).toUpperCase());
			
			FlightCompany company = fcRepo.findByCompanyName(flight.getCompany());
			flt.setCompany(company);
			
			Cities source = cRepo.findByCity(flight.getSource());
			flt.setSource(source);
			Cities destination = cRepo.findByCity(flight.getDestination());
			flt.setDestination(destination);
			
			flt.setDeppTime(LocalTime.parse( flight.getDeppTime()));
			flt.setArrTime(LocalTime.parse( flight.getArrTime()));
			flt.setDays(flight.getDays());
			flt.setPrice(flight.getPrice());
			flt.setSeats(flight.getSeats());
			Date date = new Date();
			flt.setModifiedOn(date);
			
			return frepo.save(flt);
		}
		return null;
	}

	@Override
	public String deleteFlight(String uuid) {
		Flight flight = frepo.findByUuid(uuid);
		if(flight != null) {
			flight.setCompany(null);
			flight.setSource(null);
			flight.setDestination(null);
			frepo.save(flight);
			frepo.delete(flight);
			return " Flight Deleted successfully";
		}
		return null;
	}

	@Override
	public List<Flight> findBySourceAndDestination(String source, String destination,String date) {
		Cities src = cRepo.findByCity(source);
		Cities dst = cRepo.findByCity(destination);

		List<Flight> flightList =  frepo.findBySourceAndDestination(src,dst);
		return filterByDay(flightList, date);
	}

	public List<Flight> filterByDay(List<Flight> flight, String date){
		List<Flight> flightList = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dt = LocalDate.parse(date, formatter);
		String day = dt.getDayOfWeek().toString().toUpperCase();
        for(Flight flt : flight){
			if(flt.getDays().contains(day)){
				flightList.add(flt);
			}
		}
		return flightList;
	}

	
	
}
