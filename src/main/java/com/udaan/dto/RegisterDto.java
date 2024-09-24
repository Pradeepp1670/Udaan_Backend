package com.udaan.dto;

import lombok.Data;

@Data
public class RegisterDto {

	private String fname;
	private String lname;
	private long mobileNo;
	private String email;
	private String password;
}
