package com.example.cab_calculate_fare_service.controller;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cab_calculate_fare_service.model.CabBooking;
import com.example.cab_calculate_fare_service.model.CalculateFareResponse;

@RestController                     //@RestController = @Controller + @ResponseBody. @ResponseBody returns HTTP response body (i.e, payload data) and not a html view
@RequestMapping("/calculatefare")   //@RequestMapping show how to call this class by api
public class CalculationController {
	private static final Logger logger = LogManager.getLogger(CalculationController.class);
	
	/**
	 * Fxn to calculate the cab fare,
	 * 		which takes in CabBooking request body and and returns calculated fare body response
	 * @ResponseEntity = Spring class that provides a flexible way to handle HTTP responses: status code, headers, and body
	 * allowing fully customize the HTTP response sent to the client
	 * @RequestBody: mainly used with CRUD Operations to read the request body.
	 * ResponseEntity.ok(response) = A shortcut for ResponseEntity.status(OK status).body(response)
	 * */
	@PostMapping
	public ResponseEntity<CalculateFareResponse> calculate(@RequestBody CabBooking cabBooking) {
		
		logger.info("Start cab_calculate_fare_service.CalculationController.calculate()");
		int min = 20;
		int max = 200;
		
		if (cabBooking.getStartAddress().equals(cabBooking.getDestAddress())) {
			CalculateFareResponse badBooking = CalculateFareResponse.builder().message("Destination address is the same as start address")  //Change to a non-null body for test
					.startAddress(cabBooking.getStartAddress())
					.destAddress(cabBooking.getDestAddress())
					.cabType(cabBooking.getCabType())
					.build();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badBooking);
		}
		
		Double fare = (double) Math.round( (min + Math.random() * (max-min))*100.0)/100.0;
		
		CalculateFareResponse response = new CalculateFareResponse("Calculation completed", fare);
		return ResponseEntity.ok(response);
	}
}
