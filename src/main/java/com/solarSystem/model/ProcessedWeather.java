package com.solarSystem.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ProcessedWeather {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private SolarSystem system;
	private int processedDays;
	private int daysProcessing;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SolarSystem getSystem() {
		return system;
	}

	public void setSystem(SolarSystem system) {
		this.system = system;
	}

	public int getProcessedDays() {
		return processedDays;
	}

	public void setProcessedDays(int processedDays) {
		this.processedDays = processedDays;
	}

	public int getDaysProcessing() {
		return daysProcessing;
	}

	public void setDaysProcessing(int daysProcessing) {
		this.daysProcessing = daysProcessing;
	}
}
