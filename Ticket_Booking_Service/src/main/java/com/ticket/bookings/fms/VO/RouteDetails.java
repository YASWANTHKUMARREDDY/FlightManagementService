package com.ticket.bookings.fms.VO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RouteDetails {

	private String fromRoute;

	private String toRoute;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate journeyDate;

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

	public LocalDate getJourneyDate() {
		return journeyDate;
	}

	public void setJourneyDate(LocalDate journeyDate) {
		this.journeyDate = journeyDate;
	}

}
