package com.udaan.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udaan.dto.CountryDto;
import com.udaan.entity.Country;
import com.udaan.service.impl.CountryServiceImpl;

@RestController
@RequestMapping("/api/country")
@CrossOrigin("*")
public class CountryController {

	@Autowired
	CountryServiceImpl countryService;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Country>> getAllCountry(){
		List<Country> ct = countryService.getAllCountry();
		return new ResponseEntity<List<Country>>(ct, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Country> addCountry(@RequestBody CountryDto country){
		Country ct = countryService.addCountry(country);
		return new ResponseEntity<Country>(ct, HttpStatus.OK);
	}
	
	@PutMapping("/update/{uuid}")
	public ResponseEntity<Country> updateCountry(@RequestBody CountryDto country,@PathVariable String uuid){
		Country ct = countryService.updateCountry(country, uuid);
		if(ct != null) {
			return new ResponseEntity<Country>(ct, HttpStatus.OK);
		}
		return new ResponseEntity<>(ct, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<String> deleteCity(@PathVariable String uuid){
		String ct = countryService.deleteCountry(uuid);
		if(ct == null) {
			return new ResponseEntity<>("Country Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(ct, HttpStatus.OK);
	}
}
