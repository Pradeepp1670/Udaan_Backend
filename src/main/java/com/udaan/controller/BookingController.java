package com.udaan.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.udaan.dto.BookingDto;
import com.udaan.entity.Booking;
import com.udaan.service.BookingService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/book")
public class BookingController {

	@Autowired
	BookingService bookService;
	

	@GetMapping("/getAll")
	public ResponseEntity<List<Booking>> getAllBookings(){
		List<Booking> book = bookService.getAllBookings();
		return new ResponseEntity<List<Booking>>(book, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Booking> addBooking(@RequestBody BookingDto book){
		Booking obj = bookService.addBooking(book);
		return new ResponseEntity<Booking>(obj, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{uuid}")
	public ResponseEntity<Booking> updateBooking(@RequestBody BookingDto book,@PathVariable String uuid){
		Booking obj = bookService.updateBooking(book, uuid);
		if(obj != null) {
			return new ResponseEntity<Booking>(obj, HttpStatus.OK);
		}
		return new ResponseEntity<>(obj, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/cancel/{uuid}")
	public ResponseEntity<String> cancelBooking(@PathVariable String uuid){
		Booking book = bookService.cancelBooking(uuid);
		if(book == null) {
			return new ResponseEntity<>("Booking id not found", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>("Booking Cancelled Successfully", HttpStatus.OK);
	}

}
