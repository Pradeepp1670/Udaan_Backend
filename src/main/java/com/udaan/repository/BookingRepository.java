package com.udaan.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udaan.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	Booking findByUuid(String uuid);

}
