package com.flight.service.fms.service;

import java.time.LocalDate;
import java.util.List;

import com.flight.service.fms.vo.AvailabilityResponse;
import com.flight.service.fms.vo.FlightDetailsVO;
import com.flight.service.fms.vo.RouteDetailsRequest;
import com.flight.service.fms.vo.ScheduleFlightRequest;

public interface ViewFlightDetailsService {

	public FlightDetailsVO viewFlightDetails(String flightNo, LocalDate journeyDate);

	public ScheduleFlightRequest viewFlightSchedule(String flightNo, LocalDate journeyDate);

	public RouteDetailsRequest viewRouteDetails(String flightNo, LocalDate journeyDate);
	
	public List<AvailabilityResponse> checkFlightAvailability(String fromRoute, String toRoute);
}
