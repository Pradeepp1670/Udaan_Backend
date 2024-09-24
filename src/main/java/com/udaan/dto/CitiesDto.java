package com.udaan.dto;

import java.util.Date;

import com.udaan.entity.Country;

import lombok.Data;

@Data
public class CitiesDto {

	private String city;
	private String cityCode;
	private String airport;
	private String country;
}
