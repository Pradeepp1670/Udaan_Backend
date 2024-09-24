package com.udaan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.dto.CitiesDto;
import com.udaan.entity.Cities;
import com.udaan.entity.Country;
import com.udaan.repository.CitiesRepository;
import com.udaan.repository.CountryRepository;
import com.udaan.service.CitiesService;

@Service
public class CitiesServiceImpl implements CitiesService {

	@Autowired
	CitiesRepository cityRepo;
	
	@Autowired
	CountryRepository countryRepo;
	
	@Override
	public List<Cities> getAllCities() {
		
		return cityRepo.findAll();
	}

	@Override
	public Cities addCity(CitiesDto city) {
		if(cityRepo.findByCityAndCountryCountry(city.getCity(), city.getCountry()) == null) {
			Cities ct= new Cities();
			ct.setCity(city.getCity());
			ct.setAirport(city.getAirport());
			ct.setCityCode(city.getCityCode());
			Country country = countryRepo.findByCountry(city.getCountry());
			ct.setCountry(country);
			ct.setUuid(UUID.randomUUID().toString());
			Date date = new Date();
			ct.setCreatedOn(date);
			ct.setModifiedOn(date);
			
			return cityRepo.save(ct);
		}
		return null;
	}

	@Override
	public Cities updateCity(CitiesDto city, String uuid) {
		Cities ct= cityRepo.findByUuid(uuid);
		if( ct != null) {
			ct.setCity(city.getCity());
			ct.setAirport(city.getAirport());
			ct.setCityCode(city.getCityCode());
			ct.setCountry(ct.getCountry());
			Date date = new Date();
			ct.setModifiedOn(date);
			
			return cityRepo.save(ct);
		}
		return null;
	}

	@Override
	public String deleteCity(String uuid) {
		Cities city = cityRepo.findByUuid(uuid);
		if( city != null) {
			cityRepo.delete(city);
		}
		return null;
	}

}
