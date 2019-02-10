package com.solarSystem.service.dto;

public enum ReportStatus {
	COMPLETED("Report complete", ""), INPROCESS("Report in process", "The values needed are beign calculated");

	private String state;
	private String reason;

	private ReportStatus(String state, String reason) {
		this.state = state;
		this.reason = reason;
	}
}
