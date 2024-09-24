package com.udaan.service;

import java.util.List;
import java.util.UUID;

import com.udaan.dto.FlightCompanyDto;
import com.udaan.entity.FlightCompany;

public interface FlightCompanyService {

	public List<FlightCompany> getAllCompanies();
	
	public FlightCompany addCompany(FlightCompanyDto company);
	
	public FlightCompany updateCompany(FlightCompanyDto company, String uuid);
	
	public String deleteCompany(String uuid);
}
