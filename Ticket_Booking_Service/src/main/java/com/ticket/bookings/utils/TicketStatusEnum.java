package com.ticket.bookings.utils;

public enum TicketStatusEnum {

	BOOKED("booked"), CANCELED("canceled");
	
	private String name;

	TicketStatusEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

