package com.flight.service.fms.utils;

public enum StatusEnum {

	Active("active"), INACTIVE("inActive");
	
	private String name;

	StatusEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

