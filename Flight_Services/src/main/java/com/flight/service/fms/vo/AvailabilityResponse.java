package com.flight.service.fms.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AvailabilityResponse {

	private String flightNo;

	private String flightName;

	private String fromRoute;

	private String toRoute;

	private Double ticketPrice;

	private int totalSeats;

	private int remainingSeats;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getFlightName() {
		return flightName;
	}

	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	public String getFromRoute() {
		return fromRoute;
	}

	public void setFromRoute(String fromRoute) {
		this.fromRoute = fromRoute;
	}

	public String getToRoute() {
		return toRoute;
	}

	public void setToRoute(String toRoute) {
		this.toRoute = toRoute;
	}

	public Double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(Double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getRemainingSeats() {
		return remainingSeats;
	}

	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

}
