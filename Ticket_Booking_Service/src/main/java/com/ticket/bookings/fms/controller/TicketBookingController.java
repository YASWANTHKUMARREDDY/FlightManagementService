package com.ticket.bookings.fms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.bookings.fms.VO.TicketBookingData;
import com.ticket.bookings.fms.VO.TicketResponse;
import com.ticket.bookings.fms.service.TicketServices;
import com.ticket.bookings.utils.BaseUrls;
import com.ticket.bookings.utils.OwnException;
import com.ticket.bookings.utils.OwnResponse;

@RestController
public class TicketBookingController {
	
	
	private static final Logger log = LoggerFactory.getLogger(TicketBookingController.class);
	
	@Autowired
	private TicketServices ticketServices;
	
	// Output for this Service is List of Ticket no's.
	@PostMapping(value = BaseUrls.BOOKATICKET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> bookTicket(@RequestBody TicketBookingData input) {
		log.info("Ticket Booking Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			List<String> result = ticketServices.ticketBooking(input);
			return new OwnResponse<>(result, true, HttpStatus.CREATED);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Ticket Booking " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Ticket Booking " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = BaseUrls.VIEWTICKET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> viewTicketData(@RequestParam String ticketNo) {
		log.info("Ticket Details Api Calling..");
		try {
			if (!StringUtils.hasLength(ticketNo))
				throw new OwnException("Ticket No Missing....");
			 TicketResponse result = ticketServices.getTicketDetails(ticketNo);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Ticket Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Ticket Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Ticket Created User only has access to cancel the Ticket
	@GetMapping(value = BaseUrls.CANCELTICKET, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> ticketCancellation(@RequestParam String ticketNo, @RequestParam String user) {
		log.info("Ticket Cancellation Api Calling..");
		try {
			if (!StringUtils.hasLength(ticketNo))
				throw new OwnException("Ticket No Missing....");
			 String result = ticketServices.cancelTicket(ticketNo,user);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Ticket Cancellation " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Ticket Cancellation " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
