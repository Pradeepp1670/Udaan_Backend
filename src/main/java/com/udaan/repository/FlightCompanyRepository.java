package com.udaan.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udaan.entity.FlightCompany;

@Repository
public interface FlightCompanyRepository extends JpaRepository<FlightCompany, Integer> {

	FlightCompany findByUuid(String uuid);

	FlightCompany findByCompanyName(String company);

}
