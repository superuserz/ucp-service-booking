package com.nagp.ucp.booking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.ucp.booking.domain.Booking;
import com.nagp.ucp.booking.service.BookingService;
import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.common.request.AddBookingRequest;
import com.nagp.ucp.common.request.AssignBookingRequest;
import com.nagp.ucp.common.request.BookingCommentRequest;
import com.nagp.ucp.common.request.UpdateBookingStatusRequest;
import com.nagp.ucp.common.responses.BaseResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/booking")
public class BookingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingService service;

	@GetMapping(value = "/{id}")
	@ApiOperation(value = "Get Booking Details by ID")
	public BaseResponse<Booking> getBooking(@PathVariable int id) throws UCPException {
		LOGGER.info("Getting Booking ID : " + id);
		return new BaseResponse<>(service.getBookingById(id));
	}

	@PostMapping("/assign")
	@ApiOperation(value = "Assign a worker to the Booking")
	public void assignProvider(@RequestBody final AssignBookingRequest request) throws UCPException {
		LOGGER.info("Assign Booking : " + request.toString());
		service.assignProvider(request);
	}

	@PostMapping("/status")
	@ApiOperation(value = "Accept or Reject a Booking")
	public BaseResponse<Booking> updateBookingStatus(@RequestBody final UpdateBookingStatusRequest request)
			throws UCPException {
		LOGGER.info("Update Booking Status : " + request.toString());
		return new BaseResponse<>(service.updateBookingStatus(request));
	}

	@PostMapping("/new")
	@ApiOperation(value = "Create a New Booking in system")
	public BaseResponse<Booking> createBooking(@RequestBody final AddBookingRequest request) throws UCPException {
		LOGGER.info("Update Booking Status : " + request.toString());
		return service.createBooking(request);
	}

	@PostMapping("/comment")
	@ApiOperation(value = "Add Comments to a Booking")
	public BaseResponse<Booking> addBookingComment(@RequestBody final BookingCommentRequest request)
			throws UCPException {
		LOGGER.info("Adding Comment to Booking : " + request.toString());
		return new BaseResponse<>(service.addBookingComment(request));
	}

}