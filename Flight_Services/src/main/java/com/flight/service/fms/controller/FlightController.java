package com.flight.service.fms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flight.service.fms.service.FlightBookingService;
import com.flight.service.fms.serviceImpl.FlightDetailsMapper;
import com.flight.service.fms.utils.BaseUrls;
import com.flight.service.fms.utils.OwnException;
import com.flight.service.fms.utils.OwnResponse;
import com.flight.service.fms.vo.AddDetailsRequest;
import com.flight.service.fms.vo.RouteDetailsRequest;
import com.flight.service.fms.vo.ScheduleFlightRequest;
import com.flight.service.fms.vo.SeatModification;

@RestController
public class FlightController {

	private static final Logger log = LoggerFactory.getLogger(FlightController.class);

	@Autowired
	FlightBookingService flightBookingService;

	@Autowired
	FlightDetailsMapper flightDetailsMapper;

	@PostMapping(value = BaseUrls.ADDFLIGHTDETAILS, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> addflightDetails(@RequestBody AddDetailsRequest input) {
		log.info("Add Flight Details Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			flightDetailsMapper.checkingAuthorizedUser(input.getUser());
			String result = flightBookingService.addFlightDetails(input);
			return new OwnResponse<>(result, true, HttpStatus.CREATED);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Add Flight Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Add Flight Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = BaseUrls.ADDROUTEDETAILS, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> addRouteDetails(@RequestBody RouteDetailsRequest input) {
		log.info("Add Flight Details Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			flightDetailsMapper.checkingAuthorizedUser(input.getUser());
			String result = flightBookingService.addRouteDetails(input);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Add Flight Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Add Flight Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = BaseUrls.SCHEDULEFLIGHT, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> scheduleFlight(@RequestBody ScheduleFlightRequest input) {
		log.info("Schedule Flight Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			flightDetailsMapper.checkingAuthorizedUser(input.getUser());
			String result = flightBookingService.scheduleFlight(input);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Schedule Flight API" + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Schedule Flight API " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = BaseUrls.CANCELFLIGHT, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> cancelFlight(@RequestBody AddDetailsRequest input) {
		log.info("Cancel Flight Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			flightDetailsMapper.checkingAuthorizedUser(input.getUser());
			String result = flightBookingService.cancelFlight(input);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Cancel Flight API" + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Cancel Flight API " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = BaseUrls.MODIFYSCHEDULE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> modifyschedule(@RequestBody ScheduleFlightRequest input) {
		log.info("Schedule Modify Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			flightDetailsMapper.checkingAuthorizedUser(input.getUser());
			String result = flightBookingService.modifySchedule(input);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Schedule Modify API" + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Schedule Modify API " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = BaseUrls.MODIFYROUTEDETAILS, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> modifyFlightDetails(@RequestBody RouteDetailsRequest input) {
		log.info("Modify Flight Details Api Calling..");
		try {
			if (ObjectUtils.isEmpty(input))
				throw new OwnException("Inpus Cant be null....");
			flightDetailsMapper.checkingAuthorizedUser(input.getUser());
			String result = flightBookingService.modifyRouteDetails(input);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Modify Flight Details API" + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Modify Flight Details API " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(value = BaseUrls.MODIFYSEATDETAILS, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> modifySeatDetails(@RequestBody SeatModification seatModification) {
		log.info("Modify Seat Details Api Calling..");
		try {
			String result = flightBookingService.modifySeatDetails(seatModification);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in Modify Seat Details API" + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in Modify Seat Details API " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
