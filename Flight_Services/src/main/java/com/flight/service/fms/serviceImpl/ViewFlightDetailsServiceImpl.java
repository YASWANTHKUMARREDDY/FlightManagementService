package com.flight.service.fms.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.flight.service.fms.entilty.FlightDetails;
import com.flight.service.fms.repository.FlightRepository;
import com.flight.service.fms.service.ViewFlightDetailsService;
import com.flight.service.fms.utils.OwnException;
import com.flight.service.fms.utils.StatusEnum;
import com.flight.service.fms.vo.AvailabilityResponse;
import com.flight.service.fms.vo.FlightDetailsVO;
import com.flight.service.fms.vo.RouteDetailsRequest;
import com.flight.service.fms.vo.ScheduleFlightRequest;

@Service
public class ViewFlightDetailsServiceImpl implements ViewFlightDetailsService {

	private static final Logger log = LoggerFactory.getLogger(ViewFlightDetailsServiceImpl.class);

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private FlightDetailsMapper mapper;

	private FlightDetails commonMethodToGetFlightDetails(String flightNo, LocalDate journeyDate) {
		log.info("Fetching Flight Details From Data Base..");
		Optional<FlightDetails> flightDetails = flightRepository.findByFlightNoAndJourneyDate(flightNo, journeyDate);
		if (!flightDetails.isPresent())
			throw new OwnException("Flight Details Not Found for Flight No " + flightNo);
		return flightDetails.get();
	}

	@Override
	public FlightDetailsVO viewFlightDetails(String flightNo, LocalDate journeyDate) {
		FlightDetails flightDetails = commonMethodToGetFlightDetails(flightNo, journeyDate);
		log.info("Setting Flight Details..");
		FlightDetailsVO result = mapper.convertEntityToInput(flightDetails);
		return result;
	}

	@Override
	public ScheduleFlightRequest viewFlightSchedule(String flightNo, LocalDate journeyDate) {
		FlightDetails flightDetails = commonMethodToGetFlightDetails(flightNo, journeyDate);
		log.info("Setting Flight Schedule..");
		ScheduleFlightRequest result = new ScheduleFlightRequest();
		result.setFlightNo(flightNo);
		result.setJourneyDate(journeyDate);
		result.setTotalSeats(flightDetails.getTotalSeats());
		result.setTicketPrice(flightDetails.getTicketPrice());
		result.setStatus(flightDetails.getStatus());
		return result;
	}

	@Override
	public RouteDetailsRequest viewRouteDetails(String flightNo, LocalDate journeyDate) {
		FlightDetails flightDetails = commonMethodToGetFlightDetails(flightNo, journeyDate);
		log.info("Setting Route Details..");
		RouteDetailsRequest result = new RouteDetailsRequest();
		result.setFlightNo(flightNo);
		result.setJourneyDate(journeyDate);
		result.setFromRoute(flightDetails.getFromRoute());
		result.setToRoute(flightDetails.getToRoute());
		return result;
	}

	@Override
	public List<AvailabilityResponse> checkFlightAvailability(String fromRoute, String toRoute) {
		List<AvailabilityResponse> availableFlights = new ArrayList<AvailabilityResponse>();
		log.info("Checking Flight Availablie or Not..");
		if (!StringUtils.hasLength(fromRoute) || !StringUtils.hasLength(toRoute))
			throw new OwnException("From Route and  To Route Details Missing...");
		List<FlightDetails> flightDetails = flightRepository.findByFromRouteAndToRouteAndFlightStatus(fromRoute,
				toRoute, StatusEnum.Active.getName());
		if (!ObjectUtils.isEmpty(flightDetails)) {
			flightDetails.forEach(f -> {
				AvailabilityResponse available = new AvailabilityResponse();
				available.setFlightNo(f.getFlightNo());
				available.setFlightName(f.getFlightName());
				available.setFromRoute(f.getFromRoute());
				available.setToRoute(f.getToRoute());
				available.setTotalSeats(f.getTotalSeats());
				available.setRemainingSeats(f.getRemainingSeats());
				available.setTicketPrice(f.getTicketPrice());
				available.setJourneyDate(f.getJourneyDate());
				availableFlights.add(available);
			});
		}
		return availableFlights;
	}

}
