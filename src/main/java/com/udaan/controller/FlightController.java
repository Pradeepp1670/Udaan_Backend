package com.udaan.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udaan.dto.FlightDto;
import com.udaan.entity.Flight;
import com.udaan.service.impl.FlightServiceImpl;

@RestController
@RequestMapping("/api/flight")
@CrossOrigin("*")
public class FlightController {

	@Autowired
	private FlightServiceImpl flightservice;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Flight>> getAllFlights(){
		List<Flight> flt = flightservice.getAllFlights();
		return new ResponseEntity<List<Flight>>(flt, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<Flight> addFlight(@RequestBody FlightDto flight){
		Flight flt = flightservice.addFlight(flight);
		return new ResponseEntity<Flight>(flt, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uuid}")
	public ResponseEntity<Flight> updateFlight(@RequestBody FlightDto flight,@PathVariable String uuid){
		Flight flt = flightservice.updateFlight(flight, uuid);
		if(flt != null) {
			return new ResponseEntity<Flight>(flt, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<String> deleteFlight(@PathVariable String uuid){
		String flight = flightservice.deleteFlight(uuid);
		if(flight == null) {
			return new ResponseEntity<>("Flight Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(flight, HttpStatus.OK);
	}
	
	@GetMapping("getBySourceAndDestination/{source}/{destination}/{date}")
	public ResponseEntity<List<Flight>> getBySourceAndDestination(@PathVariable String source,@PathVariable String destination, @PathVariable String date){
		List<Flight> flight = flightservice.findBySourceAndDestination(source, destination, date);
		if(flight != null) {
			return new ResponseEntity<List<Flight>>(flight,HttpStatus.OK);
		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
}
