package com.nagp.ucp.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagp.ucp.booking.domain.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
