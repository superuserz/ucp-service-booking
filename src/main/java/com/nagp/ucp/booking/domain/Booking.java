package com.nagp.ucp.booking.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nagp.ucp.common.enums.BookingStatusEnum;
import com.nagp.ucp.common.enums.PaymentModeEnum;

import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@ToString
@NoArgsConstructor
public class Booking {
	/**
	 * Unique identifier for a service
	 */
	@Id
	@NonNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/**
	 * ID from the catalog service for which booking is to be done
	 */
	@Column
	private int serviceId;

	/**
	 * ID of the User Requesting the service
	 */
	@Column
	private int userId;

	/**
	 * ID of the User to which service is assigned
	 */
	@Column
	private int assigneeId;

	/**
	 * Booking Status
	 */
	@Column
	private String bookingStatus;

	/**
	 * Additional Comments for the Booking
	 */
	@Column
	private String comment;

	/**
	 * Amount To be Paid by Customer
	 */
	@Column
	private double serviceAmount;

	/**
	 * Payment Mode for the Booking
	 */
	@Column
	private String paymentMode;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp createdOn;

	@Column
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp modifiedOn;

	public Booking(int id, int serviceId, int userId, int assigneeId, String bookingStatus, String comment,
			double serviceAmount, String paymentMode, Timestamp createdOn, Timestamp modifiedOn) {
		super();
		this.id = id;
		this.serviceId = serviceId;
		this.userId = userId;
		this.assigneeId = assigneeId;
		this.bookingStatus = bookingStatus;
		this.comment = comment;
		this.serviceAmount = serviceAmount;
		this.paymentMode = paymentMode;
		this.createdOn = createdOn;
		this.modifiedOn = modifiedOn;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(int assigneeId) {
		this.assigneeId = assigneeId;
	}

	public BookingStatusEnum getBookingStatus() {
		return BookingStatusEnum.parse(this.bookingStatus);
	}

	public void setBookingStatus(final BookingStatusEnum bookingStatus) {
		this.bookingStatus = bookingStatus.getValue();
	}

	public PaymentModeEnum getPaymentMode() {
		return PaymentModeEnum.parse(this.paymentMode);
	}

	public void setPaymentMode(PaymentModeEnum paymentMode) {
		this.paymentMode = paymentMode.getValue();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getServiceAmount() {
		return serviceAmount;
	}

	public void setServiceAmount(double serviceAmount) {
		this.serviceAmount = serviceAmount;
	}

	public Timestamp getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}

	public Timestamp getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Timestamp modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
