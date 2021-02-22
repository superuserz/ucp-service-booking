package com.nagp.ucp.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	@GetMapping(value = "/greet")
	public String greet() {
		return "Hello from booking";
	}
}