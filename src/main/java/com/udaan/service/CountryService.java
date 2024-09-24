package com.udaan.service;

import java.util.List;
import java.util.UUID;

import com.udaan.dto.CountryDto;
import com.udaan.entity.Country;

public interface CountryService {

	public List<Country> getAllCountry();
	
	public Country addCountry(CountryDto country);
	
	public Country updateCountry(CountryDto country, String uuid);
	
	public String deleteCountry(String uuid);
}
