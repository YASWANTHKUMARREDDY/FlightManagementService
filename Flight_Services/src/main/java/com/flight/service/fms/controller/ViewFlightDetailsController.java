package com.flight.service.fms.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight.service.fms.service.ViewFlightDetailsService;
import com.flight.service.fms.utils.BaseUrls;
import com.flight.service.fms.utils.OwnException;
import com.flight.service.fms.utils.OwnResponse;
import com.flight.service.fms.vo.AvailabilityResponse;
import com.flight.service.fms.vo.FlightDetailsVO;
import com.flight.service.fms.vo.RouteDetailsRequest;
import com.flight.service.fms.vo.ScheduleFlightRequest;

@RestController
public class ViewFlightDetailsController {

	private static final Logger log = LoggerFactory.getLogger(ViewFlightDetailsController.class);

	@Autowired
	ViewFlightDetailsService viewFlightDetailsService;

	@GetMapping(value = BaseUrls.VIEWFLIGHTDETAILS, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> viewflightDetails(@RequestParam String flightNo,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate journeyDate) {
		log.info("View Flight Details Api Calling..");
		try {
			validationCheck(flightNo, journeyDate);
			FlightDetailsVO result = viewFlightDetailsService.viewFlightDetails(flightNo, journeyDate);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in View Flight Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in View Flight Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = BaseUrls.VIEWSCHEDULE, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> viewflightSchedule(@RequestParam String flightNo, @RequestParam LocalDate journeyDate) {
		log.info("View Flight Schedule Api Calling..");
		try {
			validationCheck(flightNo, journeyDate);
			ScheduleFlightRequest result = viewFlightDetailsService.viewFlightSchedule(flightNo, journeyDate);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in View Flight Schedule " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in View Flight Schedule " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value = BaseUrls.VIEWROUTEDETAILS, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public OwnResponse<?> viewRouteDetails(@RequestParam String flightNo, @RequestParam LocalDate journeyDate) {
		log.info("View Route Details Api Calling..");
		try {
			validationCheck(flightNo, journeyDate);
			RouteDetailsRequest result = viewFlightDetailsService.viewRouteDetails(flightNo, journeyDate);
			return new OwnResponse<>(result, true, HttpStatus.OK);
		} catch (OwnException e) {
			log.info("Custom Exception Occured in View Route Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			log.info("Exception Occured in View Route Details " + e.getMessage());
			return new OwnResponse<>(e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	void validationCheck(String flightNo, LocalDate journeyDate) {
		if (!StringUtils.hasLength(flightNo) || journeyDate == null)
			throw new OwnException("Flight no and Journey Details Missing...");
	}

	@GetMapping(value = BaseUrls.FLIGHTAVAILABLITY, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<AvailabilityResponse> checkFlightAvailability(@RequestParam String fromRoute,
			@RequestParam String toRoute) {
		log.info("View Route Details Api Calling..");
		return viewFlightDetailsService.checkFlightAvailability(fromRoute, toRoute);

	}

}
