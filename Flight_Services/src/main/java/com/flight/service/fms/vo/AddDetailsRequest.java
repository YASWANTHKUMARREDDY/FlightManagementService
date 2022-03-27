package com.flight.service.fms.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AddDetailsRequest {

	private String user;

	private String flightNo;

	private String flightName;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

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

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

}
