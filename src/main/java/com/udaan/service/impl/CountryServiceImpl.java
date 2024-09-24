package com.udaan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.dto.CountryDto;
import com.udaan.entity.Country;
import com.udaan.repository.CountryRepository;
import com.udaan.service.CountryService;

@Service
public class CountryServiceImpl  implements CountryService{

	@Autowired
	CountryRepository cRepo;
	
	@Override
	public List<Country> getAllCountry() {
		return cRepo.findAll();
	}

	@Override
	public Country addCountry(CountryDto country) {
		if(cRepo.findByCountryCode(country.getCountryCode()) == null) {
			Country ct = new Country();
			ct.setCountry(country.getCountry());
			ct.setCountryCode(country.getCountryCode());
			ct.setPhoneCode(country.getPhoneCode());
			ct.setUuid(UUID.randomUUID().toString());
			Date date = new Date();
			ct.setCreatedOn(date);
			ct.setModifiedOn(date);
			
			return cRepo.save(ct);
		}
		return null;
	}

	@Override
	public Country updateCountry(CountryDto country, String uuid) {
		Country ct = cRepo.findByUuid(uuid);

		if(ct != null) {
			ct.setCountry(country.getCountry());
			ct.setCountryCode(country.getCountryCode());
			ct.setPhoneCode(country.getPhoneCode());
			Date date = new Date();
			ct.setModifiedOn(date);
			
			return cRepo.save(ct);
		}
		return null;
	}

	@Override
	public String deleteCountry(String uuid) {
		Country ct = cRepo.findByUuid(uuid);
		if(ct != null) {
			cRepo.delete(ct);
			return "Deleted Successfully";
		}
		return null;
	}

}
