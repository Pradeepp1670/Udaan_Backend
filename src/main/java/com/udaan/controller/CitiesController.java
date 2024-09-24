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

import com.udaan.dto.CitiesDto;
import com.udaan.entity.Cities;
import com.udaan.service.impl.CitiesServiceImpl;

@RestController
@RequestMapping("/api/city")
@CrossOrigin("*")
public class CitiesController {
	
	@Autowired
	CitiesServiceImpl cityService;

	@PreAuthorize("permitAll()")
	@GetMapping("/getAll")
	public ResponseEntity<List<Cities>> getAllCities(){
		List<Cities> flt = cityService.getAllCities();
		return new ResponseEntity<List<Cities>>(flt, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Cities> addCity(@RequestBody CitiesDto city){
		Cities ct = cityService.addCity(city);
		return new ResponseEntity<Cities>(ct, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uuid}")
	public ResponseEntity<Cities> updateCity(@RequestBody CitiesDto city,@PathVariable String uuid){
		Cities ct = cityService.updateCity(city, uuid);
		if(ct != null) {
			return new ResponseEntity<Cities>(ct, HttpStatus.OK);
		}
		return new ResponseEntity<>(ct, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<String> deleteCity(@PathVariable String uuid){
		String ct = cityService.deleteCity(uuid);
		if(ct == null) {
			return new ResponseEntity<>("City Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ct, HttpStatus.OK);
	}

}
