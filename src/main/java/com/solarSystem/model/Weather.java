package com.solarSystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.solarSystem.service.WeatherEnum;

@Entity
public class Weather {
	@Id
	private int id;
	private long day;
	private double rainIntensity;
	private WeatherEnum weather;
	private SolarSystem system;

	public Weather(long day, SolarSystem system) {
		super();
		this.day = day;
		this.setSystem(system);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public double getRainIntensity() {
		return rainIntensity;
	}

	public void setRainIntensity(double rainIntensity) {
		this.rainIntensity = rainIntensity;
	}

	public WeatherEnum getWeather() {
		return weather;
	}

	public void setWeather(WeatherEnum weather) {
		this.weather = weather;
	}

	public SolarSystem getSystem() {
		return system;
	}

	public void setSystem(SolarSystem system) {
		this.system = system;
	}

}
