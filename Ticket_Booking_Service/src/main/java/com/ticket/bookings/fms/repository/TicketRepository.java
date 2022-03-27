package com.ticket.bookings.fms.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticket.bookings.fms.entity.TicketDetails;

@Repository
public interface TicketRepository extends CrudRepository<TicketDetails, Serializable> {

	Optional<TicketDetails> findByTicketNo(String ticketNo);
}
