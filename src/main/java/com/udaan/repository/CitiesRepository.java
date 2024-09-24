package com.udaan.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udaan.entity.Cities;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer> {

	Cities findByCityAndCountryCountry(String city, String country);

	Cities findByUuid(String uuid);

	Cities findByCity(String source);


}
