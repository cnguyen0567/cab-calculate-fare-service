package com.example.cab_calculate_fare_service.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CabBooking {
	
	private int id;
	private String startAddress;
	private String destAddress;
	private String cabType;
	private Double fare;
	
	//Special Constructor for the Calculate Fare service, which only needs these 3 info
	public CabBooking(String startAddress, String destAddress, String cabType) {
		super();
		this.startAddress = startAddress;
		this.destAddress = destAddress;
		this.cabType = cabType;
	}
	
	

}
