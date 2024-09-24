package com.udaan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.dto.FlightCompanyDto;
import com.udaan.entity.FlightCompany;
import com.udaan.repository.FlightCompanyRepository;
import com.udaan.service.FlightCompanyService;

@Service
public class FlightCompanyServiceImpl implements FlightCompanyService {

	@Autowired
	FlightCompanyRepository fRepo;
	
	@Override
	public List<FlightCompany> getAllCompanies() {
		
		return fRepo.findAll();
	}

	@Override
	public FlightCompany addCompany(FlightCompanyDto company) {
		FlightCompany cmpn = new FlightCompany();
		cmpn.setUuid(UUID.randomUUID().toString());
		cmpn.setAddress(company.getAddress());
		cmpn.setCompanyName(company.getCompanyName());
		cmpn.setLogo(company.getLogo());
		
		Date date = new Date();
		cmpn.setCreatedOn(date);
		cmpn.setModifiedOn(date);
		
		return fRepo.save(cmpn);

	}

	@Override
	public FlightCompany updateCompany(FlightCompanyDto company, String uuid) {
		FlightCompany cmpn = fRepo.findByUuid(uuid);
		if(cmpn != null) {
			//cmpn.setAddress(company.getAddress());
			//cmpn.setCompanyName(company.getCompanyName());
			cmpn.setLogo(company.getLogo());
			
			Date date = new Date();
			cmpn.setModifiedOn(date);
			
			return fRepo.save(cmpn);
		}
		return null;
	}

	@Override
	public String deleteCompany(String uuid) {
		FlightCompany cmpn = fRepo.findByUuid(uuid);
		if(cmpn != null) {
			fRepo.delete(cmpn);
			return "Company Deleted Successfully";
		}
		return null;
	}

}
