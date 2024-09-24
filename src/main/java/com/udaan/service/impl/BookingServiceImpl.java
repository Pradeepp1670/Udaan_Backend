package com.udaan.service.impl;

import java.util.*;

import com.udaan.entity.Flight;
import com.udaan.entity.User;
import com.udaan.repository.FlightRepository;
import com.udaan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udaan.dto.BookingDto;
import com.udaan.entity.Booking;
import com.udaan.repository.BookingRepository;
import com.udaan.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository brepo;

	@Autowired
	FlightRepository frepo;

	@Autowired
	UserRepository urepo;
	
	@Override
	public List<Booking> getAllBookings() {
		return brepo.findAll();
	}

	@Override
	public Booking addBooking(BookingDto book) {
		Booking bk = new Booking();
		Flight flight = frepo.findByUuid(book.getFlightUuid());
		bk.setFlight(flight);
		User user = urepo.findByUuid(book.getUserUuid());
		bk.setUser(user);
		bk.setPassangers(book.getPassengers());
		bk.setUuid(UUID.randomUUID().toString());
		
		Random random = new Random();
        StringBuilder pnr = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            pnr.append(random.nextInt(10));
        }
        bk.setPnr(pnr.toString());
        
        Date date = new Date();
        bk.setCreatedOn(date);
        bk.setModifiedOn(date);
        bk.setStatus("Booked");
        
        float price = flight.getPrice();

        price *= book.getPassengers().size();
        
        bk.setTotalAmount(price);
        
        return brepo.save(bk);

	}

	@Override
	public Booking updateBooking(BookingDto book, String uuid) {
		Booking bk =brepo.findByUuid(uuid);
		if(bk != null) {
			Flight flight = frepo.findByUuid(book.getFlightUuid());
			bk.setFlight(flight);
			bk.setPassangers(book.getPassengers());
	        
	        Date date = new Date();
	        bk.setModifiedOn(date);
	        
	        float price = flight.getPrice();
	        price += (price * 18)/100; //GST 18%
	        price += (price * 5)/100;   // CGST 2.5% + SGST 2.5%
	        price *= book.getPassengers().size();
	        
	        bk.setTotalAmount(price);
	        
	        return brepo.save(bk);
		}
		return null;
	}

	@Override
	public Booking cancelBooking(String uuid) {
		Booking bk =brepo.findByUuid(uuid);
		if(bk != null) {
			bk.setStatus("Cancelled");
			return brepo.save(bk);
		}
		return null;
	}

}
