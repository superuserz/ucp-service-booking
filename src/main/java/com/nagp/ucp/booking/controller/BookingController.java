package com.nagp.ucp.booking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

	@GetMapping(value = "/greet")
	public String greet() {
		return "Hello from booking";
	}
}