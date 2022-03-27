package com.ticket.bookings.fms.service;

import java.util.List;

import com.ticket.bookings.fms.VO.TicketBookingData;
import com.ticket.bookings.fms.VO.TicketResponse;

public interface TicketServices {
	
	public List<String> ticketBooking(TicketBookingData input);
	
	public TicketResponse getTicketDetails(String ticketno);
	
	public String cancelTicket(String ticketNo, String user);

}
