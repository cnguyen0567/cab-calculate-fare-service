package com.example.cab_calculate_fare_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CalculateFareResponse {
	@JsonProperty("message")  //this transforms the json input to the java object
	private String message;
	@JsonProperty("startAddress")
	private String startAddress;
	@JsonProperty("destAddress")
	private String destAddress;
	@JsonProperty("cabType")
	private String cabType; 
	@JsonProperty("fare")
	private Double fare; // calculation performed
    
    public CalculateFareResponse() {
    }
    
    public CalculateFareResponse(String message, Double fare) { //A special constructor =/= allargsconstructor
        this.message=message;
    	this.fare = fare;
    }

}
