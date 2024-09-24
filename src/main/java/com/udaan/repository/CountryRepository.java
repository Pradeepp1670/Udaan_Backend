package com.udaan.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udaan.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	Country findByCountryCode(String countryCode);

	Country findByUuid(String uuid);

	Country findByCountry(String country);

}
