package com.udaan.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid"})})
public class Flight {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String uuid;
	private Date createdOn;
	private Date modifiedOn;
	private String flightName;
	private String flightNumber;
	private int seats;
	private LocalTime deppTime;
	private LocalTime arrTime;
	private List<String> days;
	private float price;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cities source;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Cities destination;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private FlightCompany company;
}
