package com.udaan.dto;

import java.util.List;

import com.udaan.entity.Flight;
import com.udaan.entity.Passanger;
import com.udaan.entity.User;

import lombok.Data;

@Data
public class BookingDto {
	
	private String userUuid;
	private String flightUuid;
	private List<Passanger> passengers;

}
