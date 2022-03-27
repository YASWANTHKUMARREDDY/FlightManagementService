package com.flight.service.fms.service;

import com.flight.service.fms.vo.AddDetailsRequest;
import com.flight.service.fms.vo.RouteDetailsRequest;
import com.flight.service.fms.vo.ScheduleFlightRequest;
import com.flight.service.fms.vo.SeatModification;

public interface FlightBookingService {

	public String addFlightDetails(AddDetailsRequest input);
	
	public String addRouteDetails(RouteDetailsRequest input);
	
	public String scheduleFlight(ScheduleFlightRequest input);
	
	public String cancelFlight(AddDetailsRequest input);
	
	public String modifyRouteDetails(RouteDetailsRequest input);
	
	public String modifySchedule(ScheduleFlightRequest input);	

	public String modifySeatDetails(SeatModification seatModification);
}
