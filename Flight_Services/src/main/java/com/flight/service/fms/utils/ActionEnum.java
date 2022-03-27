package com.flight.service.fms.utils;

public enum ActionEnum {

	CREATE("create"), MODIFY("modify"), CANCEL("cancel");

	private String name;

	ActionEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
