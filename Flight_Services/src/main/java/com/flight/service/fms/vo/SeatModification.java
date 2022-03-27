package com.flight.service.fms.vo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SeatModification {

	private String flightNo;

	private int noSeats;

	private String modifyType;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public int getNoSeats() {
		return noSeats;
	}

	public void setNoSeats(int noSeats) {
		this.noSeats = noSeats;
	}

	public String getModifyType() {
		return modifyType;
	}

	public void setModifyType(String modifyType) {
		this.modifyType = modifyType;
	}

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

}
