package com.udaan.service;

import java.util.List;
import java.util.UUID;

import com.udaan.dto.BookingDto;
import com.udaan.entity.Booking;

public interface BookingService {

	public List<Booking> getAllBookings();
	
	public Booking addBooking(BookingDto book);
	
	public Booking updateBooking(BookingDto book, String uuid);
	
	public Booking cancelBooking(String uuid);
}
