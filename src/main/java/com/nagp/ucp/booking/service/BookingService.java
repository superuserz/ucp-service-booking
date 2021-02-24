package com.nagp.ucp.booking.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.ucp.booking.client.UserClient;
import com.nagp.ucp.booking.domain.Booking;
import com.nagp.ucp.booking.dto.User;
import com.nagp.ucp.booking.repository.BookingRepository;
import com.nagp.ucp.common.constant.QueueConstants;
import com.nagp.ucp.common.enums.BookingStatusEnum;
import com.nagp.ucp.common.enums.PaymentModeEnum;
import com.nagp.ucp.common.exception.UCPException;
import com.nagp.ucp.common.request.AddBookingRequest;
import com.nagp.ucp.common.request.AssignBookingRequest;
import com.nagp.ucp.common.request.BookingCommentRequest;
import com.nagp.ucp.common.request.NotificationPayload;
import com.nagp.ucp.common.request.UpdateBookingStatusRequest;
import com.nagp.ucp.common.responses.BaseResponse;

@Service
public class BookingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookingService.class);

	@Autowired
	private BookingRepository repository;

	@Autowired
	private UserClient userClient;

	@Autowired
	private RabbitTemplate publisher;

	public Booking getBookingById(int id) throws UCPException {
		Optional<Booking> booking = repository.findById(id);
		if (booking.isPresent()) {
			return booking.get();
		} else {
			throw new UCPException("ucp.service.booking.001", "Booking ID : " + id + " Not Found");
		}
	}

	public void assignProvider(final AssignBookingRequest request) throws UCPException {
		BaseResponse<User> response = null;
		User user = null;
		try {
			response = userClient.getProvider(request.getProviderId());
		} catch (Exception e) {
			LOGGER.error("Unable to Fetch User Details From Client : " + e.getMessage());
			throw new UCPException("ucp.service.booking.002",
					"Unable to Assign Provider. Make sure the Provider ID exists");
		}
		if (response != null) {
			user = response.getData();
			Booking booking = getBookingById(request.getBookingId());
			booking.setAssigneeId(user.getId());
			booking.setAssigneeName(user.getName());
			booking.setBookingStatus(BookingStatusEnum.ASSIGNED);
			repository.save(booking);
		} else {
			throw new UCPException("ucp.service.booking.002",
					"Unable to Assign Provider. Make sure the Provider ID exists");
		}

		String message = "Your Booking has been assigned to " + user.getName() + ". Contact the Provider on "
				+ user.getContact() + ".";
		NotificationPayload payload = new NotificationPayload(message,
				"Provider Assigned. Booking ID : " + request.getBookingId(), null, null);
		publisher.convertAndSend(QueueConstants.NOTIFICATION_EXCHANGE, QueueConstants.NOTIFICATION_ROUTING, payload);

	}

	public Booking updateBookingStatus(final UpdateBookingStatusRequest request) {
		String message = null;
		String subject = "Your Booking Status Updated.";
		Booking booking = getBookingById(request.getBookingId());
		if (null == BookingStatusEnum.parse(request.getStatus())) {
			throw new UCPException("ucp.service.booking.003", "Invalid Status. Please provide a valid one");
		}
		if (BookingStatusEnum.ACCEPTED.getValue().equalsIgnoreCase(request.getStatus())) {
			booking.setBookingStatus(BookingStatusEnum.parse(request.getStatus()));
			message = "Your Booking with order ID : " + request.getBookingId() + " has been ACCEPTED.";
		} else if (BookingStatusEnum.REJECTED.getValue().equalsIgnoreCase(request.getStatus())) {
			booking.setBookingStatus(BookingStatusEnum.parse(request.getStatus()));
			message = "Your Booking with order ID : " + request.getBookingId()
					+ " has been REJECTED. New provider Will be Assigned Soon";
		} else if (BookingStatusEnum.INPROCESS.getValue().equalsIgnoreCase(request.getStatus())) {
			booking.setBookingStatus(BookingStatusEnum.parse(request.getStatus()));
		} else if (BookingStatusEnum.COMPLETED.getValue().equalsIgnoreCase(request.getStatus())) {
			booking.setBookingStatus(BookingStatusEnum.parse(request.getStatus()));
		}

		booking = repository.save(booking);
		NotificationPayload payload = new NotificationPayload(message, subject + " Booking ID : " + booking.getId(),
				null, null);
		publisher.convertAndSend(QueueConstants.NOTIFICATION_EXCHANGE, QueueConstants.NOTIFICATION_ROUTING, payload);
		return booking;

	}

	public BaseResponse<Booking> createBooking(final AddBookingRequest request) {

		Booking booking = new Booking();
		booking.setServiceId(request.getServiceId());
		booking.setUserId(request.getUserId());
		booking.setBookingStatus(BookingStatusEnum.NEW);
		booking.setServiceAmount(request.getServiceAmount());
		booking.setPaymentMode(PaymentModeEnum.parse(request.getPaymentMode()));
		booking = repository.save(booking);

		// send new booking creation notification to user.
		String message = "Your Booking with order ID : " + booking.getId()
				+ " has been registered. Provider will be assigned soon.";
		NotificationPayload payload = new NotificationPayload(message,
				"Booking Created. Booking ID : " + booking.getId(), null, null);
		publisher.convertAndSend(QueueConstants.NOTIFICATION_EXCHANGE, QueueConstants.NOTIFICATION_ROUTING, payload);
		return new BaseResponse<>(booking);
	}

	public Booking addBookingComment(final BookingCommentRequest request) {

		Booking booking = null;
		Optional<Booking> bookingOpt = repository.findById(request.getBookingId());
		if (bookingOpt.isPresent()) {
			booking = bookingOpt.get();
			String newComment = "[" + LocalDateTime.now() + "] -- " + request.getComment() + " ";
			booking.setComment((booking.getComment() == null ? "" : booking.getComment()) + newComment);
		} else {
			throw new UCPException("ucp.service.booking.001", "Booking ID : " + request.getBookingId() + " Not Found");
		}

		return repository.save(booking);

	}
}
