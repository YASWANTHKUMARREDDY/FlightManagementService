package com.flight.service.fms.serviceImpl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.flight.service.fms.entilty.FlightDetails;
import com.flight.service.fms.repository.FlightRepository;
import com.flight.service.fms.service.FlightBookingService;
import com.flight.service.fms.utils.ActionEnum;
import com.flight.service.fms.utils.OwnException;
import com.flight.service.fms.utils.StatusEnum;
import com.flight.service.fms.vo.AddDetailsRequest;
import com.flight.service.fms.vo.RouteDetailsRequest;
import com.flight.service.fms.vo.ScheduleFlightRequest;
import com.flight.service.fms.vo.SeatModification;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {

	private static final Logger log = LoggerFactory.getLogger(FlightBookingServiceImpl.class);

	// Checking every service with Journey Date As Same Flight No have all the week
	// to fly.

	@Autowired
	FlightRepository flightRepository;

	@Autowired
	FlightDetailsMapper flightDetailsMapper;

	@Override
	public String addFlightDetails(AddDetailsRequest input) {
		log.info("Input Validation Check at Add Flight Details Service ");
		if (!StringUtils.hasLength(input.getFlightNo()) || !StringUtils.hasLength(input.getFlightName()))
			throw new OwnException("Inputs Can't be null.....");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(input.getFlightNo(),
				input.getJourneyDate());
		if (flightDetails.isPresent())
			throw new OwnException("Flight Details Already Added for Flight No " + input.getFlightNo());

		log.info("Mapper Details at Add Flight Details Service ");
		FlightDetails entity = new FlightDetails();
		entity.setFlightNo(input.getFlightNo());
		entity.setFlightName(input.getFlightName());
		entity.setJourneyDate(input.getJourneyDate());
		log.info("Saving Flight Details at Add Flight Details Service ");
		flightRepository.save(entity);
		return "Flight Details Added for Flight No : " + input.getFlightNo();
	}

	@Override
	public String addRouteDetails(RouteDetailsRequest input) {
		log.info("Input Validation Check at Add Route Details Service ");
		if (!StringUtils.hasLength(input.getFlightNo()) || !StringUtils.hasLength(input.getFromRoute())
				|| !StringUtils.hasLength(input.getToRoute()))
			throw new OwnException("Inputs Can't be null.....");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(input.getFlightNo(),
				input.getJourneyDate());
		if (!flightDetails.isPresent())
			throw new OwnException("No Flight Details Exists on " + input.getFlightNo());
		flightDetails.get().setFromRoute(input.getFromRoute());
		flightDetails.get().setToRoute(input.getToRoute());
		flightRepository.save(flightDetails.get());
		return "Route Details Added Succesfully to FlightNo " + input.getFlightNo();
	}

	@Override
	public String scheduleFlight(ScheduleFlightRequest input) {
		log.info("Input Validation Check at Schedule Flight Service ");
		if (!StringUtils.hasLength(input.getFlightNo()))
			throw new OwnException("Inputs Can't be null.....");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(input.getFlightNo(),
				input.getJourneyDate());
		if (!flightDetails.isPresent())
			throw new OwnException("No Flight Details Exists on " + input.getFlightNo());
		flightDetails.get().setTotalSeats(input.getTotalSeats());
		flightDetails.get().setTicketPrice(input.getTicketPrice());
		flightDetails.get().setRemainingSeats(input.getTotalSeats());
		flightDetails.get().setStatus(Boolean.TRUE);
		flightDetails.get().setFlightStatus(StatusEnum.Active.getName());
		flightRepository.save(flightDetails.get());
		return input.getFlightNo() + " Flight Scheduled";
	}

	// Cancel The Flight Based on Flight no and journey date
	@Override
	public String cancelFlight(AddDetailsRequest input) {
		log.info("Input Validation Check at Cancel Flight Service ");
		if (!StringUtils.hasLength(input.getFlightNo()))
			throw new OwnException("Inputs Can't be null.....");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(input.getFlightNo(),
				input.getJourneyDate());
		if (!flightDetails.isPresent())
			throw new OwnException("No Flight Details Exists on " + input.getFlightNo());
		flightDetails.get().setStatus(Boolean.FALSE);
		flightDetails.get().setFlightStatus(StatusEnum.INACTIVE.getName());
		flightRepository.save(flightDetails.get());
		return input.getFlightNo() + " Flight Cancelled";
	}

	@Override
	public String modifyRouteDetails(RouteDetailsRequest input) {
		if (!StringUtils.hasLength(input.getFlightNo()))
			throw new OwnException("Inputs Can't be null.....");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(input.getFlightNo(),
				input.getJourneyDate());
		if (!flightDetails.isPresent())
			throw new OwnException("No Flight Details Exists on " + input.getFlightNo() + " to Modify Route Details");
		if (StringUtils.hasLength(input.getFromRoute()))
			flightDetails.get().setFromRoute(input.getFromRoute());
		if (StringUtils.hasLength(input.getToRoute()))
			flightDetails.get().setFromRoute(input.getToRoute());
		flightRepository.save(flightDetails.get());
		return " Route Details Modified for : " + input.getFlightNo();
	}

	@Override
	public String modifySchedule(ScheduleFlightRequest input) {
		if (!StringUtils.hasLength(input.getFlightNo()))
			throw new OwnException("Inputs Can't be null.....");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(input.getFlightNo(),
				input.getJourneyDate());
		if (!flightDetails.isPresent())
			throw new OwnException("No Flight Details Exists on " + input.getFlightNo() + " to Schedule");

		if (input.getStatus()) {
			flightDetails.get().setStatus(Boolean.TRUE);
			flightDetails.get().setFlightStatus(StatusEnum.Active.getName());
		} else {
			flightDetails.get().setStatus(Boolean.FALSE);
			flightDetails.get().setFlightStatus(StatusEnum.INACTIVE.getName());
		}
		FlightDetails savedData = flightRepository.save(flightDetails.get());
		return input.getFlightNo() + "Flight " + savedData.getFlightStatus() + "Successfully";
	}

	// Modifying SeatDetails After Cancellation or Booking
	@Override
	public String modifySeatDetails(SeatModification seatModification) {
		log.info("Modifying Seats after Booking or Cancellation..");
		int seatsAfterBooking = 0;
		Optional<FlightDetails> flightDetails = flightRepository
				.findByFlightNoAndJourneyDate(seatModification.getFlightNo(), seatModification.getJourneyDate());

		if (!flightDetails.isPresent())
			throw new OwnException("No Flight Details Exists on " + seatModification.getFlightNo());
		int remainingSeats = flightDetails.get().getRemainingSeats();
		if (seatModification.getModifyType().equalsIgnoreCase(ActionEnum.CREATE.getName()))
			seatsAfterBooking = remainingSeats - seatModification.getNoSeats();
		else if (seatModification.getModifyType().equalsIgnoreCase(ActionEnum.CANCEL.getName())) {
			seatsAfterBooking = remainingSeats + seatModification.getNoSeats();
		} else
			return null;
		flightDetails.get().setRemainingSeats(seatsAfterBooking);
		flightRepository.save(flightDetails.get());
		return "modified";
	}

}
