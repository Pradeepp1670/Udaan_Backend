package com.udaan.entity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"uuid"})})
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String uuid;
	private Date createdOn;
	private Date modifiedOn;
	private String pnr;
	private float totalAmount;
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL)
	 @JoinColumn(name = "booking_id", referencedColumnName = "id")
	private List<Passanger> passangers;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@ManyToOne
	private Flight flight;
}
