package com.ticket.bookings.fms.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ticket.bookings.fms.VO.TicketBookingData;
import com.ticket.bookings.fms.VO.TicketResponse;
import com.ticket.bookings.fms.entity.TicketDetails;
import com.ticket.bookings.fms.repository.TicketRepository;
import com.ticket.bookings.fms.service.TicketServices;
import com.ticket.bookings.utils.OwnException;
import com.ticket.bookings.utils.TicketMapper;
import com.ticket.bookings.utils.TicketStatusEnum;

@Service
public class TicketServiceImpl implements TicketServices {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TicketMapper ticketMapper;

	@Override
	public List<String> ticketBooking(TicketBookingData input) {
		if(!StringUtils.hasLength(input.getFlightNo()))
			throw new OwnException("Flight No Missing....");
		if(!StringUtils.hasLength(input.getUserId()))
			throw new OwnException("User Name Missing....");
		List<TicketDetails> ticketDetails = ticketMapper.convertToEntity(input);
		ticketRepository.saveAll(ticketDetails);
		List<String> ticketNos = ticketDetails.stream().map(a -> a.getTicketNo()).collect(Collectors.toList());
		// RestCall to Flight Details for Modifying Remaining seats.
		return ticketNos;
	}

	@Override
	public TicketResponse getTicketDetails(String ticketNo) {
		Optional<TicketDetails> ticketDetails = ticketRepository.findByTicketNo(ticketNo.toUpperCase());
		if (!ticketDetails.isPresent())
			throw new OwnException("Invalid Ticket No : " + ticketNo);
		return ticketMapper.convertToResponse(ticketDetails.get());
	}

	@Override
	public String cancelTicket(String ticketNo, String user) {
		Optional<TicketDetails> ticketDetails = ticketRepository.findByTicketNo(ticketNo.toUpperCase());
		if (!ticketDetails.isPresent())
			throw new OwnException("Invalid Ticket No : " + ticketNo);
		if (!ticketDetails.get().getUserId().equalsIgnoreCase(user)
				|| ticketDetails.get().getJourneyDate().isBefore(LocalDate.now()))
			throw new OwnException("Can't Cancel the Ticket..");
		ticketDetails.get().setTicketStatus(TicketStatusEnum.CANCELED.getName());
		ticketDetails.get().setModifiedBy(user);
		ticketDetails.get().setModifiedDate(LocalDate.now());
		ticketRepository.save(ticketDetails.get());
		// RestCall to Flight Details for Modifying Remaining seats.
		return "Ticket No : "+ticketNo + " Cancelled Succesfully";
	}
}
