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

import com.udaan.dto.FlightCompanyDto;
import com.udaan.entity.FlightCompany;
import com.udaan.service.impl.FlightCompanyServiceImpl;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class FlightCompanyController {

	@Autowired
	private FlightCompanyServiceImpl companyservice;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<FlightCompany>> getAllCompanies(){
		List<FlightCompany> flt = companyservice.getAllCompanies();
		return new ResponseEntity<List<FlightCompany>>(flt, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<FlightCompany> addCompany(@RequestBody FlightCompanyDto company){
		FlightCompany flt = companyservice.addCompany(company);
		return new ResponseEntity<FlightCompany>(flt, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uuid}")
	public ResponseEntity<FlightCompany> updateCompany(@RequestBody FlightCompanyDto company,@PathVariable String uuid){
		FlightCompany flt = companyservice.updateCompany(company, uuid);
		if(flt != null) {
			return new ResponseEntity<FlightCompany>(flt, HttpStatus.OK);
		}
		return new ResponseEntity<>(flt, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{uuid}")
	public ResponseEntity<String> deleteCompany(@PathVariable String uuid){
		String flight = companyservice.deleteCompany(uuid);
		if(flight == null) {
			return new ResponseEntity<>("Flight Company Not Found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(flight, HttpStatus.OK);
	}
}
