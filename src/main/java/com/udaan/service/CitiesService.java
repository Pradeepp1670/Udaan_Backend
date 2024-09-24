package com.udaan.service;

import java.util.List;
import java.util.UUID;

import com.udaan.dto.CitiesDto;
import com.udaan.entity.Cities;

public interface CitiesService {
	
	public List<Cities> getAllCities();
	
	public Cities addCity(CitiesDto city);
	
	public Cities updateCity(CitiesDto city, String uuid);
	
	public String deleteCity(String uuid);
}
