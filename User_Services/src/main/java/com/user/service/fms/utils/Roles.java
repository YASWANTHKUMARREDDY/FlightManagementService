package com.user.service.fms.utils;

public enum Roles {

	Admin("admin"), Traveler("traveler");

	private String name;

	Roles(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
