package com.flight.service.fms.serviceImpl;

import java.time.LocalDate;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.flight.service.fms.entilty.FlightDetails;
import com.flight.service.fms.utils.OwnException;
import com.flight.service.fms.vo.FlightDetailsVO;

@Component
public class FlightDetailsMapper {

	public void checkingAuthorizedUser(String user) {
		if(!StringUtils.hasLength(user))
			throw new OwnException("User Name Missing..");
		// Rest to Check user
	}

	public FlightDetails convertInputToEntity(FlightDetailsVO input) {

		FlightDetails entity = new FlightDetails();
		entity.setFlightName(input.getFlightName());
		entity.setFlightNo(input.getFlightNo());
		entity.setFromRoute(input.getFromRoute());
		entity.setToRoute(input.getToRoute());
		entity.setTicketPrice(input.getTicketPrice());
		entity.setStatus(Boolean.TRUE);
		entity.setTotalSeats(input.getTotalSeats());
		entity.setRemainingSeats(input.getTotalSeats());
		entity.setJourneyDate(LocalDate.now());

		return entity;
	}

	public FlightDetailsVO convertEntityToInput(FlightDetails entity) {

		FlightDetailsVO vo = new FlightDetailsVO();
		vo.setFlightName(entity.getFlightName());
		vo.setFlightNo(entity.getFlightNo());
		vo.setFromRoute(entity.getFromRoute());
		vo.setToRoute(entity.getToRoute());
		vo.setTicketPrice(entity.getTicketPrice());
		vo.setStatus(entity.getStatus());
		vo.setFlightStatus(entity.getFlightStatus());
		vo.setTotalSeats(entity.getTotalSeats());
		vo.setRemainingSeats(entity.getTotalSeats());
		vo.setJourneyDate(entity.getJourneyDate());

		return vo;
	}

	public <T> void funPoint(T value, Consumer<T> consumer) {
		if (value != null) {
			consumer.accept(value);
		} else
			throw new OwnException("Pass All the Required Fields...");
	}
}
