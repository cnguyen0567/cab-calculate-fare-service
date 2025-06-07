package com.example.cab_calculate_fare_service.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.cab_calculate_fare_service.model.CabBooking;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CalculationController.class)
public class CalculationControllerTest {
	
	@Autowired
	private MockMvc mockMvc;   //mock a call to server
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	void testCabBookingRequest() throws Exception {
		//Set the test case
		CabBooking cabBooking = new CabBooking("Address 1", "Different Address 2", "Standard");
		
		//Run test
		String response = mockMvc.perform(post("/calculatefare")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cabBooking))) //turn the java obj to json
						.andExpect(status().isOk())                            //test that we expect ok status
						.andReturn()                                           //return the responsebody of the request
						.getResponse()                                         //Get the simulated HTTP response
						.getContentAsString();                                 //Extracts the body of the HTTP response as a plain String.

		//Extract the fare result
		JsonNode root = objectMapper.readTree(response);
	    
		//Test the fare
	    assertTrue(root.has("fare"), "Response should contain 'fare' field");
	    assertTrue(root.get("fare").isDouble(), "Fare should be a double");
	}
	
	@Test
	void testBadCabBookingRequest() throws Exception {
		CabBooking cabBooking = new CabBooking("Same Address", "Same Address", "Standard");
		mockMvc.perform(post("/calculatefare")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cabBooking))) //turn the java obj to json
						.andExpect(status().isBadRequest());                   //test that we expect bad request
	}

}
