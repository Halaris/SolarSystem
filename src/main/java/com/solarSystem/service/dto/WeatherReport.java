package com.solarSystem.service.dto;

public class WeatherReport {

	private ReportStatus status;
	private Report report;

	public ReportStatus getStatus() {
		return status;
	}

	public void setStatus(ReportStatus status) {
		this.status = status;
	}

	public Report getReport() {
		return report;
	}

	public void setReport(Report report) {
		this.report = report;
	}
}
