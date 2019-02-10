package com.solarSystem.service.dto;

import com.solarSystem.service.WeatherEnum;

public class WeatherDto {

	private double rainIntensity;
	private WeatherEnum weather;

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
}
