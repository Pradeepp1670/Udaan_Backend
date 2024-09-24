package com.udaan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udaan.entity.Cities;
import com.udaan.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

	Flight findByUuid(String uuid);

	List<Flight> findBySourceAndDestination(Cities src, Cities dst);

	

}
