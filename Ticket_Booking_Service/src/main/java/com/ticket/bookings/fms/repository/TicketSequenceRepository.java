package com.ticket.bookings.fms.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ticket.bookings.fms.entity.TicketNoSequence;

public interface TicketSequenceRepository extends CrudRepository<TicketNoSequence, Serializable>{
	
	Optional<TicketNoSequence> findByTicketSequence(Boolean status);

}
