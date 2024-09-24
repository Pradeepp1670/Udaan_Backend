package com.udaan.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.udaan.entity.Cities;
import com.udaan.entity.FlightCompany;

import lombok.Data;

@Data
public class FlightDto {

	private int seats;
	private String deppTime;
	private String arrTime;
	private List<String> days;
	private float price;
	private String source;
	private String destination;
	private String company;
	 
}
